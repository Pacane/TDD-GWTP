package com.arcbees.client.application.events;

import com.google.gwt.event.shared.EventHandler;

public interface ConnectionErrorEventHandler extends EventHandler {
    void onConnectionError(ConnectionErrorEvent event);
}
