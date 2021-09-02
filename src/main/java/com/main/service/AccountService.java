package com.main.service;

import com.dao.AccountDAO;
import com.dao.IAccountDAO;
import com.main.account.Account;

import javax.inject.Inject;
import java.util.List;

public class AccountService implements IAccountService{


    @Inject
    private IAccountDAO accountDAO;


    @Override
    public List<Account> findAll() {
        return accountDAO.fillAll();
    }

    @Override
    public boolean save(Account account) {
        return accountDAO.save(account);
    }

    @Override
    public boolean findOne(Account account) {
        return accountDAO.findOne(account);
    }


}
