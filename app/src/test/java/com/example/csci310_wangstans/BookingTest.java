package com.example.csci310_wangstans;

import static org.mockito.Mockito.when;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.util.List;
import java.util.Vector;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class BookingTest {

    @Test
    public void parseTime_isCorrect() {
        String[] reservation = new String[] {"id", "0000", "1300", "1", "2"};
        Booking booking = new Booking(reservation);

        Assert.assertEquals( "12:00 AM", booking.getStartTime());
        Assert.assertEquals( "1:00 PM", booking.getEndTime());
    }

    @Test
    public void parseUsers_isCorrect() {
        String[] reservation = new String[] {"id", "0000", "0100", "1", "2"};
        Booking booking = new Booking(reservation);

        Assert.assertEquals( 2, booking.getNumUsers());
        Vector<String> users = new Vector<>();
        users.add("1");
        users.add("2");
        Assert.assertEquals(users, booking.getUsers());
    }

    @Test
    public void getId_isCorrect() {
        String[] reservation = new String[] {"id", "0000", "0100", "1", "2"};
        Booking booking = new Booking(reservation);

        Assert.assertEquals( "id", booking.getResId());
    }
}