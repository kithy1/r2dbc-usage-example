package com.kithy.r2dbcusageexample.model;

import java.math.BigDecimal;

public class Account {

    private Long id;
    private String iban;
    private BigDecimal balance;

    public Account() {
    }

    public Account(Long id, String iban, BigDecimal balance) {
        this.id = id;
        this.iban = iban;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
