package com.dao;


import com.main.account.Account;

import java.util.List;

public interface IAccountDAO {
    List<Account> fillAll();
    boolean save(Account account);
    boolean findOne(Account account);
}
