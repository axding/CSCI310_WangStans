package com.example.csci310_wangstans;

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
    public void ensureNoUpcomingReservations() {}

    @Test
    public void ensureUpcomingReservations() {}
}
