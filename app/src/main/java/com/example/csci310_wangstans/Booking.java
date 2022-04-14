package com.example.csci310_wangstans;

import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;

public class Booking {
    private String resId;
    private String startTime;
    private String endTime;
    private Vector<String> users;
    private int numUsers;
    private Vector<String> waitlist;

    public Booking(String[] resInfo) {
        this.resId = resInfo[0];
        this.startTime = parseTime(resInfo[1]);
        this.endTime = parseTime(resInfo[2]);
        this.numUsers = resInfo.length - 4;
        this.users = new Vector<>();
        if (numUsers > 0) {
            Collections.addAll(this.users, Arrays.copyOfRange(resInfo, 4, resInfo.length));
        }
        this.waitlist = new Vector<>();
    }

    private String parseTime(String time) {
        int hour = Integer.parseInt(time.substring(0,2));
        int convertedHour = ((hour % 12) != 0) ? hour % 12 : 12;
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

    public Vector<String> getUsers() {
        return users;
    }

    public Vector<String> getWaitlist() {
        return waitlist;
    }

    public void addToWaitlist(String userId) {
        waitlist.add(userId);
    }
}
