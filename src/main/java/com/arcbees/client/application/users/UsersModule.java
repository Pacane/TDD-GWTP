package com.arcbees.client.application.users;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class UsersModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(UsersPresenter.class, UsersPresenter.MyView.class, UsersView.class,
                UsersPresenter.MyProxy.class);
    }
}
