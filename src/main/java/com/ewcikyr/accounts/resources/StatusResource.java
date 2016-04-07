package com.ewcikyr.accounts.resources;


import com.google.common.collect.ImmutableMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Map;

@Path("/status")
public class StatusResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map responseOk() {
        return new ImmutableMap.Builder<String, String>()
                .put("status", "ok")
                .build();
    }
}
