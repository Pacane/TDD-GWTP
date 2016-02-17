package com.arcbees.client.application.users;

import java.util.ArrayList;
import java.util.List;

import org.jukito.JukitoModule;
import org.jukito.JukitoRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.arcbees.client.api.User;
import com.arcbees.client.api.UserApi;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.AutobindDisable;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import static org.mockito.Matchers.same;
import static org.mockito.Mockito.verify;

import static com.gwtplatform.dispatch.rest.delegates.test.DelegateTestUtils.givenDelegate;

@RunWith(JukitoRunner.class)
public class UsersPresenterTest {
    public static class Module extends JukitoModule {
        @Override
        protected void configureTest() {
            bind(AutobindDisable.class).toInstance(new AutobindDisable(true));
        }
    }

    private static final int USER_ID = 32131;

    @Inject
    private UsersPresenter presenter;
    @Inject
    private UsersPresenter.MyView view;
    @Inject
    ResourceDelegate<UserApi> userApiResourceDelegate;
    @Inject
    private UserApi userApi;

    private List<User> users = new ArrayList<>();

    @Before
    public void setUp() {
        givenDelegate(userApiResourceDelegate).useResource(userApi);
    }

    @Test
    public void prepareFromRequest_displaysUsers_onSuccess() {
        PlaceRequest placeRequest = new PlaceRequest();
        givenDelegate(userApiResourceDelegate)
                .succeed().withResult(users)
                .when().getUsers();

        presenter.prepareFromRequest(placeRequest);

        verify(view).displayUsers(users);
    }

    @Test
    public void ctor_setsUiHandlers() {
        verify(view).setUiHandlers(same(presenter));
    }

    @Test
    public void deleteUser_delegatesToService() {
        presenter.deleteUser(USER_ID);

        verify(userApi).delete(USER_ID);
    }

    @Test
    public void deleteUser_displaysUsers_onSuccess() {
        givenDelegate(userApiResourceDelegate)
                .succeed().withResult(users)
                .when().delete(USER_ID);

        presenter.deleteUser(USER_ID);

        verify(view).displayUsers(same(users));
    }
}
