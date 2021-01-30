package com.kithy.r2dbcusageexample.controller;

import com.kithy.r2dbcusageexample.dao.ReactiveAccountDao;
import com.kithy.r2dbcusageexample.model.Account;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class AccountController {

    private final ReactiveAccountDao accountDao;

    public AccountController(ReactiveAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @GetMapping("/accounts/{id}")
    public Mono<ResponseEntity<Account>> getAccount(@PathVariable("id") Long id) {

        return accountDao.findById(id)
                .map(acc -> new ResponseEntity<>(acc, HttpStatus.OK))
                .switchIfEmpty(Mono.just(new ResponseEntity<>(null, HttpStatus.NOT_FOUND)));
    }

    @GetMapping("/accounts")
    public Flux<Account> getAllAccounts() {
        return accountDao.findAll();
    }

    @PostMapping("/accounts")
    public Mono<ResponseEntity<Account>> postAccount(@RequestBody Account account) {
        return accountDao.createAccount(account)
                .map(acc -> new ResponseEntity<>(acc, HttpStatus.CREATED))
                .log();
    }
}
