package com.zadani.service.auth;

public interface AuthService {
    Boolean login(String username, String password);
    void logout(String username);
}
