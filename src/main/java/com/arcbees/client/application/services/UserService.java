package com.arcbees.client.application.services;

public interface UserService {
    String getUsername(int userId);

    void saveUsername(int userId, String username);
}
