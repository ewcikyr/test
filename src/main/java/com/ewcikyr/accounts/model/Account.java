package com.ewcikyr.accounts.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Account {
    private Long user;
    private Long account;

    public Account() {
    }

    @JsonProperty
    public Long getUser() {
        return user;
    }

    @JsonProperty
    public Long getAccount() {
        return account;
    }
}
