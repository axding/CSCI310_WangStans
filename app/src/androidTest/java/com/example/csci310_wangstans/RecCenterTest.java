package com.example.csci310_wangstans;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.view.View;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;

@RunWith(AndroidJUnit4.class)
public class RecCenterTest {

    @Rule
    public ActivityScenarioRule<VRecActivity> mActivityRule = new ActivityScenarioRule<>(VRecActivity.class);

    static Matcher<View> withChildText(final String string) {
        return new BoundedMatcher<View, FrameLayout>(FrameLayout.class) {
            @Override
            public boolean matchesSafely(FrameLayout view) {
                View child = view.getChildAt(0);
                if (child != null && child instanceof TextView) {
                    return ((TextView) child).getText().toString().equals(string);
                }
                return false;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with child text: ");
            }
        };
    }

    public int getYear(DatePicker dp) {
        return dp.getYear();
    }
    public int getMonth(DatePicker dp) {
        return dp.getMonth();
    }
    public int getDay(DatePicker dp) {
        return dp.getDayOfMonth();
    }

    @Test
    public void ensureDateBookingsShow() {
        Calendar today = Calendar.getInstance();

        int currentYear = today.get(Calendar.YEAR);
        int currentMonth = today.get(Calendar.MONTH);
        int currentDay = today.get(Calendar.DAY_OF_MONTH);
        onView(withId(R.id.datePicker)).perform(PickerActions.setDate(currentYear, currentMonth, currentDay));
        onView(withId(R.id.bookingDisplay)).check(matches(withChild(withText("7:00"))));
    }
/*
    @Test
    public void ensureNoDateBookingsShow() {
        Calendar today = Calendar.getInstance();

        int currentYear = today.get(Calendar.YEAR);
        int currentMonth = today.get(Calendar.MONTH);
        int currentDay = today.get(Calendar.DAY_OF_MONTH)+1;
        onView(withId(R.id.datePicker)).perform(PickerActions.setDate(currentYear, currentMonth, currentDay));

    }
    @Test
    public void ensureBookingReservations() {}

    @Test
    public void ensureRemindMe() {}

    @Test
    public void ensureBookingReservationStays() {}*/
}
