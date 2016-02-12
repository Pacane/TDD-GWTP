package com.arcbees.client.application.services;

import java.util.HashMap;
import java.util.Map;

import com.arcbees.client.application.events.UserDeletedEvent;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

// Dummy implementation of a UserService
public class UserServiceImpl implements UserService, HasHandlers {
    private final EventBus eventBus;

    private Map<Integer, String> usernames;

    @Inject
    public UserServiceImpl(EventBus eventBus) {
        this.eventBus = eventBus;

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

    @Override
    public Map<Integer, String> getUsers() {
        return usernames;
    }

    @Override
    public void deleteUser(int userId) {
        usernames.remove(userId);
        UserDeletedEvent.fire(this);
    }

    @Override
    public void fireEvent(GwtEvent<?> gwtEvent) {
        eventBus.fireEventFromSource(gwtEvent, this);
    }
}
