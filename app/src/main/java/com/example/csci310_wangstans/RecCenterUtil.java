package com.example.csci310_wangstans;

import android.content.SharedPreferences;

public class RecCenterUtil {
    public RecCenterUtil() { }

    public boolean userInRes(String userId, Booking booking, SharedPreferences sharedBookings) {
        String[] resInfo = parseResInfo(booking, sharedBookings);
        if (resInfo.length < 5) {
            return false;
        }

        for (int i=0; i<resInfo.length; i++) {
            System.out.println(resInfo[i]);
            if (resInfo[i].equals(userId)) return true;
        }

        return false;
    }

    public String[] parseResInfo(Booking booking, SharedPreferences sharedBookings) {
        return sharedBookings.getString(booking.getResId(), "").split(",");
    }

    protected void addToWaitlist(String userId, Booking booking, SharedPreferences sp, SharedPreferences.Editor editor) {
        String resId = booking.getResId();
        if (sp.contains(resId)) {
            editor.putString(resId, sp.getString(resId, "") + "," + userId);
        }
        else {
            editor.putString(resId, userId);
        }
        editor.apply();
        booking.addToWaitlist(userId);
    }

}
