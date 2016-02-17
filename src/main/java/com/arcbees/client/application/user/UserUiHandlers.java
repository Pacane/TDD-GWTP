package com.arcbees.client.application.user;

import com.arcbees.client.api.User;
import com.gwtplatform.mvp.client.UiHandlers;

public interface UserUiHandlers extends UiHandlers {
    void saveUser(User user);
}
