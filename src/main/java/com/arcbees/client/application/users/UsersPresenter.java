package com.arcbees.client.application.users;

import java.util.List;

import com.arcbees.client.api.RestCallbackImpl;
import com.arcbees.client.api.User;
import com.arcbees.client.api.UserApi;
import com.arcbees.client.place.NameTokens;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.presenter.slots.NestedSlot;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

public class UsersPresenter extends Presenter<UsersPresenter.MyView, UsersPresenter.MyProxy>
        implements UsersViewUiHandlers {
    interface MyView extends View, HasUiHandlers<UsersViewUiHandlers> {
        void displayUsers(List<User> users);
    }

    @ProxyStandard
    @NameToken(NameTokens.USERS)
    interface MyProxy extends ProxyPlace<UsersPresenter> {
    }

    public static final NestedSlot SLOT_MAIN = new NestedSlot();

    private final ResourceDelegate<UserApi> userApiResourceDelegate;

    @Inject
    UsersPresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy,
            ResourceDelegate<UserApi> userApiResourceDelegate) {
        super(eventBus, view, proxy, RevealType.Root);

        this.userApiResourceDelegate = userApiResourceDelegate;

        getView().setUiHandlers(this);
    }

    @Override
    public void prepareFromRequest(PlaceRequest request) {
        userApiResourceDelegate.withCallback(new RestCallbackImpl<List<User>>() {
            @Override
            public void onSuccess(List<User> users) {
                getView().displayUsers(users);
            }
        }).getUsers();
    }

    @Override
    public void deleteUser(int userId) {
        userApiResourceDelegate.withCallback(new RestCallbackImpl<List<User>>() {
            @Override
            public void onSuccess(List<User> users) {
                getView().displayUsers(users);
            }
        }).delete(userId);
    }
}
