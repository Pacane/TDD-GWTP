package com.arcbees.client.application.services;

import java.util.Map;

public interface UserService {
    String getUsername(int userId);

    void saveUsername(int userId, String username);

    Map<Integer, String> getUsers();

    void deleteUser(int userId);
}
