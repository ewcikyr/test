package com.ewcikyr.accounts.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountInfo {
    private Long user;
    private Long account;
    private Long amount;

    public AccountInfo() {
    }

    public AccountInfo(Long user, Long account, Long amount) {
        this.user = user;
        this.account = account;
        this.amount = amount;
    }

    @JsonProperty
    public Long getUser() {
        return user;
    }

    @JsonProperty
    public Long getAccount() {
        return account;
    }

    @JsonProperty
    public Long getAmount() {
        return amount;
    }
}
