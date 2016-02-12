package com.arcbees.client.application.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class UserDeletedEvent extends GwtEvent<UserDeletedEventHandler> {
    public static Type<UserDeletedEventHandler> TYPE = new Type<>();

    @Override
    public Type<UserDeletedEventHandler> getAssociatedType() {
        return TYPE;
    }

    protected void dispatch(UserDeletedEventHandler handler) {
        handler.onUserDeleted(this);
    }

    public static void fire(HasHandlers source) {
        source.fireEvent(new UserDeletedEvent());
    }
}
