package com.arcbees.client.application;

import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.arcbees.client.application.services.UserService;
import com.google.inject.Inject;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(JukitoRunner.class)
public class ApplicationPresenterTest {
    private static final String A_USERNAME = "bobby";

    @Inject
    private ApplicationPresenter presenter;
    @Inject
    private ApplicationPresenter.MyView view;
    @Inject
    UserService userService;

    @Test
    public void onBind_displayUsername() {
        given(userService.getUsername()).willReturn(A_USERNAME);

        presenter.onBind();

        verify(view).displayUsername(A_USERNAME);
    }
}
