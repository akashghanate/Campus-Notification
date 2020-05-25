package com.example.abhinandan.onlinedb;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.*;

public class MainActivityTestIntent {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    public MainActivity mainActivity = null;

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(Studentlayout.class.getName(),null,false);

    @Before
    public void setUp() throws Exception {

        mainActivity = mainActivityActivityTestRule.getActivity();
    }

    @Test
    public void testlaunch(){

        assertNotNull(mainActivity.findViewById(R.id.btnlogin));
        assertNotNull(mainActivity.findViewById(R.id.etUsername));

    }

    @Test
    public void onlogin() {
        Activity activity = getInstrumentation().waitForMonitorWithTimeout(monitor,100000);
        assertNotNull(activity);
    }




    @After
    public void tearDown() throws Exception {

        mainActivity = null;
    }

}