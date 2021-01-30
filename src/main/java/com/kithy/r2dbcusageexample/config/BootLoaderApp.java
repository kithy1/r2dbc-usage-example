package com.kithy.r2dbcusageexample.config;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class BootLoaderApp {

    @Bean
    public CommandLineRunner initDatabase(ConnectionFactory cf) {

        return (args) ->
                Flux.from(cf.create())
                        .flatMap(c ->
                                Flux.from(c.createBatch()
                                        .add("drop table if exists Account")
                                        .add("create table Account(" +
                                                "id IDENTITY(1,1)," +
                                                "iban varchar(80) not null," +
                                                "balance DECIMAL(18,2) not null)")
                                        .add("insert into Account(iban,balance)" +
                                                "values('BR430120980198201982',100.00)")
                                        .add("insert into Account(iban,balance)" +
                                                "values('BR430120998729871000',250.00)")
                                        .execute())
                                        .doFinally((st) -> c.close())
                        )
                        .log()
                        .blockLast();
    }
}
