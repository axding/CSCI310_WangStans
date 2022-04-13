package com.example.csci310_wangstans;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.test.core.app.ActivityScenario;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

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