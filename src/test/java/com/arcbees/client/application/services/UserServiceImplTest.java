package com.arcbees.client.application.services;

import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.arcbees.client.application.events.UserDeletedEvent;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

import static org.mockito.Matchers.isA;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.verify;

@RunWith(JukitoRunner.class)
public class UserServiceImplTest {
    private static final int USER_ID = 4566;

    @Inject
    private EventBus eventBus;
    @Inject
    private UserServiceImpl userService;

    @Test
    public void deleteUser_firesEvent() {
        userService.deleteUser(USER_ID);

        verify(eventBus).fireEventFromSource(isA(UserDeletedEvent.class), same(userService));
    }
}
