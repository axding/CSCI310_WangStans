package com.example.csci310_wangstans;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.StringStartsWith.startsWith;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ReservationTest {

    @Rule
    public ActivityScenarioRule<ReservationActivity> mActivityRule = new ActivityScenarioRule<>(ReservationActivity.class);

    //use user with no past or upcoming -1
    @Test
    public void ensurePastReservationsEmpty() {

        onView(withId(R.id.idto1r)).perform(click());
        onView(withId(R.id.pastResButton)).perform(click());

        onView(withId(R.id.pastResButton)).check(matches(withText(startsWith("No"))));
    }

    @Test
    public void ensureUpcomingReservationsEmpty() {
        onView(withId(R.id.idto1r)).perform(click());
        onView(withId(R.id.comingResButton)).perform(click());

        onView(withId(R.id.comingResButton)).check(matches(withText(startsWith("No"))));
    }


    //-2
    @Test
    public void ensurePastReservationsExists() {
        onView(withId(R.id.idto2r)).perform(click());

        onView(withId(R.id.pastResButton)).perform(click());

        onView(withId(R.id.pastResButton)).check(matches(withText(startsWith("Past"))));
    }

    @Test
    public void ensureUpcomingReservationsExists() {
        onView(withId(R.id.idto2r)).perform(click());

        onView(withId(R.id.comingResButton)).perform(click());
        onView(withId(R.id.comingResButton)).check(matches(withText(startsWith("Upcoming"))));
    }

//
//    @Test
//    public void ensureCancelUpcomingReservations() {
//
//    }

}
