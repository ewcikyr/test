package com.ewcikyr.accounts.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Transfer {
    private Account from;
    private Account to;
    private Long amount;

    public Transfer() {
    }

    @JsonProperty
    public Account getFrom() {
        return from;
    }

    @JsonProperty
    public Account getTo() {
        return to;
    }

    @JsonProperty
    public Long getAmount() {
        return amount;
    }
}
