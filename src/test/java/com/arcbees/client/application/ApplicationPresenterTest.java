package com.arcbees.client.application;

import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JukitoRunner.class)
public class ApplicationPresenterTest {
    @Test
    public void test() {
        assertThat(true).isFalse();
    }
}
