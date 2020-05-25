package com.example.abhinandan.onlinedb;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    public MainActivity mainActivity = null;

    @Before
    public void setUp() throws Exception {
        mainActivity = mainActivityActivityTestRule.getActivity();
    }

    @Test
    public void testrun(){

        View view1 = mainActivity.findViewById(R.id.radiogroup);
        View view2 = mainActivity.findViewById(R.id.btnlogin);

        assertNotNull(view1);
        assertNotNull(view2);
    }

    @After
    public void tearDown() throws Exception {
        mainActivity = null;

    }
}