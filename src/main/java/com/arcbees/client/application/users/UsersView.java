package com.arcbees.client.application.users;

import java.util.Map;

import com.arcbees.client.place.NameTokens;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.gwtplatform.mvp.shared.proxy.TokenFormatter;

public class UsersView extends ViewImpl implements UsersPresenter.MyView {
    interface Binder extends UiBinder<Widget, UsersView> {
    }

    @UiField
    HTMLPanel usersList;
    @UiField
    HTMLPanel root;

    private final TokenFormatter tokenFormatter;

    @Inject
    public UsersView(
            Binder uiBinder,
            TokenFormatter tokenFormatter) {
        this.tokenFormatter = tokenFormatter;

        initWidget(uiBinder.createAndBindUi(this));

        bindSlot(UsersPresenter.SLOT_MAIN, root);
    }

    @Override
    public void displayUsers(Map<Integer, String> users) {
        usersList.clear();

        for (Integer userId : users.keySet()) {
            Anchor linkToEdit = new Anchor();

            linkToEdit.setText(users.get(userId));

            PlaceRequest placeToGo = new PlaceRequest.Builder()
                    .nameToken(NameTokens.USER)
                    .with(NameTokens.PARAM_ID, String.valueOf(userId)).build();
            linkToEdit.setHref("#" + tokenFormatter.toPlaceToken(placeToGo));

            usersList.add(linkToEdit);
            usersList.add(new HTML("<br/>"));
        }
    }
}
