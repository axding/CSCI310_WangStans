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
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;

@RunWith(AndroidJUnit4.class)
public class RegisterFragmentTest {

    @Rule
    public ActivityScenarioRule<RegisterActivity> mActivityRule = new ActivityScenarioRule<>(RegisterActivity.class);

    @Test
    public void ensureCorrectRegister() {
        // Type text and then press the button.
        onView(withId(R.id.editUserName))
                .perform(typeText("matthewcho"), closeSoftKeyboard());
        onView(withId(R.id.editEmail))
                .perform(typeText("mcho9434@usc.edu"), closeSoftKeyboard());
        onView(withId(R.id.editName))
                .perform(typeText("Matthew Cho"), closeSoftKeyboard());
        onView(withId(R.id.editPassword))
                .perform(typeText("student"), closeSoftKeyboard());

//        onView(withId(R.id.buttonRegister)).perform(click());
        onView(withId(R.id.buttonRegister)).perform(click());

        onView(withId(R.id.fragment_register)).check(matches(isDisplayed()));
    }

//
//    @Test
//    public void ensureCorrectRegisterAndLogin() {
//        // Type text and then press the button.
//        onView(withId(R.id.editUserName))
//                .perform(typeText("brennachen"), closeSoftKeyboard());
//        onView(withId(R.id.editName))
//                .perform(typeText("Brenna Chen"), closeSoftKeyboard());
//        onView(withId(R.id.editEmail))
//                .perform(typeText("brennajc@usc.edu"), closeSoftKeyboard());
//        onView(withId(R.id.editPassword))
//                .perform(typeText("student"), closeSoftKeyboard());
//
//        onView(withId(R.id.buttonRegister)).perform(click());
//
//        onView(withId(R.id.fragment_login)).check(matches(isDisplayed()));
//
//        onView(withId(R.id.editUserName))
//                .perform(typeText("brennachen"), closeSoftKeyboard());
//
//        onView(withId(R.id.editPassword))
//                .perform(typeText("student"), closeSoftKeyboard());
//
//        onView(withId(R.id.buttonLogin)).perform(click());
//
//        onView(withId(R.id.map_home_page)).check(matches(isDisplayed()));
//    }
}