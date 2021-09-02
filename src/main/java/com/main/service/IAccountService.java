package com.main.service;

import com.main.account.Account;

import java.util.List;

public interface IAccountService {
    List<Account> findAll();
    boolean save(Account account);
    boolean findOne(Account account);
}
