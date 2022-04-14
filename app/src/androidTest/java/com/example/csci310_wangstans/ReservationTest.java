package com.example.csci310_wangstans;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ReservationTest {

    @Rule
    public ActivityScenarioRule<ReservationActivity> mActivityRule = new ActivityScenarioRule<>(ReservationActivity.class);

    @Test
    public void ensurePastReservationsEmpty() {}

    @Test
    public void ensureUpcomingReservationsEmpty() {}

    @Test
    public void ensurePastReservationsExists() {}

    @Test
    public void ensureUpcomingReservationsExists() {}

    @Test
    public void ensureCancelUpcomingReservations() {}

}
