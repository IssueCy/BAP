package de.kolja.bap.service;

public class AuthService {

    public boolean login(String username, String password) {
        return username.equals("admin") && password.equals("1234");
    }
}