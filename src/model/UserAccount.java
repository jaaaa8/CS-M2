package model;

import util.HashFunction;

public class UserAccount {
    private String username;
    private String password;
    private String refID;

    public UserAccount() {
    }

    public UserAccount(String username, String password, String refID) {
        this.username = username;
        this.password = HashFunction.hashString(password);
        this.refID = refID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRefID() {
        return refID;
    }

    public void setRefID(String refID) {
        this.refID = refID;
    }

    public String getInfo(){
        return username + "," + password + "," + refID;
    }
}
