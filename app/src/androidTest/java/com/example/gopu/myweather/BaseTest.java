package com.example.gopu.myweather;

import org.junit.After;
import org.junit.Before;
import org.mockito.MockitoAnnotations;

/**
 * Created by dasaratharamireddygopu on 11/11/17.
 */

public class BaseTest {

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void clear() {}
}
