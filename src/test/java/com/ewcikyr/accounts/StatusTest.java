package com.ewcikyr.accounts;

import com.ewcikyr.accounts.config.MainConfig;
import com.jayway.restassured.http.ContentType;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.Test;

import static com.google.common.io.Resources.getResource;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class StatusTest {
    @ClassRule
    public static final DropwizardAppRule<MainConfig> RULE = new DropwizardAppRule<>(AccountsApplication.class, getResource("config/test-config.yaml").getPath());

    @Test
    public void checkHealth() {
        given()
            .port(RULE.getLocalPort())
        .when()
            .get("/status")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("status", equalTo("ok"));
    }
}
