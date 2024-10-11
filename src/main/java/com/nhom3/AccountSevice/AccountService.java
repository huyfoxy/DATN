package com.nhom3.AccountSevice;

import java.util.List;

import com.nhom3.Entity.Account;


public interface AccountService {
    List<Account> findAccountAll();

    Account saveAccount(Account account);
    Account register(Account account);
    boolean authenticate(String username, String password);
    Account findAccountByUsername(String username); // Thêm phương thức này
}