package com.example.csci310_wangstans;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.runner.AndroidJUnit4;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MapHomePageTest {

    @Rule
    public ActivityScenarioRule<TestActivity> mActivityTestRule = new ActivityScenarioRule<>(
            MapHomePage.class);

//    private void startFragment( Fragment fragment ) {
//        FragmentManager fragmentManager = mainActivity.getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.add(fragment, null );
//        fragmentTransaction.commit();
//    }

    @Test
    public void testMainActivity() {
    }
}