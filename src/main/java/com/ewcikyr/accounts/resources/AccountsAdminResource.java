package com.ewcikyr.accounts.resources;

import com.ewcikyr.accounts.dao.AccountsDao;
import com.ewcikyr.accounts.model.AccountInfo;
import com.google.common.collect.ImmutableMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Map;

@Path("/admin")
public class AccountsAdminResource {

    private AccountsDao accounts;

    public AccountsAdminResource(AccountsDao accounts) {
        this.accounts = accounts;
    }

    @POST
    @Path("accounts")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Map createAccount(AccountInfo newAccount) {
        accounts.createAccount(newAccount);

        return new ImmutableMap.Builder<String, String>()
                .put("status", "ok")
                .build();
        //todo HATEOAS location of resource
    }
}
