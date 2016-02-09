package com.arcbees.client.application.user;

import com.arcbees.client.application.services.UserService;
import com.arcbees.client.place.NameTokens;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
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
    }

    @ProxyStandard
    @NameToken(NameTokens.USER)
    interface MyProxy extends ProxyPlace<UserPresenter> {
    }

    public static final NestedSlot SLOT_MAIN = new NestedSlot();

    private final UserService userService;
    private final PlaceManager placeManager;

    @Inject
    UserPresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy,
            UserService userService,
            PlaceManager placeManager) {
        super(eventBus, view, proxy, RevealType.Root);

        this.userService = userService;
        this.placeManager = placeManager;

        getView().setUiHandlers(this);
    }

    @Override
    public void prepareFromRequest(PlaceRequest request) {
        int userId = parseUserIdFromPlaceRequest(request);
        String username = userService.getUsername(userId);

        getView().displayUsername(username);
    }

    private int parseUserIdFromPlaceRequest(PlaceRequest placeRequest) {
        String idParameter = placeRequest.getParameter(NameTokens.PARAM_ID, "");
        return Integer.parseInt(idParameter);
    }

    @Override
    public void saveUsername(String username) {
        int userId = parseUserIdFromPlaceRequest(placeManager.getCurrentPlaceRequest());

        userService.saveUsername(userId, username);

        String newName = userService.getUsername(userId);
        getView().displayUsername(newName);
    }
}
