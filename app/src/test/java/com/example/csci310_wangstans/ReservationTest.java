package com.example.csci310_wangstans;

import static org.mockito.Mockito.when;

import android.content.Context;
import android.content.SharedPreferences;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Vector;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ReservationTest {

    @Mock
    Context context;

    @Mock
    SharedPreferences sharedPref;

    Reservation reservation;
    String resEncoding;

    @Before
    public void setup() {
        context = Mockito.mock(Context.class);
        sharedPref = Mockito.mock(SharedPreferences.class);
        when( context.getSharedPreferences("sharedBooking", Context.MODE_PRIVATE) ).thenReturn(sharedPref);
    }

    @Test
    public void getDate_isCorrect() {
        resEncoding = "c3";
        when( sharedPref.getString(resEncoding, "no res") ).thenReturn("c3,1900,1950,3-30-2022,1");

        reservation = new Reservation(resEncoding, context);

        Assert.assertEquals( "3-30-2022", reservation.getDate());
    }

    @Test
    public void getStartTime_isCorrect() {
        resEncoding = "c3";
        when( sharedPref.getString(resEncoding, "no res") ).thenReturn("c3,1900,1950,3-30-2022,1");

        reservation = new Reservation(resEncoding, context);

        Assert.assertEquals( "1900", reservation.getStartTime());
    }

    @Test
    public void getEndTime_isCorrect() {
        resEncoding = "c3";
        when( sharedPref.getString(resEncoding, "no res") ).thenReturn("c3,1900,1950,3-30-2022,1");

        reservation = new Reservation(resEncoding, context);

        Assert.assertEquals( "1950", reservation.getEndTime());
    }

    @Test
    public void getResEnc_isCorrect() {
        resEncoding = "c3";
        when( sharedPref.getString(resEncoding, "no res") ).thenReturn("c3,1900,1950,3-30-2022,1");

        reservation = new Reservation(resEncoding, context);

        Assert.assertEquals( "c3", reservation.getResEnc());
    }

    @Test
    public void getLocC_isCorrect() {
        resEncoding = "c3";
        when( sharedPref.getString(resEncoding, "no res") ).thenReturn("c3,1900,1950,3-30-2022,1");

        reservation = new Reservation(resEncoding, context);

        Assert.assertEquals( "Cromwell Track", reservation.getLoc());
    }

    @Test
    public void getLocL_isCorrect() {
        resEncoding = "l3";
        when( sharedPref.getString(resEncoding, "no res") ).thenReturn("l3,1900,1950,3-30-2022,1");

        reservation = new Reservation(resEncoding, context);

        Assert.assertEquals( "Lyon Center", reservation.getLoc());
    }

    @Test
    public void getLocU_isCorrect() {
        resEncoding = "u3";
        when( sharedPref.getString(resEncoding, "no res") ).thenReturn("u3,1900,1950,3-30-2022,1");

        reservation = new Reservation(resEncoding, context);

        Assert.assertEquals( "Swimming Pool", reservation.getLoc());
    }

    @Test
    public void getLocR_isCorrect() {
        resEncoding = "r3";
        when( sharedPref.getString(resEncoding, "no res") ).thenReturn("r3,1900,1950,3-30-2022,1");

        reservation = new Reservation(resEncoding, context);

        Assert.assertEquals( "Racquetball Courts", reservation.getLoc());
    }

    @Test
    public void getLocV_isCorrect() {
        resEncoding = "v3";
        when( sharedPref.getString(resEncoding, "no res") ).thenReturn("v3,1900,1950,3-30-2022,1");

        reservation = new Reservation(resEncoding, context);

        Assert.assertEquals( "Village Gym", reservation.getLoc());
    }

    @Test
    public void getLocH_isCorrect() {
        resEncoding = "h3";
        when( sharedPref.getString(resEncoding, "no res") ).thenReturn("h3,1900,1950,3-30-2022,1");

        reservation = new Reservation(resEncoding, context);

        Assert.assertEquals( "HSC Gym", reservation.getLoc());
    }
}