package com.example.abhinandan.onlinedb.fragments;

import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.NavigationViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import com.example.abhinandan.onlinedb.Popupimage;
import com.example.abhinandan.onlinedb.R;
import com.example.abhinandan.onlinedb.Studentlayout;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class aboutTest {
    @Rule
    public ActivityTestRule<Studentlayout> studentlayoutActivityTestRule = new ActivityTestRule<>(Studentlayout.class);


    public  Studentlayout studentlayout = null;


    @Before
    public void setUp() throws Exception {
        studentlayout = studentlayoutActivityTestRule.getActivity();
    }

    @Test
    public void testrun(){

        FrameLayout frameLayout = studentlayout.findViewById(R.id.fragment_container);
        assertNotNull(frameLayout);

        DrawerLayout drawerLayout = studentlayout.findViewById(R.id.drawer_layout_student);
        assertNotNull(drawerLayout);

        onView(withId(R.id.drawer_layout_student)).check(matches(isClosed(Gravity.LEFT))).perform(DrawerActions.open());

        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_about_student));

        about_student a = new about_student();

        studentlayout.getSupportFragmentManager().beginTransaction().replace(frameLayout.getId(),a).commitAllowingStateLoss();

        getInstrumentation().waitForIdleSync();

        View view = a.getView().findViewById(R.id.tvline1);

        assertNotNull(view);

    }

    @After
    public void tearDown() throws Exception {
        studentlayout = null;

    }

}