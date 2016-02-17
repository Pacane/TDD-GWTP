package com.arcbees.client.api;

import javax.inject.Inject;

import com.arcbees.client.application.events.ConnectionErrorEvent;
import com.google.gwt.core.client.GWT;
import com.google.web.bindery.event.shared.EventBus;

public class ConnectionErrorConsoleNotifier implements ConnectionErrorNotifier{
    @Override
    public void onConnectionError(ConnectionErrorEvent event) {
        GWT.log("An error occured while communicating with the server");
    }

    @Inject
    public ConnectionErrorConsoleNotifier(EventBus eventBus) {
        eventBus.addHandler(ConnectionErrorEvent.TYPE, this);
    }
}
