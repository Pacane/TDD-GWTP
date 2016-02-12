package com.arcbees.client.application.events;

import com.google.gwt.event.shared.EventHandler;

public interface UserDeletedEventHandler extends EventHandler {
    void onUserDeleted(UserDeletedEvent event);
}
