package com.arcbees.client.api;

import javax.inject.Inject;

import com.arcbees.client.application.events.ConnectionErrorEvent;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.http.client.Response;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestCallback;

public abstract class RestCallbackImpl<T> implements RestCallback<T>, HasHandlers {
    @Inject
    static EventBus eventBus;

    private Response response;

    @Override
    public void setResponse(Response response) {
        this.response = response;
    }

    @Override
    public void onFailure(Throwable throwable) {
        onError(response);
    }

    public void onError(Response response) {
        ConnectionErrorEvent.fire(this);
    }

    @Override
    public void fireEvent(GwtEvent<?> gwtEvent) {
        eventBus.fireEventFromSource(gwtEvent, this);
    }
}
