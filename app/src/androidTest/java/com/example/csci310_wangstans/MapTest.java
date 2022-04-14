package com.example.csci310_wangstans;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.StringStartsWith.*;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MapTest {

    @Rule
    public ActivityScenarioRule<MapActivity> mActivityRule = new ActivityScenarioRule<>(MapActivity.class);

    @Test
    public void ensureNoUpcomingReservations() {
        onView(withId(R.id.upcomingReservations)).check(matches(withText(startsWith("No"))));
    }

/*
    @Test
    public void ensureUpcomingReservations() {
        onView(withId(R.id.upcomingReservations)).check(matches(withText(startsWith("Upcoming"))));
    }
 */
}
