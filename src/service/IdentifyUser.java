package service;

import service.impl.ILogin;
import util.ReadAndWriteData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IdentifyUser implements ILogin {
    private static final String accountDataFilePath = "E:\\CS M2\\src\\repository\\accounts.csv";

    @Override
    public Map<String,String> userAccountsMap(){
        Map<String,String> userAccountsMap = new HashMap<>();
        List<String> lines = ReadAndWriteData.readFile(accountDataFilePath);
        for (String line : lines) {
            String[] fields = line.split(",");
            userAccountsMap.put(fields[0]+fields[1], fields[2]);
        }

        return userAccountsMap;
    }

    @Override
    public boolean userAccountExists(String username) {
        return false;
    }

    @Override
    public boolean validateAccount(String username, String password) {
        return false;
    }
}
