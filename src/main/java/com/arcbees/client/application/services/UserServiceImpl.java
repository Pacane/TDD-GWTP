package com.arcbees.client.application.services;

// Dummy implementation of a UserService
public class UserServiceImpl implements UserService {
    private String username = "Arcbees";

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void saveUsername(String username) {
        this.username = username;
    }
}
