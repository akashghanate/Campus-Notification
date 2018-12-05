package com.example.abhinandan.onlinedb;

import android.support.design.widget.NavigationView;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.rule.ActivityTestRule;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.View;

import com.example.abhinandan.onlinedb.fragments.about;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class StudentlayoutTest {

    public ActivityTestRule<Studentlayout> studentlayoutActivityTestRule = new ActivityTestRule<>(Studentlayout.class);

    public  Studentlayout studentlayout = null;

    @Before
    public void setUp() throws Exception {

        studentlayout = studentlayoutActivityTestRule.getActivity();
    }

    @Test
    public void testrun(){
        DrawerLayout drawerLayout = studentlayout.findViewById(R.id.drawer_layout_student);
        assertNotNull(drawerLayout);

        NavigationView navigationView = studentlayout.findViewById(R.id.nav_view);
        assertNotNull(navigationView);

        onView(withId(R.id.drawer_layout_student)).perform(DrawerActions.open());


    }

    @Test
    public void onNavigationItemSelected() {

        about a = new about();

        onView(withId(R.id.nav_about_student)).perform(click());

        View view = a.getView().findViewById(R.id.tvline1);
        assertNotNull(view);


    }

    @After
    public void tearDown() throws Exception {

        studentlayout = null;
    }


}