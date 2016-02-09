package com.arcbees.client.application.services;

import java.util.HashMap;
import java.util.Map;

// Dummy implementation of a UserService
public class UserServiceImpl implements UserService {
    private Map<Integer, String> usernames;

    public UserServiceImpl() {
        usernames = new HashMap<>();

        usernames.put(1, "Arcbees");
        usernames.put(2, "Joel");
        usernames.put(3, "Olivier");
    }

    @Override
    public String getUsername(int userId) {
        return usernames.get(userId);
    }

    @Override
    public void saveUsername(int userId, String username) {
        usernames.put(userId, username);
    }
}
