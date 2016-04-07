package com.ewcikyr.accounts.dao;

import com.ewcikyr.accounts.model.Account;
import com.ewcikyr.accounts.model.AccountInfo;
import com.ewcikyr.accounts.model.Transfer;

import java.util.HashMap;
import java.util.Map;

public class AccountsDao {
    private Map<String, Long> accounts;

    public AccountsDao() {
        accounts = new HashMap<>();
    }

    private String getAccountKey(Long user, Long account) {
        return user + "_" + account;
    }

    public void createAccount(AccountInfo accountInfo) {
        String accountKey = getAccountKey(accountInfo.getUser(), accountInfo.getAccount());
        accounts.put(accountKey, accountInfo.getAmount());
    }

    public AccountInfo getAccount(Long userId, Long accountId) {
        String accountKey = getAccountKey(userId, accountId);

        Long amount = accounts.get(accountKey);

        return new AccountInfo(userId, accountId, amount);
    }

    public void transferAmount(Transfer transfer) {
        synchronized (accounts) {
            Account fromAccount = transfer.getFrom();
            Account toAccount = transfer.getTo();
            Long transferAmount = transfer.getAmount();

            String fromAccountKey = getAccountKey(fromAccount.getUser(), fromAccount.getAccount());
            String toAccountKey = getAccountKey(toAccount.getUser(), toAccount.getAccount());

            accounts.put(fromAccountKey, accounts.get(fromAccountKey) - transferAmount);
            accounts.put(toAccountKey, accounts.get(toAccountKey) + transferAmount);
        }
    }
}
