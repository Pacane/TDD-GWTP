package com.arcbees.client.application.user;

import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.arcbees.client.application.services.UserService;
import com.arcbees.client.place.NameTokens;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.verify;

@RunWith(JukitoRunner.class)
public class UserPresenterTest {
    private static final String A_USERNAME = "bobby";
    private static final int USER_ID = 23423890;

    @Inject
    private UserPresenter presenter;
    @Inject
    private UserPresenter.MyView view;
    @Inject
    private UserService userService;
    @Inject
    private PlaceManager placeManager;

    private PlaceRequest createPlaceRequestWithUserId(int userId) {
        return new PlaceRequest.Builder()
                .with(NameTokens.PARAM_ID, String.valueOf(userId))
                .build();
    }

    @Test
    public void prepareFromRequest_displayUsername() {
        given(userService.getUsername(USER_ID)).willReturn(A_USERNAME);
        PlaceRequest placeRequest = createPlaceRequestWithUserId(USER_ID);

        presenter.prepareFromRequest(placeRequest);

        verify(view).displayUsername(A_USERNAME);
    }

    @Test
    public void ctor_setsUiHandlers() {
        verify(view).setUiHandlers(same(presenter));
    }

    @Test
    public void saveUsername_delegatesToService() {
        mockCurrentPlaceRequest(USER_ID);

        presenter.saveUsername(A_USERNAME);

        verify(userService).saveUsername(USER_ID, A_USERNAME);
    }

    @Test
    public void saveUsername_navigatesToUsersPage() {
        mockCurrentPlaceRequest(USER_ID);
        given(userService.getUsername(USER_ID)).willReturn(A_USERNAME);
        PlaceRequest usersPlaceRequest = new PlaceRequest.Builder()
                .nameToken(NameTokens.USERS).build();

        presenter.saveUsername(A_USERNAME);

        verify(placeManager).revealPlace(usersPlaceRequest);
    }

    private void mockCurrentPlaceRequest(int userId) {
        PlaceRequest placeRequest = createPlaceRequestWithUserId(userId);
        given(placeManager.getCurrentPlaceRequest()).willReturn(placeRequest);
    }
}
