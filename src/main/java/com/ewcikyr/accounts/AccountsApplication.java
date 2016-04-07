package com.ewcikyr.accounts;

import com.ewcikyr.accounts.config.MainConfig;
import com.ewcikyr.accounts.dao.AccountsDao;
import com.ewcikyr.accounts.resources.AccountsAdminResource;
import com.ewcikyr.accounts.resources.AccountsResource;
import com.ewcikyr.accounts.resources.StatusResource;
import com.ewcikyr.accounts.resources.TransactionsResource;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class AccountsApplication extends Application<MainConfig> {

    @Override
    public void initialize(Bootstrap<MainConfig> bootstrap) {
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(
                        bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );
    }

    @Override
    public void run(MainConfig config, Environment environment) {
        environment.jersey().register(new StatusResource());

        AccountsDao accountsDao = new AccountsDao();
        environment.jersey().register(new AccountsAdminResource(accountsDao));
        environment.jersey().register(new AccountsResource(accountsDao));
        environment.jersey().register(new TransactionsResource(accountsDao));
    }

    public static void main(String[] args) throws Exception {
        new AccountsApplication().run(args);
    }
}
