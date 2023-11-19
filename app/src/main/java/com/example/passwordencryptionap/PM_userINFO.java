package com.example.passwordencryptionap;

import java.util.HashMap;
import java.util.Map;

public class PM_userINFO {
    private HashMap<String, String> userInformation = new HashMap<String, String>();
    //add user method
    public void addAccount(String username, String password){
        userInformation.put(username, password);
    }
    //checks username method
    public boolean checkUsername(String username){
        return userInformation.containsKey(username);
    }
    //check account method
    public boolean checkInformation(String username, String password){
        if(userInformation.containsKey(username)){
            return password.equals(userInformation.get(username));
        }
        return false;
    }
    //load account information
    public void loadUserInformation(Map<String, ?> preferencesMap){
        for(Map.Entry<String, ?> entries : preferencesMap.entrySet()){
            if(!entries.getKey().equals("RememberMeCheckbox") || !entries.getKey().equals("LastSavedUsername") ||
                    !entries.getKey().equals("LastSavedPassword")){
                userInformation.put(entries.getKey(), entries.getValue().toString());
            }
        }
    }
}
