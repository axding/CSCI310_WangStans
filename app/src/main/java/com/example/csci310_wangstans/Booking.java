package com.example.csci310_wangstans;

import java.util.Arrays;

public class Booking {
    private String resId;
    private String startTime;
    private String endTime;
    private String[] users;
    private int numUsers;
    private String[] waitlist;

    public Booking(String[] resInfo) {
        this.resId = resInfo[0];
        this.startTime = parseTime(resInfo[1]);
        this.endTime = parseTime(resInfo[2]);
        this.numUsers = resInfo.length - 4;
        if (numUsers > 0) {
            this.users = Arrays.copyOfRange(resInfo, 4, resInfo.length);
        }
        else {
            this.users = new String[0];
        }
        this.waitlist = new String[0];
    }

    private String parseTime(String time) {
        int hour = Integer.parseInt(time.substring(0,2));
        int convertedHour = ((hour % 12) != 0) ? hour % 12 : 0;
        String period = "AM";
        if (hour > 11) {
            period = "PM";
        }
        return convertedHour + ":" + time.substring(2) + " " + period;
    }

    public String getResId() {
        return resId;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public int getNumUsers() {
        return numUsers;
    }

    public String[] getUsers() {
        return users;
    }

    public String[] getWaitlist() {
        return waitlist;
    }
}
