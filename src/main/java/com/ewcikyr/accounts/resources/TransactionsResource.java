package com.ewcikyr.accounts.resources;

import com.ewcikyr.accounts.dao.AccountsDao;
import com.ewcikyr.accounts.model.Transfer;
import com.google.common.collect.ImmutableMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Map;

@Path("/transactions")
public class TransactionsResource {
    private AccountsDao accounts;

    public TransactionsResource(AccountsDao accounts) {
        this.accounts = accounts;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Map transferAmount(Transfer transfer) {
        accounts.transferAmount(transfer);

        return new ImmutableMap.Builder<String, String>()
                .put("status", "ok")
                .build();
        //todo HATEOAS location of resource
    }
}
