package com.example.csci310_wangstans;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.runner.RunWith;
import org.junit.Rule;
import org.junit.Test;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
@RunWith(AndroidJUnit4.class)
public class LoginFragmentTest {

//    @Rule
//    public ActivityScenarioRule<MainActivity> mActivityRule = new ActivityScenarioRule<>(MainActivity.class);
    @Rule
    public ActivityScenarioRule<MainActivity> mActivityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void ensureIncorrectPassword() {
        // Type text and then press the button.
        onView(withId(R.id.editUserName))
                .perform(typeText("asdfghjkl"), closeSoftKeyboard());

        onView(withId(R.id.editPassword))
                .perform(typeText("asdfghjkl"), closeSoftKeyboard());

        onView(withId(R.id.buttonLogin)).perform(click());

        onView(withId(R.id.fragment_login)).check(matches(isDisplayed()));
    }

    @Test
    public void ensureCorrectPassword() {
        // Should log in as it corresponds to 0th entry (dummy data) in databsse
        onView(withId(R.id.editUserName))
                .perform(typeText("student"), closeSoftKeyboard());

        onView(withId(R.id.editPassword))
                .perform(typeText("student"), closeSoftKeyboard());

        onView(withId(R.id.buttonLogin)).perform(click());

        onView(withId(R.id.map_home_page)).check(matches(isDisplayed()));
    }
}