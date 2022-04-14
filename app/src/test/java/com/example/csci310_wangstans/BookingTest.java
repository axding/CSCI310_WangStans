package com.example.csci310_wangstans;

import org.junit.Assert;
import org.junit.Test;

import java.util.Vector;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class BookingTest {

    @Test
    public void parseTime_isCorrect() {
        String[] reservation = new String[] {"id", "0000", "1300", "4-5-2022", "2"};
        Booking booking = new Booking(reservation);

        Assert.assertEquals( "12:00 AM", booking.getStartTime());
        Assert.assertEquals( "1:00 PM", booking.getEndTime());
    }

    @Test
    public void parseUsers_isCorrect() {
        String[] reservation = new String[] {"id", "0000", "0100", "4-5-2022", "1"};
        Booking booking = new Booking(reservation);

        Assert.assertEquals( 1, booking.getNumUsers());
        Vector<String> users = new Vector<>();
        users.add("1");
        Assert.assertEquals(users, booking.getUsers());
    }

    @Test
    public void getId_isCorrect() {
        String[] reservation = new String[] {"id", "0000", "0100", "4-5-2022", "1"};
        Booking booking = new Booking(reservation);

        Assert.assertEquals( "id", booking.getResId());
    }

    @Test
    public void getWaitlist_isCorrect() {
        String[] reservation = new String[] {"id", "0000", "0100", "4-5-2022", "1"};
        Booking booking = new Booking(reservation);

        Vector<String> expected = new Vector<>();

        Assert.assertEquals( expected, booking.getWaitlist());
    }

    @Test
    public void addWaitlist_isCorrect() {
        String[] reservation = new String[] {"id", "0000", "0100", "4-5-2022", "1"};
        Booking booking = new Booking(reservation);
        booking.addToWaitlist("1");

        Vector<String> expected = new Vector<>();
        expected.add("1");

        Assert.assertEquals( expected, booking.getWaitlist());
    }
}