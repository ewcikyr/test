package com.ewcikyr.accounts;

import com.ewcikyr.accounts.config.MainConfig;
import com.google.common.collect.ImmutableMap;
import com.jayway.restassured.http.ContentType;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.Test;

import java.util.Map;

import static com.google.common.io.Resources.getResource;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AccountsTest {
    @ClassRule
    public static final DropwizardAppRule<MainConfig> RULE = new DropwizardAppRule<>(AccountsApplication.class, getResource("config/test-config.yaml").getPath());

    @Test
    public void createAccount() throws Exception {
        givenAccountWithAmount(123, 456, 238434);

        given()
            .port(RULE.getLocalPort())
        .when()
            .get("/user/123/account/456")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("amount", equalTo(238434));
    }

    @Test
    public void transferMoney() {
        givenAccountWithAmount(1, 10, 1021);
        givenAccountWithAmount(2, 11, 4234);

        Map fromAccount = new ImmutableMap.Builder<>()
                .put("user", 1)
                .put("account", 10)
                .build();

        Map toAccount = new ImmutableMap.Builder<>()
                .put("user", 2)
                .put("account", 11)
                .build();

        Map transfer = new ImmutableMap.Builder<>()
                .put("from", fromAccount)
                .put("to", toAccount)
                .put("amount", 19)
                .build();

        given()
            .port(RULE.getLocalPort())
        .when()
            .body(transfer)
            .contentType(ContentType.JSON)
            .post("/transactions")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("status", equalTo("ok"));

        given()
            .port(RULE.getLocalPort())
        .when()
            .get("/user/1/account/10")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("amount", equalTo(1002));

        given()
            .port(RULE.getLocalPort())
        .when()
            .get("/user/2/account/11")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("amount", equalTo(4253));

    }

    private void givenAccountWithAmount(long user, long account, long amount) {
        Map accountInfo = new ImmutableMap.Builder<>()
                .put("user", user)
                .put("account", account)
                .put("amount", amount)
                .build();

        given()
            .port(RULE.getLocalPort())
        .when()
            .body(accountInfo)
            .contentType(ContentType.JSON)
            .post("/admin/accounts")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("status", equalTo("ok"));
    }
}
