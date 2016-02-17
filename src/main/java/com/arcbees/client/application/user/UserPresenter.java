package com.arcbees.client.application.user;

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
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

public class UserPresenter
        extends Presenter<UserPresenter.MyView, UserPresenter.MyProxy>
        implements UserUiHandlers {
    interface MyView extends View, HasUiHandlers<UserUiHandlers> {
        void displayUsername(String username);

        void displayUser(User user);
    }

    @ProxyStandard
    @NameToken(NameTokens.USER)
    interface MyProxy extends ProxyPlace<UserPresenter> {
    }

    public static final NestedSlot SLOT_MAIN = new NestedSlot();

    private final PlaceManager placeManager;
    private final ResourceDelegate<UserApi> userApiResourceDelegate;

    @Inject
    UserPresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy,
            PlaceManager placeManager,
            ResourceDelegate<UserApi> userApiResourceDelegate) {
        super(eventBus, view, proxy, RevealType.Root);

        this.placeManager = placeManager;
        this.userApiResourceDelegate = userApiResourceDelegate;

        getView().setUiHandlers(this);
    }

    @Override
    public void prepareFromRequest(PlaceRequest request) {
        int userId = parseUserIdFromPlaceRequest(request);

        userApiResourceDelegate.withCallback(new RestCallbackImpl<User>() {
            @Override
            public void onSuccess(User user) {
                getView().displayUser(user);
            }
        }).getUser(userId);
    }

    private int parseUserIdFromPlaceRequest(PlaceRequest placeRequest) {
        String idParameter = placeRequest.getParameter(NameTokens.PARAM_ID, "");
        return Integer.parseInt(idParameter);
    }

    @Override
    public void saveUser(User user) {
        userApiResourceDelegate.withCallback(new RestCallbackImpl<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                PlaceRequest usersListPlace = new PlaceRequest.Builder()
                        .nameToken(NameTokens.USERS)
                        .build();

                placeManager.revealPlace(usersListPlace);
            }
        }).saveUser(user.getId(), user);
    }
}
