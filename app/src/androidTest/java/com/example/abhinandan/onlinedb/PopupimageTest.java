package com.example.abhinandan.onlinedb;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class PopupimageTest {

    @Rule
    public ActivityTestRule<Popupimage> popupimageActivityTestRule = new ActivityTestRule<>(Popupimage.class);

    public  Popupimage popupimage = null;


    @Before
    public void setUp() throws Exception {
        popupimage = popupimageActivityTestRule.getActivity();
    }

    @Test
    public void testrun(){

        View view1 = popupimage.findViewById(R.id.enlarge_image);

        assertNotNull(view1);
        onView(withId(R.id.enlarge_image)).perform(click());
    }

    @After
    public void tearDown() throws Exception {
        popupimage = null;

    }

}