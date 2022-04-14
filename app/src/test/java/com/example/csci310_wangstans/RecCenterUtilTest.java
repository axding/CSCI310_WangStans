package com.example.csci310_wangstans;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.content.Context;
import android.content.SharedPreferences;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class RecCenterUtilTest {

    RecCenterUtil util = new RecCenterUtil();

    @Mock
    SharedPreferences sharedBooking = Mockito.mock(SharedPreferences.class);

    @Mock
    SharedPreferences.Editor sharedBookingEditor = Mockito.mock(SharedPreferences.Editor.class);

    @Mock
    Booking booking = Mockito.mock(Booking.class);

    @Test
    public void parseResInfo_isCorrect() {

        when( booking.getResId() ).thenReturn("c1");
        when( sharedBooking.getString("c1", "") ).thenReturn("c1,2100,2150,3-23-2022,1");

        String[] expected = new String[] {"c1", "2100", "2150", "3-23-2022", "1"};
        Assert.assertArrayEquals(expected, util.parseResInfo(booking, sharedBooking));
    }

    @Test
    public void userInRes_isFalse() {

        when( booking.getResId() ).thenReturn("c1");
        when( sharedBooking.getString("c1", "") ).thenReturn("c1,2100,2150,3-23-2022");

        Assert.assertEquals(false, util.userInRes("1", booking, sharedBooking));
    }

    @Test
    public void userInRes_isTrue() {

        when( booking.getResId() ).thenReturn("c1");
        when( sharedBooking.getString("c1", "") ).thenReturn("c1,2100,2150,3-23-2022,1");

        Assert.assertEquals(true, util.userInRes("1", booking, sharedBooking));
    }

    @Test
    public void addToWaitlist_WaitlistIsCreated() {
        when( booking.getResId() ).thenReturn("c1");
        when( sharedBooking.contains("c1") ).thenReturn(false);
        when( sharedBookingEditor.putString("c1", "1") ).thenReturn(sharedBookingEditor);
        doNothing().when(booking).addToWaitlist("1");
        doNothing().when(sharedBookingEditor).apply();

        util.addToWaitlist("1", booking, sharedBooking, sharedBookingEditor);
        verify(sharedBookingEditor,times(1)).apply();
        verify(booking,times(1)).addToWaitlist("1");
    }

    @Test
    public void addToWaitlist_UserIsAppended() {
        when( booking.getResId() ).thenReturn("c1");
        when( sharedBooking.contains("c1") ).thenReturn(true);
        when( sharedBooking.getString("1", "")).thenReturn("booking");
        when( sharedBookingEditor.putString("c1", "booking,1") ).thenReturn(sharedBookingEditor);
        doNothing().when(booking).addToWaitlist("1");

        util.addToWaitlist("1", booking, sharedBooking, sharedBookingEditor);
        verify(booking,times(1)).addToWaitlist("1");
    }

}