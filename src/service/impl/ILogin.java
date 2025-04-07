package service.impl;

import model.UserAccount;

import java.util.Map;

public interface ILogin {
    Map<String,String> userAccountsMap();
    boolean userAccountExists(String username);
    boolean validateAccount(String username, String password);
}
