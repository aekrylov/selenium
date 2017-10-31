package com.github.aekrylov.itis.sem05.selenium;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 10/31/17 10:47 PM
 */
public class AccountData {

    private final String username;
    private final String password;

    public AccountData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
