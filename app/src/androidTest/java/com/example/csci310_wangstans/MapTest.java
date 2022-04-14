package com.example.csci310_wangstans;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.action.ViewActions.click;

import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.StringStartsWith.*;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.sharedHolder;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(AndroidJUnit4.class)
public class MapTest {

    @Rule
    public ActivityScenarioRule<MapActivity> mActivityRule = new ActivityScenarioRule<>(MapActivity.class);

    private SharedPreferences userDB;
    private SharedPreferences.Editor userEditor;

    @Test
    public void ensureNoUpcomingReservations() {


        onView(withId(R.id.idto1)).perform(click());

        onView(withId(R.id.upcomingReservations)).check(matches(withText(startsWith("No"))));
    }

    @Test
    public void ensureUpcomingReservations() {

        onView(withId(R.id.idto2)).perform(click());

        onView(withId(R.id.upcomingReservations)).check(matches(withText(startsWith("Upcoming"))));
    }

}
