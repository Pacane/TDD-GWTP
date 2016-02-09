package com.arcbees.client.gin;

import com.arcbees.client.application.services.UserService;
import com.arcbees.client.application.services.UserServiceImpl;
import com.arcbees.client.application.user.UserModule;
import com.arcbees.client.application.users.UsersModule;
import com.arcbees.client.place.NameTokens;
import com.arcbees.client.resources.ResourceLoader;
import com.gwtplatform.mvp.client.annotations.DefaultPlace;
import com.gwtplatform.mvp.client.annotations.ErrorPlace;
import com.gwtplatform.mvp.client.annotations.UnauthorizedPlace;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import com.gwtplatform.mvp.shared.proxy.RouteTokenFormatter;

public class ClientModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        install(new DefaultModule.Builder().tokenFormatter(RouteTokenFormatter.class).build());
        install(new UserModule());
        install(new UsersModule());

        bind(ResourceLoader.class).asEagerSingleton();
        bind(UserService.class).to(UserServiceImpl.class).asEagerSingleton();

        // DefaultPlaceManager Places
        bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.USERS);
        bindConstant().annotatedWith(ErrorPlace.class).to(NameTokens.USERS);
        bindConstant().annotatedWith(UnauthorizedPlace.class).to(NameTokens.USERS);
    }
}
