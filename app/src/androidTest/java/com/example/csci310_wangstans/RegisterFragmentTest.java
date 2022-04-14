package com.example.csci310_wangstans;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Root;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.Rule;
import org.junit.Test;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static org.hamcrest.Matchers.not;

import android.view.View;

@RunWith(AndroidJUnit4.class)
public class RegisterFragmentTest {

    @Rule
    public ActivityScenarioRule<RegisterActivity> mActivityRule = new ActivityScenarioRule<>(RegisterActivity.class);
    private View decorView;

    @Before
    public void setUp() {
        mActivityRule.getScenario().onActivity(new ActivityScenario.ActivityAction<RegisterActivity>() {
            @Override
            public void perform(RegisterActivity activity) {
                decorView = activity.getWindow().getDecorView();
            }
        });
    }


    @Test
    public void ensureCorrectRegister() {
        // Type text and then press the button.
        onView(withId(R.id.editUserName))
                .perform(typeText("matthewcho"), closeSoftKeyboard());
        onView(withId(R.id.editEmail))
                .perform(typeText("mcho9ddddddd434@usc.edu"), closeSoftKeyboard());
        onView(withId(R.id.editName))
                .perform(typeText("Matthew Cho"), closeSoftKeyboard());
        onView(withId(R.id.editPassword))
                .perform(typeText("student"), closeSoftKeyboard());

        //onView(withId(R.id.buttonRegister)).perform(click());
        onView(withId(R.id.buttonRegister)).perform(click());

//        onView(withId(R.id.fragment_register)).check(matches(isDisplayed()));
        //onView(withId(R.id.editName)).check(matches(withText("Matthew Cho")));
        //onView(withText("Registered")).inRoot(withDecorView(not(mActivityRule.getWindow().getDecorView()))).check(matches(isDisplayed()));
        onView(withText("Registered"))
                .inRoot(withDecorView(not(decorView)))// Here we use decorView
                .check(matches(isDisplayed()));
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
//public class ToastMatcher extends TypeSafeMatcher<Root> {
//
//    @Override    public void describeTo(Description description) {
//        description.appendText("is toast");
//    }
//
//    @Override    public boolean matchesSafely(Root root) {
//        int type = root.getWindowLayoutParams().get().type;
//        if ((type == WindowManager.LayoutParams.TYPE_TOAST)) {
//            IBinder windowToken = root.getDecorView().getWindowToken();
//            IBinder appToken = root.getDecorView().getApplicationWindowToken();
//            if (windowToken == appToken) {
//                //means this window isn't contained by any other windows.
//                return true;
//            }
//        }
//        return false;
//    }