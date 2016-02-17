package com.arcbees.client.application.user;

import javax.inject.Inject;

import com.arcbees.client.api.User;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class UserView extends ViewWithUiHandlers<UserUiHandlers>
        implements UserPresenter.MyView {
    interface Binder extends UiBinder<Widget, UserView> {
    }

    @UiField
    HTMLPanel main;
    @UiField
    Label username;
    @UiField
    Button saveUsername;
    @UiField
    TextBox newName;

    private User user;

    @Inject
    UserView(
            Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));

        bindSlot(UserPresenter.SLOT_MAIN, main);
    }

    @Override
    public void displayUsername(String username) {
        this.username.setText(username);
    }

    @Override
    public void displayUser(User user) {
        this.user = user;
        this.username.setText(user.getName());
    }

    @UiHandler("saveUsername")
    void onSaveClick(ClickEvent e) {
        user.setName(newName.getText());
        getUiHandlers().saveUser(user);
    }
}
