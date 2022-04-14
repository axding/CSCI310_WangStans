package com.example.csci310_wangstans;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import android.content.Context;
import android.content.SharedPreferences;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Vector;

public class MapHomePageTest {

    @Mock
    MapHomePage fragment = Mockito.mock(MapHomePage.class);

    @Mock
    SharedPreferences sharedPreferences = Mockito.mock(SharedPreferences.class);

    @Mock
    SharedPreferences.Editor sharedPreferencesEditor = Mockito.mock(SharedPreferences.Editor.class);

    @Mock
    Context context = Mockito.mock(Context.class);

//    @Mock
//    Reservation res1;
//
//    @Mock
//    Reservation res2;

    @Test
    public void populateRes_isFalse() {

        when( fragment.getContext() ).thenReturn(context);
        when( context.getSharedPreferences("sharedBooking", Context.MODE_PRIVATE) ).thenReturn(sharedPreferences);
        when( sharedPreferences.getString("c0", "none") ).thenReturn("none");

        Assert.assertEquals(false, fragment.isPopulated());
    }

//    @Test
//    public void populateRes_isTrue() {
//
//        when( fragment.getContext() ).thenReturn(context);
//        when( context.getSharedPreferences("sharedBooking", Context.MODE_PRIVATE) ).thenReturn(sharedPreferences);
//        when( sharedPreferences.edit() ).thenReturn(sharedPreferencesEditor);
//        doNothing().when( sharedPreferencesEditor.putString())
//        when( sharedPreferences.getString("c0", "none") ).thenReturn("data");
//
//
//        Assert.assertEquals(true, fragment.isPopulated());
//    }
//
//    @Test
//    public void upcomingEvents_isCorrect() {
//
//        when( fragment.getContext() ).thenReturn(context);
//        when( context.getSharedPreferences("usersFile", Context.MODE_PRIVATE) ).thenReturn(sharedPreferences);
//        when( sharedPreferences.getInt("currentUser",1) ).thenReturn(1);
//        when( sharedPreferences.getString("1", "none") ).thenReturn("dummy,Tommy,tommy@usc.edu,pass");
////        res1 = mock(Reservation.class);
////        when( new Reservation("c1", context)).thenReturn(res1);
////        res2 = mock(Reservation.class);
////        when( new Reservation("c2", context)).thenReturn(res2);
////        SharedPreferences sharedBooking = Mockito.mock(SharedPreferences.class);
////        when( context.getSharedPreferences("sharedBooking", Context.MODE_PRIVATE) ).thenReturn(sharedBooking);
////        when( sharedBooking.getString("c1", "no res")).thenReturn("c1,1900,1950,3-30-2022,1");
////        when( sharedBooking.getString("c2", "no res")).thenReturn("c2,1900,1950,3-30-2022,1");
//
////        Vector<Reservation> expectedRes = new Vector<>();
////        expectedRes.add(res1);
////        expectedRes.add(res2);
//
////        fragment.getUpcomingEvents();
//        Assert.assertEquals(0, fragment.comingRes.size());
////        Assert.assertEquals("c2", fragment.getComingRes().get(1).getResEnc());
//    }

}