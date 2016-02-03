package com.arcbees.client.application;

import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.arcbees.client.application.services.UserService;
import com.google.inject.Inject;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.same;
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

    @Test
    public void ctor_setsUiHandlers() {
        verify(view).setUiHandlers(same(presenter));
    }

    @Test
    public void saveUsername_delegatesToService() {
        presenter.saveUsername(A_USERNAME);

        verify(userService).saveUsername(A_USERNAME);
    }

    @Test
    public void saveUsername_displaysNewUsernameInView() {
        given(userService.getUsername()).willReturn(A_USERNAME);

        presenter.saveUsername(A_USERNAME);

        verify(view).displayUsername(A_USERNAME);
    }
}
