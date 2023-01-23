package ru.gb.oopseminar;


import java.util.Arrays;



public final class Protecteddata {
    private final String[] username;
    private final Integer pincode;
    private final Integer password;


    public Protecteddata(String[] username, Integer pincode, Integer password) {
        this.username = new String[username.length];
        for (int i = 0; i < username.length; i++) {
            this.username[i] = username[i];
        }
        this.pincode = pincode;
        this.password = password;
        System.setSecurityManager(new SecurityManager());
    }
    public Protecteddata() {
        this.username = new String[2];
        this.pincode = 0;
        this.password = 0;
    }
    public String[] getUsername() {
        String[] tempUsername = new String[username.length];
        for (int i = 0; i < tempUsername.length; i++) {
            tempUsername[i] = this.username[i];
        }
        return tempUsername;
    }
    public Integer getPincode() {
        return pincode;
    }
    public Integer getPassword() {
        return password;
    }
    public Protecteddata setUsername(String[] username) {
        return new Protecteddata(username, pincode, password);
    }
    public Protecteddata setPincode(Integer pincode) {
        return new Protecteddata(username, pincode, password);
    }
    public Protecteddata setPassword(Integer password) {
        return new Protecteddata(username, pincode, password);
    }
    @Override
    public String toString() {
        String data =
                "\nИМЯ:            \t" + Arrays.toString(username) +
                "\nПИНКОД:         \t" + pincode +
                "\nПАРОЛЬ:         \t" + password;
        return data;
    }
}
