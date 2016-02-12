package com.arcbees.client.application.users;

import java.util.HashMap;
import java.util.Map;

import org.jukito.JukitoModule;
import org.jukito.JukitoRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.arcbees.client.application.events.UserDeletedEvent;
import com.arcbees.client.application.services.UserService;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.AutobindDisable;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.verify;

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
    private UserService userService;
    @Inject
    private UsersPresenter.MyView view;
    @Inject
    private EventBus eventBus;
    private Map<Integer, String> users = new HashMap<>();

    @Before
    public void setUp() {
        given(userService.getUsers()).willReturn(users);
    }

    @Test
    public void prepareFromRequest_displaysUsers() {
        PlaceRequest placeRequest = new PlaceRequest();

        presenter.prepareFromRequest(placeRequest);

        verify(view).displayUsers(same(users));
    }

    @Test
    public void ctor_setsUiHandlers() {
        verify(view).setUiHandlers(same(presenter));
    }

    @Test
    public void onBind_addsHandlers() {
        presenter.onBind();

        verify(eventBus).addHandler(eq(UserDeletedEvent.TYPE), same(presenter));
    }

    @Test
    public void deleteUser_delegatesToService() {
        presenter.deleteUser(USER_ID);

        verify(userService).deleteUser(USER_ID);
    }

    @Test
    public void onUserDeleted_displaysUsers() {
        UserDeletedEvent event = new UserDeletedEvent();

        presenter.onUserDeleted(event);

        verify(view).displayUsers(same(users));
    }
}
