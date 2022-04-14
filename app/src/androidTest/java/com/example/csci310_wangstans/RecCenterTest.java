package com.example.csci310_wangstans;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class RecCenterTest {

    @Rule
    public ActivityScenarioRule<VRecActivity> mActivityRule = new ActivityScenarioRule<>(VRecActivity.class);

    @Test
    public void ensureDateBookingsShow() {

    }

    @Test
    public void ensureBookingReservations() {}

    @Test
    public void ensureRemindMe() {}

    @Test
    public void ensureBookingReservationStays() {}
}
