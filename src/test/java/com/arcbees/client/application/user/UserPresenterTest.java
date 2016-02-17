package com.arcbees.client.application.user;

import org.jukito.JukitoRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.arcbees.client.api.User;
import com.arcbees.client.api.UserApi;
import com.arcbees.client.place.NameTokens;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.verify;

import static com.gwtplatform.dispatch.rest.delegates.test.DelegateTestUtils.givenDelegate;

@RunWith(JukitoRunner.class)
public class UserPresenterTest {
    private static final String A_USERNAME = "bobby";
    private static final int USER_ID = 23423890;

    @Inject
    private UserPresenter presenter;
    @Inject
    private UserPresenter.MyView view;
    @Inject
    private PlaceManager placeManager;
    @Inject
    private ResourceDelegate<UserApi> userApiResourceDelegate;
    @Inject
    private UserApi userApi;

    @Before
    public void setUp() {
        givenDelegate(userApiResourceDelegate).useResource(userApi);
    }

    @Test
    public void prepareFromRequest_displayUsername() {
        PlaceRequest placeRequest = createPlaceRequestWithUserId(USER_ID);
        User user = createUser(USER_ID, A_USERNAME);
        givenDelegate(userApiResourceDelegate)
                .succeed().withResult(user)
                .when().getUser(USER_ID);

        presenter.prepareFromRequest(placeRequest);

        verify(view).displayUsername(A_USERNAME);
    }

    private PlaceRequest createPlaceRequestWithUserId(int userId) {
        return new PlaceRequest.Builder()
                .with(NameTokens.PARAM_ID, String.valueOf(userId))
                .build();
    }

    private User createUser(int userId, String aUsername) {
        User user = new User();
        user.setId(userId);
        user.setName(aUsername);
        return user;
    }

    @Test
    public void ctor_setsUiHandlers() {
        verify(view).setUiHandlers(same(presenter));
    }

    @Test
    public void saveUsername_delegatesToService() {
        User user = createUser(USER_ID, A_USERNAME);
        givenDelegate(userApiResourceDelegate)
                .succeed().withResult(null)
                .when().saveUser(eq(USER_ID), same(user));
        PlaceRequest placeRequestToGoAfterSave = new PlaceRequest.Builder()
                .nameToken(NameTokens.USERS).build();

        presenter.saveUser(user);

        verify(placeManager).revealPlace(placeRequestToGoAfterSave);
    }
}
