package com.ewcikyr.accounts.resources;

import com.ewcikyr.accounts.dao.AccountsDao;
import com.ewcikyr.accounts.model.AccountInfo;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/user/{user}/account/{account}")
public class AccountsResource {
    private AccountsDao accounts;

    public AccountsResource(AccountsDao accounts) {
        this.accounts = accounts;
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AccountInfo getAccount(@PathParam("user") Long userId, @PathParam("account") Long accountId) {
        return accounts.getAccount(userId, accountId);
    }
}
