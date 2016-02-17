package com.arcbees.client.application.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class ConnectionErrorEvent extends GwtEvent<ConnectionErrorEventHandler> {
    public static Type<ConnectionErrorEventHandler> TYPE = new Type<ConnectionErrorEventHandler>();

    public Type<ConnectionErrorEventHandler> getAssociatedType() {
        return TYPE;
    }

    protected void dispatch(ConnectionErrorEventHandler handler) {
        handler.onConnectionError(this);
    }

    public static void fire(HasHandlers source) {
        source.fireEvent(new ConnectionErrorEvent());
    }
}
