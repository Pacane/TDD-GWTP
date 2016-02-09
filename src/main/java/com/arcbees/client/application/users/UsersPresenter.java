package com.arcbees.client.application.users;

import java.util.Map;

import com.arcbees.client.application.services.UserService;
import com.arcbees.client.place.NameTokens;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.presenter.slots.NestedSlot;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

public class UsersPresenter extends Presenter<UsersPresenter.MyView, UsersPresenter.MyProxy> {
    interface MyView extends View {
        void displayUsers(Map<Integer, String> users);
    }

    @ProxyStandard
    @NameToken(NameTokens.USERS)
    interface MyProxy extends ProxyPlace<UsersPresenter> {
    }

    public static final NestedSlot SLOT_MAIN = new NestedSlot();

    private final UserService userService;

    @Inject
    UsersPresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy,
            UserService userService) {
        super(eventBus, view, proxy, RevealType.Root);

        this.userService = userService;
    }

    @Override
    public void prepareFromRequest(PlaceRequest request) {
        Map<Integer, String> users = userService.getUsers();

        getView().displayUsers(users);
    }
}
