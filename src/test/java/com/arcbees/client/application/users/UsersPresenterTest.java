package com.arcbees.client.application.users;

import java.util.HashMap;
import java.util.Map;

import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.arcbees.client.application.services.UserService;
import com.google.inject.Inject;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.verify;

@RunWith(JukitoRunner.class)
public class UsersPresenterTest {
    @Inject
    private UsersPresenter presenter;
    @Inject
    private UserService userService;
    @Inject
    private UsersPresenter.MyView view;

    @Test
    public void prepareFromRequest_displaysUsers() {
        PlaceRequest placeRequest = new PlaceRequest();
        Map<Integer, String> users = new HashMap<>();
        given(userService.getUsers()).willReturn(users);

        presenter.prepareFromRequest(placeRequest);

        verify(view).displayUsers(same(users));
    }
}
