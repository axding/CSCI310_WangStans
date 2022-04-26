package com.example.csci310_wangstans;

import android.content.Context;
import android.content.SharedPreferences;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.ChronoField;
import java.util.Arrays;

public class Populator {
    private SharedPreferences sharedBookings;
    private SharedPreferences.Editor sharedBookingsEditor;

    private SharedPreferences userDB;
    private SharedPreferences.Editor userEditor;

    public Populator(Context c){
        sharedBookings = c.getSharedPreferences("sharedBooking", Context.MODE_PRIVATE);
        sharedBookingsEditor = sharedBookings.edit();

        userDB = c.getSharedPreferences("usersFile", Context.MODE_PRIVATE);
        userEditor = userDB.edit();

    }

    public void activityUserConfig(){

        //check if user id is 0+

        String current=userDB.getInt("currUser", -4)+"";

        if(Integer.parseInt(current)==-1){
            //finished first test, switch to user -2
            userEditor.putInt("currUser", -2);
            userEditor.apply();
            System.out.println("in1");
        }
        else if(Integer.parseInt(current)>=0 || Integer.parseInt(current)==-2 || Integer.parseInt(current)==-4){//coming from legit login, starting 1st test
            //no upcoming, user -1

            userEditor.putInt("currUser", -1);
            userEditor.apply();
            System.out.println("in2");

        }

    }

    public void setDirect(int user){

        populateUser1();
        populateUser2();

        if(user==-1){
            userEditor.putInt("currUser", -1);
            userEditor.apply();
        }
        else if (user==-2){
            userEditor.putInt("currUser", -2);
            userEditor.apply();
        }

    }

    public void setCurrentUser(int user){
        int potUser= userDB.getInt("currUser", -3);

        populateUser1();
        populateUser2();

        if(potUser==-3){
            if(user==-1){
                userEditor.putInt("currUser", -1);
                userEditor.apply();
            }
            else if (user==-2){
                userEditor.putInt("currUser", -2);
                userEditor.apply();
            }
        }
    }
    public void populateUser1(){
        userEditor.putString("-1", "student,student,student@usc.edu,student,1234567890");
        userEditor.apply();
    }
    public void populateUser2(){
        //    <string name="-1">student,student,student,student</string>
        userEditor.putString("-2", "mcho,mcho,mcho@usc.edu,mcho,0987654321, r98,v98,l98,h98,u98,c98,r99,v99,l99,h99,u99,c99");
        userEditor.apply();
    }
    public static boolean isWeekend(final LocalDate ld)
    {
        DayOfWeek day = DayOfWeek.of(ld.get(ChronoField.DAY_OF_WEEK));
        return day == DayOfWeek.SUNDAY || day == DayOfWeek.SATURDAY;
    }
    public void smartPopulate(){
        System.out.println("smart pop");
        //int month=5;
        int year= Year.now().getValue();
        int[] dateLimits={0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30 ,31};
        if(year%4==0){//account for leap year
            dateLimits[2]++;
        }

        String[] recCenters={"c","u","v","h","l","r"};
        String[] weekdayTimes={"1900,1950","2000,2050","2100,2150"};
        String[] weekendTimes={"1900,2050","2100,2250"};
        //new years
        //mlk
        //christmas
        //thanksgiving
        //420
        //presidents day
        String[] holidays={"1-1-"+year, "1-17-"+year, "4-20-"+year, "2-21-"+year, "3-17-"+year, "5-5-"+year, "5-8-"+year};
        String input="";
        String dateString="";
        int indexCount=0;
        //holidays
        for(int month=1;month<=12;month++){
            for(int day=1;day<=dateLimits[month];day++){

                dateString=month+"-"+day+"-"+year;
                LocalDate someDate = LocalDate.of(year, month, day); // 2nd-Jan-2021

                //weekday will have 1900-1950, 2000-2050, 2100-2150
                //weekend will have 1900-2050, 2100-2250

                if(!(Arrays.asList(holidays).contains(dateString))) {

                    if (isWeekend(someDate)) {//weekend
                        for (int i = 0; i <2; i++) {
                            for (int rec = 0; rec <=5; rec++) {
                                input = recCenters[rec] + indexCount + "," + weekendTimes[i] + "," + dateString;
                                //sharedBookingsEditor.putString("c0", "c0,1900,1950,3-29-2022");
                                System.out.println("adding" + input);
                                sharedBookingsEditor.putString(recCenters[rec] + indexCount, input);

                            }
                            indexCount++;
                        }
                    } else {
                        for (int i = 0; i < 3; i++) {
                            for (int rec = 0; rec <= 5; rec++) {
                                input = recCenters[rec] + indexCount + "," + weekdayTimes[i] + "," + dateString;
                                //sharedBookingsEditor.putString("c0", "c0,1900,1950,3-29-2022");
                                sharedBookingsEditor.putString(recCenters[rec] + indexCount, input);

                            }
                            indexCount++;

                        }

                    }
                }
            }
        }
        sharedBookingsEditor.apply();
        System.out.println(indexCount);
    }

    public void populateRes(){
        System.out.println("Starting..");
        sharedBookingsEditor.putString("c0", "c0,1900,1950,3-29-2022");
        sharedBookingsEditor.putString("u0", "u0,1900,1950,3-29-2022");
        sharedBookingsEditor.putString("l0", "l0,1900,1950,3-29-2022");
        sharedBookingsEditor.putString("v0", "v0,1900,1950,3-29-2022");
        sharedBookingsEditor.putString("h0", "h0,1900,1950,3-29-2022");
        sharedBookingsEditor.putString("r0", "r0,1900,1950,3-29-2022");
        sharedBookingsEditor.putString("c1", "c1,2000,2050,3-29-2022");
        sharedBookingsEditor.putString("u1", "u1,2000,2050,3-29-2022");
        sharedBookingsEditor.putString("l1", "l1,2000,2050,3-29-2022");
        sharedBookingsEditor.putString("v1", "v1,2000,2050,3-29-2022");
        sharedBookingsEditor.putString("h1", "h1,2000,2050,3-29-2022");
        sharedBookingsEditor.putString("r1", "r1,2000,2050,3-29-2022");
        sharedBookingsEditor.putString("c2", "c2,2100,2150,3-29-2022");
        sharedBookingsEditor.putString("u2", "u2,2100,2150,3-29-2022");
        sharedBookingsEditor.putString("l2", "l2,2100,2150,3-29-2022");
        sharedBookingsEditor.putString("v2", "v2,2100,2150,3-29-2022");
        sharedBookingsEditor.putString("h2", "h2,2100,2150,3-29-2022");
        sharedBookingsEditor.putString("r2", "r2,2100,2150,3-29-2022");
        sharedBookingsEditor.putString("c3", "c3,1900,1950,3-30-2022");
        sharedBookingsEditor.putString("u3", "u3,1900,1950,3-30-2022");
        sharedBookingsEditor.putString("l3", "l3,1900,1950,3-30-2022");
        sharedBookingsEditor.putString("v3", "v3,1900,1950,3-30-2022");
        sharedBookingsEditor.putString("h3", "h3,1900,1950,3-30-2022");
        sharedBookingsEditor.putString("r3", "r3,1900,1950,3-30-2022");
        sharedBookingsEditor.putString("c4", "c4,2000,2050,3-30-2022");
        sharedBookingsEditor.putString("u4", "u4,2000,2050,3-30-2022");
        sharedBookingsEditor.putString("l4", "l4,2000,2050,3-30-2022");
        sharedBookingsEditor.putString("v4", "v4,2000,2050,3-30-2022");
        sharedBookingsEditor.putString("h4", "h4,2000,2050,3-30-2022");
        sharedBookingsEditor.putString("r4", "r4,2000,2050,3-30-2022");
        sharedBookingsEditor.putString("c5", "c5,2100,2150,3-30-2022");
        sharedBookingsEditor.putString("u5", "u5,2100,2150,3-30-2022");
        sharedBookingsEditor.putString("l5", "l5,2100,2150,3-30-2022");
        sharedBookingsEditor.putString("v5", "v5,2100,2150,3-30-2022");
        sharedBookingsEditor.putString("h5", "h5,2100,2150,3-30-2022");
        sharedBookingsEditor.putString("r5", "r5,2100,2150,3-30-2022");
        sharedBookingsEditor.putString("c6", "c6,1900,1950,3-31-2022");
        sharedBookingsEditor.putString("u6", "u6,1900,1950,3-31-2022");
        sharedBookingsEditor.putString("l6", "l6,1900,1950,3-31-2022");
        sharedBookingsEditor.putString("v6", "v6,1900,1950,3-31-2022");
        sharedBookingsEditor.putString("h6", "h6,1900,1950,3-31-2022");
        sharedBookingsEditor.putString("r6", "r6,1900,1950,3-31-2022");
        sharedBookingsEditor.putString("c7", "c7,2000,2050,3-31-2022");
        sharedBookingsEditor.putString("u7", "u7,2000,2050,3-31-2022");
        sharedBookingsEditor.putString("l7", "l7,2000,2050,3-31-2022");
        sharedBookingsEditor.putString("v7", "v7,2000,2050,3-31-2022");
        sharedBookingsEditor.putString("h7", "h7,2000,2050,3-31-2022");
        sharedBookingsEditor.putString("r7", "r7,2000,2050,3-31-2022");
        sharedBookingsEditor.putString("c8", "c8,2100,2150,3-31-2022");
        sharedBookingsEditor.putString("u8", "u8,2100,2150,3-31-2022");
        sharedBookingsEditor.putString("l8", "l8,2100,2150,3-31-2022");
        sharedBookingsEditor.putString("v8", "v8,2100,2150,3-31-2022");
        sharedBookingsEditor.putString("h8", "h8,2100,2150,3-31-2022");
        sharedBookingsEditor.putString("r8", "r8,2100,2150,3-31-2022");
        sharedBookingsEditor.putString("c9", "c9,1900,1950,4-1-2022");
        sharedBookingsEditor.putString("u9", "u9,1900,1950,4-1-2022");
        sharedBookingsEditor.putString("l9", "l9,1900,1950,4-1-2022");
        sharedBookingsEditor.putString("v9", "v9,1900,1950,4-1-2022");
        sharedBookingsEditor.putString("h9", "h9,1900,1950,4-1-2022");
        sharedBookingsEditor.putString("r9", "r9,1900,1950,4-1-2022");
        sharedBookingsEditor.putString("c10", "c10,2000,2050,4-1-2022");
        sharedBookingsEditor.putString("u10", "u10,2000,2050,4-1-2022");
        sharedBookingsEditor.putString("l10", "l10,2000,2050,4-1-2022");
        sharedBookingsEditor.putString("v10", "v10,2000,2050,4-1-2022");
        sharedBookingsEditor.putString("h10", "h10,2000,2050,4-1-2022");
        sharedBookingsEditor.putString("r10", "r10,2000,2050,4-1-2022");
        sharedBookingsEditor.putString("c11", "c11,2100,2150,4-1-2022");
        sharedBookingsEditor.putString("u11", "u11,2100,2150,4-1-2022");
        sharedBookingsEditor.putString("l11", "l11,2100,2150,4-1-2022");
        sharedBookingsEditor.putString("v11", "v11,2100,2150,4-1-2022");
        sharedBookingsEditor.putString("h11", "h11,2100,2150,4-1-2022");
        sharedBookingsEditor.putString("r11", "r11,2100,2150,4-1-2022");
        sharedBookingsEditor.putString("c12", "c12,1900,1950,4-2-2022");
        sharedBookingsEditor.putString("u12", "u12,1900,1950,4-2-2022");
        sharedBookingsEditor.putString("l12", "l12,1900,1950,4-2-2022");
        sharedBookingsEditor.putString("v12", "v12,1900,1950,4-2-2022");
        sharedBookingsEditor.putString("h12", "h12,1900,1950,4-2-2022");
        sharedBookingsEditor.putString("r12", "r12,1900,1950,4-2-2022");
        sharedBookingsEditor.putString("c13", "c13,2000,2050,4-2-2022");
        sharedBookingsEditor.putString("u13", "u13,2000,2050,4-2-2022");
        sharedBookingsEditor.putString("l13", "l13,2000,2050,4-2-2022");
        sharedBookingsEditor.putString("v13", "v13,2000,2050,4-2-2022");
        sharedBookingsEditor.putString("h13", "h13,2000,2050,4-2-2022");
        sharedBookingsEditor.putString("r13", "r13,2000,2050,4-2-2022");
        sharedBookingsEditor.putString("c14", "c14,2100,2150,4-2-2022");
        sharedBookingsEditor.putString("u14", "u14,2100,2150,4-2-2022");
        sharedBookingsEditor.putString("l14", "l14,2100,2150,4-2-2022");
        sharedBookingsEditor.putString("v14", "v14,2100,2150,4-2-2022");
        sharedBookingsEditor.putString("h14", "h14,2100,2150,4-2-2022");
        sharedBookingsEditor.putString("r14", "r14,2100,2150,4-2-2022");
        sharedBookingsEditor.putString("c15", "c15,1900,1950,4-3-2022");
        sharedBookingsEditor.putString("u15", "u15,1900,1950,4-3-2022");
        sharedBookingsEditor.putString("l15", "l15,1900,1950,4-3-2022");
        sharedBookingsEditor.putString("v15", "v15,1900,1950,4-3-2022");
        sharedBookingsEditor.putString("h15", "h15,1900,1950,4-3-2022");
        sharedBookingsEditor.putString("r15", "r15,1900,1950,4-3-2022");
        sharedBookingsEditor.putString("c16", "c16,2000,2050,4-3-2022");
        sharedBookingsEditor.putString("u16", "u16,2000,2050,4-3-2022");
        sharedBookingsEditor.putString("l16", "l16,2000,2050,4-3-2022");
        sharedBookingsEditor.putString("v16", "v16,2000,2050,4-3-2022");
        sharedBookingsEditor.putString("h16", "h16,2000,2050,4-3-2022");
        sharedBookingsEditor.putString("r16", "r16,2000,2050,4-3-2022");
        sharedBookingsEditor.putString("c17", "c17,2100,2150,4-3-2022");
        sharedBookingsEditor.putString("u17", "u17,2100,2150,4-3-2022");
        sharedBookingsEditor.putString("l17", "l17,2100,2150,4-3-2022");
        sharedBookingsEditor.putString("v17", "v17,2100,2150,4-3-2022");
        sharedBookingsEditor.putString("h17", "h17,2100,2150,4-3-2022");
        sharedBookingsEditor.putString("r17", "r17,2100,2150,4-3-2022");
        sharedBookingsEditor.putString("c18", "c18,1900,1950,4-4-2022");
        sharedBookingsEditor.putString("u18", "u18,1900,1950,4-4-2022");
        sharedBookingsEditor.putString("l18", "l18,1900,1950,4-4-2022");
        sharedBookingsEditor.putString("v18", "v18,1900,1950,4-4-2022");
        sharedBookingsEditor.putString("h18", "h18,1900,1950,4-4-2022");
        sharedBookingsEditor.putString("r18", "r18,1900,1950,4-4-2022");
        sharedBookingsEditor.putString("c19", "c19,2000,2050,4-4-2022");
        sharedBookingsEditor.putString("u19", "u19,2000,2050,4-4-2022");
        sharedBookingsEditor.putString("l19", "l19,2000,2050,4-4-2022");
        sharedBookingsEditor.putString("v19", "v19,2000,2050,4-4-2022");
        sharedBookingsEditor.putString("h19", "h19,2000,2050,4-4-2022");
        sharedBookingsEditor.putString("r19", "r19,2000,2050,4-4-2022");
        sharedBookingsEditor.putString("c20", "c20,2100,2150,4-4-2022");
        sharedBookingsEditor.putString("u20", "u20,2100,2150,4-4-2022");
        sharedBookingsEditor.putString("l20", "l20,2100,2150,4-4-2022");
        sharedBookingsEditor.putString("v20", "v20,2100,2150,4-4-2022");
        sharedBookingsEditor.putString("h20", "h20,2100,2150,4-4-2022");
        sharedBookingsEditor.putString("r20", "r20,2100,2150,4-4-2022");
        sharedBookingsEditor.putString("c21", "c21,1900,1950,4-5-2022");
        sharedBookingsEditor.putString("u21", "u21,1900,1950,4-5-2022");
        sharedBookingsEditor.putString("l21", "l21,1900,1950,4-5-2022");
        sharedBookingsEditor.putString("v21", "v21,1900,1950,4-5-2022");
        sharedBookingsEditor.putString("h21", "h21,1900,1950,4-5-2022");
        sharedBookingsEditor.putString("r21", "r21,1900,1950,4-5-2022");
        sharedBookingsEditor.putString("c22", "c22,2000,2050,4-5-2022");
        sharedBookingsEditor.putString("u22", "u22,2000,2050,4-5-2022");
        sharedBookingsEditor.putString("l22", "l22,2000,2050,4-5-2022");
        sharedBookingsEditor.putString("v22", "v22,2000,2050,4-5-2022");
        sharedBookingsEditor.putString("h22", "h22,2000,2050,4-5-2022");
        sharedBookingsEditor.putString("r22", "r22,2000,2050,4-5-2022");
        sharedBookingsEditor.putString("c23", "c23,2100,2150,4-5-2022");
        sharedBookingsEditor.putString("u23", "u23,2100,2150,4-5-2022");
        sharedBookingsEditor.putString("l23", "l23,2100,2150,4-5-2022");
        sharedBookingsEditor.putString("v23", "v23,2100,2150,4-5-2022");
        sharedBookingsEditor.putString("h23", "h23,2100,2150,4-5-2022");
        sharedBookingsEditor.putString("r23", "r23,2100,2150,4-5-2022");
        sharedBookingsEditor.putString("c24", "c24,1900,1950,4-6-2022");
        sharedBookingsEditor.putString("u24", "u24,1900,1950,4-6-2022");
        sharedBookingsEditor.putString("l24", "l24,1900,1950,4-6-2022");
        sharedBookingsEditor.putString("v24", "v24,1900,1950,4-6-2022");
        sharedBookingsEditor.putString("h24", "h24,1900,1950,4-6-2022");
        sharedBookingsEditor.putString("r24", "r24,1900,1950,4-6-2022");
        sharedBookingsEditor.putString("c25", "c25,2000,2050,4-6-2022");
        sharedBookingsEditor.putString("u25", "u25,2000,2050,4-6-2022");
        sharedBookingsEditor.putString("l25", "l25,2000,2050,4-6-2022");
        sharedBookingsEditor.putString("v25", "v25,2000,2050,4-6-2022");
        sharedBookingsEditor.putString("h25", "h25,2000,2050,4-6-2022");
        sharedBookingsEditor.putString("r25", "r25,2000,2050,4-6-2022");
        sharedBookingsEditor.putString("c26", "c26,2100,2150,4-6-2022");
        sharedBookingsEditor.putString("u26", "u26,2100,2150,4-6-2022");
        sharedBookingsEditor.putString("l26", "l26,2100,2150,4-6-2022");
        sharedBookingsEditor.putString("v26", "v26,2100,2150,4-6-2022");
        sharedBookingsEditor.putString("h26", "h26,2100,2150,4-6-2022");
        sharedBookingsEditor.putString("r26", "r26,2100,2150,4-6-2022");
        sharedBookingsEditor.putString("c27", "c27,1900,1950,4-7-2022");
        sharedBookingsEditor.putString("u27", "u27,1900,1950,4-7-2022");
        sharedBookingsEditor.putString("l27", "l27,1900,1950,4-7-2022");
        sharedBookingsEditor.putString("v27", "v27,1900,1950,4-7-2022");
        sharedBookingsEditor.putString("h27", "h27,1900,1950,4-7-2022");
        sharedBookingsEditor.putString("r27", "r27,1900,1950,4-7-2022");
        sharedBookingsEditor.putString("c28", "c28,2000,2050,4-7-2022");
        sharedBookingsEditor.putString("u28", "u28,2000,2050,4-7-2022");
        sharedBookingsEditor.putString("l28", "l28,2000,2050,4-7-2022");
        sharedBookingsEditor.putString("v28", "v28,2000,2050,4-7-2022");
        sharedBookingsEditor.putString("h28", "h28,2000,2050,4-7-2022");
        sharedBookingsEditor.putString("r28", "r28,2000,2050,4-7-2022");
        sharedBookingsEditor.putString("c29", "c29,2100,2150,4-7-2022");
        sharedBookingsEditor.putString("u29", "u29,2100,2150,4-7-2022");
        sharedBookingsEditor.putString("l29", "l29,2100,2150,4-7-2022");
        sharedBookingsEditor.putString("v29", "v29,2100,2150,4-7-2022");
        sharedBookingsEditor.putString("h29", "h29,2100,2150,4-7-2022");
        sharedBookingsEditor.putString("r29", "r29,2100,2150,4-7-2022");
        sharedBookingsEditor.putString("c30", "c30,1900,1950,4-8-2022");
        sharedBookingsEditor.putString("u30", "u30,1900,1950,4-8-2022");
        sharedBookingsEditor.putString("l30", "l30,1900,1950,4-8-2022");
        sharedBookingsEditor.putString("v30", "v30,1900,1950,4-8-2022");
        sharedBookingsEditor.putString("h30", "h30,1900,1950,4-8-2022");
        sharedBookingsEditor.putString("r30", "r30,1900,1950,4-8-2022");
        sharedBookingsEditor.putString("c31", "c31,2000,2050,4-8-2022");
        sharedBookingsEditor.putString("u31", "u31,2000,2050,4-8-2022");
        sharedBookingsEditor.putString("l31", "l31,2000,2050,4-8-2022");
        sharedBookingsEditor.putString("v31", "v31,2000,2050,4-8-2022");
        sharedBookingsEditor.putString("h31", "h31,2000,2050,4-8-2022");
        sharedBookingsEditor.putString("r31", "r31,2000,2050,4-8-2022");
        sharedBookingsEditor.putString("c32", "c32,2100,2150,4-8-2022");
        sharedBookingsEditor.putString("u32", "u32,2100,2150,4-8-2022");
        sharedBookingsEditor.putString("l32", "l32,2100,2150,4-8-2022");
        sharedBookingsEditor.putString("v32", "v32,2100,2150,4-8-2022");
        sharedBookingsEditor.putString("h32", "h32,2100,2150,4-8-2022");
        sharedBookingsEditor.putString("r32", "r32,2100,2150,4-8-2022");
        sharedBookingsEditor.putString("c33", "c33,1900,1950,4-9-2022");
        sharedBookingsEditor.putString("u33", "u33,1900,1950,4-9-2022");
        sharedBookingsEditor.putString("l33", "l33,1900,1950,4-9-2022");
        sharedBookingsEditor.putString("v33", "v33,1900,1950,4-9-2022");
        sharedBookingsEditor.putString("h33", "h33,1900,1950,4-9-2022");
        sharedBookingsEditor.putString("r33", "r33,1900,1950,4-9-2022");
        sharedBookingsEditor.putString("c34", "c34,2000,2050,4-9-2022");
        sharedBookingsEditor.putString("u34", "u34,2000,2050,4-9-2022");
        sharedBookingsEditor.putString("l34", "l34,2000,2050,4-9-2022");
        sharedBookingsEditor.putString("v34", "v34,2000,2050,4-9-2022");
        sharedBookingsEditor.putString("h34", "h34,2000,2050,4-9-2022");
        sharedBookingsEditor.putString("r34", "r34,2000,2050,4-9-2022");
        sharedBookingsEditor.putString("c35", "c35,2100,2150,4-9-2022");
        sharedBookingsEditor.putString("u35", "u35,2100,2150,4-9-2022");
        sharedBookingsEditor.putString("l35", "l35,2100,2150,4-9-2022");
        sharedBookingsEditor.putString("v35", "v35,2100,2150,4-9-2022");
        sharedBookingsEditor.putString("h35", "h35,2100,2150,4-9-2022");
        sharedBookingsEditor.putString("r35", "r35,2100,2150,4-9-2022");
        sharedBookingsEditor.putString("c36", "c36,1900,1950,4-10-2022");
        sharedBookingsEditor.putString("u36", "u36,1900,1950,4-10-2022");
        sharedBookingsEditor.putString("l36", "l36,1900,1950,4-10-2022");
        sharedBookingsEditor.putString("v36", "v36,1900,1950,4-10-2022");
        sharedBookingsEditor.putString("h36", "h36,1900,1950,4-10-2022");
        sharedBookingsEditor.putString("r36", "r36,1900,1950,4-10-2022");
        sharedBookingsEditor.putString("c37", "c37,2000,2050,4-10-2022");
        sharedBookingsEditor.putString("u37", "u37,2000,2050,4-10-2022");
        sharedBookingsEditor.putString("l37", "l37,2000,2050,4-10-2022");
        sharedBookingsEditor.putString("v37", "v37,2000,2050,4-10-2022");
        sharedBookingsEditor.putString("h37", "h37,2000,2050,4-10-2022");
        sharedBookingsEditor.putString("r37", "r37,2000,2050,4-10-2022");
        sharedBookingsEditor.putString("c38", "c38,2100,2150,4-10-2022");
        sharedBookingsEditor.putString("u38", "u38,2100,2150,4-10-2022");
        sharedBookingsEditor.putString("l38", "l38,2100,2150,4-10-2022");
        sharedBookingsEditor.putString("v38", "v38,2100,2150,4-10-2022");
        sharedBookingsEditor.putString("h38", "h38,2100,2150,4-10-2022");
        sharedBookingsEditor.putString("r38", "r38,2100,2150,4-10-2022");
        sharedBookingsEditor.putString("c39", "c39,1900,1950,4-11-2022");
        sharedBookingsEditor.putString("u39", "u39,1900,1950,4-11-2022");
        sharedBookingsEditor.putString("l39", "l39,1900,1950,4-11-2022");
        sharedBookingsEditor.putString("v39", "v39,1900,1950,4-11-2022");
        sharedBookingsEditor.putString("h39", "h39,1900,1950,4-11-2022");
        sharedBookingsEditor.putString("r39", "r39,1900,1950,4-11-2022");
        sharedBookingsEditor.putString("c40", "c40,2000,2050,4-11-2022");
        sharedBookingsEditor.putString("u40", "u40,2000,2050,4-11-2022");
        sharedBookingsEditor.putString("l40", "l40,2000,2050,4-11-2022");
        sharedBookingsEditor.putString("v40", "v40,2000,2050,4-11-2022");
        sharedBookingsEditor.putString("h40", "h40,2000,2050,4-11-2022");
        sharedBookingsEditor.putString("r40", "r40,2000,2050,4-11-2022");
        sharedBookingsEditor.putString("c41", "c41,2100,2150,4-11-2022");
        sharedBookingsEditor.putString("u41", "u41,2100,2150,4-11-2022");
        sharedBookingsEditor.putString("l41", "l41,2100,2150,4-11-2022");
        sharedBookingsEditor.putString("v41", "v41,2100,2150,4-11-2022");
        sharedBookingsEditor.putString("h41", "h41,2100,2150,4-11-2022");
        sharedBookingsEditor.putString("r41", "r41,2100,2150,4-11-2022");
        sharedBookingsEditor.putString("c42", "c42,1900,1950,4-12-2022");
        sharedBookingsEditor.putString("u42", "u42,1900,1950,4-12-2022");
        sharedBookingsEditor.putString("l42", "l42,1900,1950,4-12-2022");
        sharedBookingsEditor.putString("v42", "v42,1900,1950,4-12-2022");
        sharedBookingsEditor.putString("h42", "h42,1900,1950,4-12-2022");
        sharedBookingsEditor.putString("r42", "r42,1900,1950,4-12-2022");
        sharedBookingsEditor.putString("c43", "c43,2000,2050,4-12-2022");
        sharedBookingsEditor.putString("u43", "u43,2000,2050,4-12-2022");
        sharedBookingsEditor.putString("l43", "l43,2000,2050,4-12-2022");
        sharedBookingsEditor.putString("v43", "v43,2000,2050,4-12-2022");
        sharedBookingsEditor.putString("h43", "h43,2000,2050,4-12-2022");
        sharedBookingsEditor.putString("r43", "r43,2000,2050,4-12-2022");
        sharedBookingsEditor.putString("c44", "c44,2100,2150,4-12-2022");
        sharedBookingsEditor.putString("u44", "u44,2100,2150,4-12-2022");
        sharedBookingsEditor.putString("l44", "l44,2100,2150,4-12-2022");
        sharedBookingsEditor.putString("v44", "v44,2100,2150,4-12-2022");
        sharedBookingsEditor.putString("h44", "h44,2100,2150,4-12-2022");
        sharedBookingsEditor.putString("r44", "r44,2100,2150,4-12-2022");
        sharedBookingsEditor.putString("c45", "c45,1900,1950,4-13-2022");
        sharedBookingsEditor.putString("u45", "u45,1900,1950,4-13-2022");
        sharedBookingsEditor.putString("l45", "l45,1900,1950,4-13-2022");
        sharedBookingsEditor.putString("v45", "v45,1900,1950,4-13-2022");
        sharedBookingsEditor.putString("h45", "h45,1900,1950,4-13-2022");
        sharedBookingsEditor.putString("r45", "r45,1900,1950,4-13-2022");
        sharedBookingsEditor.putString("c46", "c46,2000,2050,4-13-2022");
        sharedBookingsEditor.putString("u46", "u46,2000,2050,4-13-2022");
        sharedBookingsEditor.putString("l46", "l46,2000,2050,4-13-2022");
        sharedBookingsEditor.putString("v46", "v46,2000,2050,4-13-2022");
        sharedBookingsEditor.putString("h46", "h46,2000,2050,4-13-2022");
        sharedBookingsEditor.putString("r46", "r46,2000,2050,4-13-2022");
        sharedBookingsEditor.putString("c47", "c47,2100,2150,4-13-2022");
        sharedBookingsEditor.putString("u47", "u47,2100,2150,4-13-2022");
        sharedBookingsEditor.putString("l47", "l47,2100,2150,4-13-2022");
        sharedBookingsEditor.putString("v47", "v47,2100,2150,4-13-2022");
        sharedBookingsEditor.putString("h47", "h47,2100,2150,4-13-2022");
        sharedBookingsEditor.putString("r47", "r47,2100,2150,4-13-2022");
        sharedBookingsEditor.putString("c51", "c51,1900,1950,4-15-2022");
        sharedBookingsEditor.putString("u51", "u51,1900,1950,4-15-2022");
        sharedBookingsEditor.putString("l51", "l51,1900,1950,4-15-2022");
        sharedBookingsEditor.putString("v51", "v51,1900,1950,4-15-2022");
        sharedBookingsEditor.putString("h51", "h51,1900,1950,4-15-2022");
        sharedBookingsEditor.putString("r51", "r51,1900,1950,4-15-2022");
        sharedBookingsEditor.putString("c52", "c52,2000,2050,4-15-2022");
        sharedBookingsEditor.putString("u52", "u52,2000,2050,4-15-2022");
        sharedBookingsEditor.putString("l52", "l52,2000,2050,4-15-2022");
        sharedBookingsEditor.putString("v52", "v52,2000,2050,4-15-2022");
        sharedBookingsEditor.putString("h52", "h52,2000,2050,4-15-2022");
        sharedBookingsEditor.putString("r52", "r52,2000,2050,4-15-2022");
        sharedBookingsEditor.putString("c53", "c53,2100,2150,4-15-2022");
        sharedBookingsEditor.putString("u53", "u53,2100,2150,4-15-2022");
        sharedBookingsEditor.putString("l53", "l53,2100,2150,4-15-2022");
        sharedBookingsEditor.putString("v53", "v53,2100,2150,4-15-2022");
        sharedBookingsEditor.putString("h53", "h53,2100,2150,4-15-2022");
        sharedBookingsEditor.putString("r53", "r53,2100,2150,4-15-2022");
        sharedBookingsEditor.putString("c54", "c54,1900,1950,4-16-2022");
        sharedBookingsEditor.putString("u54", "u54,1900,1950,4-16-2022");
        sharedBookingsEditor.putString("l54", "l54,1900,1950,4-16-2022");
        sharedBookingsEditor.putString("v54", "v54,1900,1950,4-16-2022");
        sharedBookingsEditor.putString("h54", "h54,1900,1950,4-16-2022");
        sharedBookingsEditor.putString("r54", "r54,1900,1950,4-16-2022");
        sharedBookingsEditor.putString("c55", "c55,2000,2050,4-16-2022");
        sharedBookingsEditor.putString("u55", "u55,2000,2050,4-16-2022");
        sharedBookingsEditor.putString("l55", "l55,2000,2050,4-16-2022");
        sharedBookingsEditor.putString("v55", "v55,2000,2050,4-16-2022");
        sharedBookingsEditor.putString("h55", "h55,2000,2050,4-16-2022");
        sharedBookingsEditor.putString("r55", "r55,2000,2050,4-16-2022");
        sharedBookingsEditor.putString("c56", "c56,2100,2150,4-16-2022");
        sharedBookingsEditor.putString("u56", "u56,2100,2150,4-16-2022");
        sharedBookingsEditor.putString("l56", "l56,2100,2150,4-16-2022");
        sharedBookingsEditor.putString("v56", "v56,2100,2150,4-16-2022");
        sharedBookingsEditor.putString("h56", "h56,2100,2150,4-16-2022");
        sharedBookingsEditor.putString("r56", "r56,2100,2150,4-16-2022");
        sharedBookingsEditor.putString("c57", "c57,1900,1950,4-17-2022");
        sharedBookingsEditor.putString("u57", "u57,1900,1950,4-17-2022");
        sharedBookingsEditor.putString("l57", "l57,1900,1950,4-17-2022");
        sharedBookingsEditor.putString("v57", "v57,1900,1950,4-17-2022");
        sharedBookingsEditor.putString("h57", "h57,1900,1950,4-17-2022");
        sharedBookingsEditor.putString("r57", "r57,1900,1950,4-17-2022");
        sharedBookingsEditor.putString("c58", "c58,2000,2050,4-17-2022");
        sharedBookingsEditor.putString("u58", "u58,2000,2050,4-17-2022");
        sharedBookingsEditor.putString("l58", "l58,2000,2050,4-17-2022");
        sharedBookingsEditor.putString("v58", "v58,2000,2050,4-17-2022");
        sharedBookingsEditor.putString("h58", "h58,2000,2050,4-17-2022");
        sharedBookingsEditor.putString("r58", "r58,2000,2050,4-17-2022");
        sharedBookingsEditor.putString("c59", "c59,2100,2150,4-17-2022");
        sharedBookingsEditor.putString("u59", "u59,2100,2150,4-17-2022");
        sharedBookingsEditor.putString("l59", "l59,2100,2150,4-17-2022");
        sharedBookingsEditor.putString("v59", "v59,2100,2150,4-17-2022");
        sharedBookingsEditor.putString("h59", "h59,2100,2150,4-17-2022");
        sharedBookingsEditor.putString("r59", "r59,2100,2150,4-17-2022");
        sharedBookingsEditor.putString("c60", "c60,1900,1950,4-18-2022");
        sharedBookingsEditor.putString("u60", "u60,1900,1950,4-18-2022");
        sharedBookingsEditor.putString("l60", "l60,1900,1950,4-18-2022");
        sharedBookingsEditor.putString("v60", "v60,1900,1950,4-18-2022");
        sharedBookingsEditor.putString("h60", "h60,1900,1950,4-18-2022");
        sharedBookingsEditor.putString("r60", "r60,1900,1950,4-18-2022");
        sharedBookingsEditor.putString("c61", "c61,2000,2050,4-18-2022");
        sharedBookingsEditor.putString("u61", "u61,2000,2050,4-18-2022");
        sharedBookingsEditor.putString("l61", "l61,2000,2050,4-18-2022");
        sharedBookingsEditor.putString("v61", "v61,2000,2050,4-18-2022");
        sharedBookingsEditor.putString("h61", "h61,2000,2050,4-18-2022");
        sharedBookingsEditor.putString("r61", "r61,2000,2050,4-18-2022");
        sharedBookingsEditor.putString("c62", "c62,2100,2150,4-18-2022");
        sharedBookingsEditor.putString("u62", "u62,2100,2150,4-18-2022");
        sharedBookingsEditor.putString("l62", "l62,2100,2150,4-18-2022");
        sharedBookingsEditor.putString("v62", "v62,2100,2150,4-18-2022");
        sharedBookingsEditor.putString("h62", "h62,2100,2150,4-18-2022");
        sharedBookingsEditor.putString("r62", "r62,2100,2150,4-18-2022");
        sharedBookingsEditor.putString("c63", "c63,1900,1950,4-19-2022");
        sharedBookingsEditor.putString("u63", "u63,1900,1950,4-19-2022");
        sharedBookingsEditor.putString("l63", "l63,1900,1950,4-19-2022");
        sharedBookingsEditor.putString("v63", "v63,1900,1950,4-19-2022");
        sharedBookingsEditor.putString("h63", "h63,1900,1950,4-19-2022");
        sharedBookingsEditor.putString("r63", "r63,1900,1950,4-19-2022");
        sharedBookingsEditor.putString("c64", "c64,2000,2050,4-19-2022");
        sharedBookingsEditor.putString("u64", "u64,2000,2050,4-19-2022");
        sharedBookingsEditor.putString("l64", "l64,2000,2050,4-19-2022");
        sharedBookingsEditor.putString("v64", "v64,2000,2050,4-19-2022");
        sharedBookingsEditor.putString("h64", "h64,2000,2050,4-19-2022");
        sharedBookingsEditor.putString("r64", "r64,2000,2050,4-19-2022");
        sharedBookingsEditor.putString("c65", "c65,2100,2150,4-19-2022");
        sharedBookingsEditor.putString("u65", "u65,2100,2150,4-19-2022");
        sharedBookingsEditor.putString("l65", "l65,2100,2150,4-19-2022");
        sharedBookingsEditor.putString("v65", "v65,2100,2150,4-19-2022");
        sharedBookingsEditor.putString("h65", "h65,2100,2150,4-19-2022");
        sharedBookingsEditor.putString("r65", "r65,2100,2150,4-19-2022");
        sharedBookingsEditor.putString("c66", "c66,1900,1950,4-20-2022");
        sharedBookingsEditor.putString("u66", "u66,1900,1950,4-20-2022");
        sharedBookingsEditor.putString("l66", "l66,1900,1950,4-20-2022");
        sharedBookingsEditor.putString("v66", "v66,1900,1950,4-20-2022");
        sharedBookingsEditor.putString("h66", "h66,1900,1950,4-20-2022");
        sharedBookingsEditor.putString("r66", "r66,1900,1950,4-20-2022");
        sharedBookingsEditor.putString("c67", "c67,2000,2050,4-20-2022");
        sharedBookingsEditor.putString("u67", "u67,2000,2050,4-20-2022");
        sharedBookingsEditor.putString("l67", "l67,2000,2050,4-20-2022");
        sharedBookingsEditor.putString("v67", "v67,2000,2050,4-20-2022");
        sharedBookingsEditor.putString("h67", "h67,2000,2050,4-20-2022");
        sharedBookingsEditor.putString("r67", "r67,2000,2050,4-20-2022");
        sharedBookingsEditor.putString("c68", "c68,2100,2150,4-20-2022");
        sharedBookingsEditor.putString("u68", "u68,2100,2150,4-20-2022");
        sharedBookingsEditor.putString("l68", "l68,2100,2150,4-20-2022");
        sharedBookingsEditor.putString("v68", "v68,2100,2150,4-20-2022");
        sharedBookingsEditor.putString("h68", "h68,2100,2150,4-20-2022");
        sharedBookingsEditor.putString("r68", "r68,2100,2150,4-20-2022");
        sharedBookingsEditor.putString("c69", "c69,1900,1950,4-21-2022");
        sharedBookingsEditor.putString("u69", "u69,1900,1950,4-21-2022");
        sharedBookingsEditor.putString("l69", "l69,1900,1950,4-21-2022");
        sharedBookingsEditor.putString("v69", "v69,1900,1950,4-21-2022");
        sharedBookingsEditor.putString("h69", "h69,1900,1950,4-21-2022");
        sharedBookingsEditor.putString("r69", "r69,1900,1950,4-21-2022");
        sharedBookingsEditor.putString("c70", "c70,2000,2050,4-21-2022");
        sharedBookingsEditor.putString("u70", "u70,2000,2050,4-21-2022");
        sharedBookingsEditor.putString("l70", "l70,2000,2050,4-21-2022");
        sharedBookingsEditor.putString("v70", "v70,2000,2050,4-21-2022");
        sharedBookingsEditor.putString("h70", "h70,2000,2050,4-21-2022");
        sharedBookingsEditor.putString("r70", "r70,2000,2050,4-21-2022");
        sharedBookingsEditor.putString("c71", "c71,2100,2150,4-21-2022");
        sharedBookingsEditor.putString("u71", "u71,2100,2150,4-21-2022");
        sharedBookingsEditor.putString("l71", "l71,2100,2150,4-21-2022");
        sharedBookingsEditor.putString("v71", "v71,2100,2150,4-21-2022");
        sharedBookingsEditor.putString("h71", "h71,2100,2150,4-21-2022");
        sharedBookingsEditor.putString("r71", "r71,2100,2150,4-21-2022");
        sharedBookingsEditor.putString("c72", "c72,1900,1950,4-22-2022");
        sharedBookingsEditor.putString("u72", "u72,1900,1950,4-22-2022");
        sharedBookingsEditor.putString("l72", "l72,1900,1950,4-22-2022");
        sharedBookingsEditor.putString("v72", "v72,1900,1950,4-22-2022");
        sharedBookingsEditor.putString("h72", "h72,1900,1950,4-22-2022");
        sharedBookingsEditor.putString("r72", "r72,1900,1950,4-22-2022");
        sharedBookingsEditor.putString("c73", "c73,2000,2050,4-22-2022");
        sharedBookingsEditor.putString("u73", "u73,2000,2050,4-22-2022");
        sharedBookingsEditor.putString("l73", "l73,2000,2050,4-22-2022");
        sharedBookingsEditor.putString("v73", "v73,2000,2050,4-22-2022");
        sharedBookingsEditor.putString("h73", "h73,2000,2050,4-22-2022");
        sharedBookingsEditor.putString("r73", "r73,2000,2050,4-22-2022");
        sharedBookingsEditor.putString("c74", "c74,2100,2150,4-22-2022");
        sharedBookingsEditor.putString("u74", "u74,2100,2150,4-22-2022");
        sharedBookingsEditor.putString("l74", "l74,2100,2150,4-22-2022");
        sharedBookingsEditor.putString("v74", "v74,2100,2150,4-22-2022");
        sharedBookingsEditor.putString("h74", "h74,2100,2150,4-22-2022");
        sharedBookingsEditor.putString("r74", "r74,2100,2150,4-22-2022");
        sharedBookingsEditor.putString("c75", "c75,1900,1950,4-23-2022");
        sharedBookingsEditor.putString("u75", "u75,1900,1950,4-23-2022");
        sharedBookingsEditor.putString("l75", "l75,1900,1950,4-23-2022");
        sharedBookingsEditor.putString("v75", "v75,1900,1950,4-23-2022");
        sharedBookingsEditor.putString("h75", "h75,1900,1950,4-23-2022");
        sharedBookingsEditor.putString("r75", "r75,1900,1950,4-23-2022");
        sharedBookingsEditor.putString("c76", "c76,2000,2050,4-23-2022");
        sharedBookingsEditor.putString("u76", "u76,2000,2050,4-23-2022");
        sharedBookingsEditor.putString("l76", "l76,2000,2050,4-23-2022");
        sharedBookingsEditor.putString("v76", "v76,2000,2050,4-23-2022");
        sharedBookingsEditor.putString("h76", "h76,2000,2050,4-23-2022");
        sharedBookingsEditor.putString("r76", "r76,2000,2050,4-23-2022");
        sharedBookingsEditor.putString("c77", "c77,2100,2150,4-23-2022");
        sharedBookingsEditor.putString("u77", "u77,2100,2150,4-23-2022");
        sharedBookingsEditor.putString("l77", "l77,2100,2150,4-23-2022");
        sharedBookingsEditor.putString("v77", "v77,2100,2150,4-23-2022");
        sharedBookingsEditor.putString("h77", "h77,2100,2150,4-23-2022");
        sharedBookingsEditor.putString("r77", "r77,2100,2150,4-23-2022");
        sharedBookingsEditor.putString("c78", "c78,1900,1950,4-24-2022");
        sharedBookingsEditor.putString("u78", "u78,1900,1950,4-24-2022");
        sharedBookingsEditor.putString("l78", "l78,1900,1950,4-24-2022");
        sharedBookingsEditor.putString("v78", "v78,1900,1950,4-24-2022");
        sharedBookingsEditor.putString("h78", "h78,1900,1950,4-24-2022");
        sharedBookingsEditor.putString("r78", "r78,1900,1950,4-24-2022");
        sharedBookingsEditor.putString("c79", "c79,2000,2050,4-24-2022");
        sharedBookingsEditor.putString("u79", "u79,2000,2050,4-24-2022");
        sharedBookingsEditor.putString("l79", "l79,2000,2050,4-24-2022");
        sharedBookingsEditor.putString("v79", "v79,2000,2050,4-24-2022");
        sharedBookingsEditor.putString("h79", "h79,2000,2050,4-24-2022");
        sharedBookingsEditor.putString("r79", "r79,2000,2050,4-24-2022");
        sharedBookingsEditor.putString("c80", "c80,2100,2150,4-24-2022");
        sharedBookingsEditor.putString("u80", "u80,2100,2150,4-24-2022");
        sharedBookingsEditor.putString("l80", "l80,2100,2150,4-24-2022");
        sharedBookingsEditor.putString("v80", "v80,2100,2150,4-24-2022");
        sharedBookingsEditor.putString("h80", "h80,2100,2150,4-24-2022");
        sharedBookingsEditor.putString("r80", "r80,2100,2150,4-24-2022");
        sharedBookingsEditor.putString("c81", "c81,1900,1950,4-25-2022");
        sharedBookingsEditor.putString("u81", "u81,1900,1950,4-25-2022");
        sharedBookingsEditor.putString("l81", "l81,1900,1950,4-25-2022");
        sharedBookingsEditor.putString("v81", "v81,1900,1950,4-25-2022");
        sharedBookingsEditor.putString("h81", "h81,1900,1950,4-25-2022");
        sharedBookingsEditor.putString("r81", "r81,1900,1950,4-25-2022");
        sharedBookingsEditor.putString("c82", "c82,2000,2050,4-25-2022");
        sharedBookingsEditor.putString("u82", "u82,2000,2050,4-25-2022");
        sharedBookingsEditor.putString("l82", "l82,2000,2050,4-25-2022");
        sharedBookingsEditor.putString("v82", "v82,2000,2050,4-25-2022");
        sharedBookingsEditor.putString("h82", "h82,2000,2050,4-25-2022");
        sharedBookingsEditor.putString("r82", "r82,2000,2050,4-25-2022");
        sharedBookingsEditor.putString("c83", "c83,2100,2150,4-25-2022");
        sharedBookingsEditor.putString("u83", "u83,2100,2150,4-25-2022");
        sharedBookingsEditor.putString("l83", "l83,2100,2150,4-25-2022");
        sharedBookingsEditor.putString("v83", "v83,2100,2150,4-25-2022");
        sharedBookingsEditor.putString("h83", "h83,2100,2150,4-25-2022");
        sharedBookingsEditor.putString("r83", "r83,2100,2150,4-25-2022");
        sharedBookingsEditor.putString("c84", "c84,1900,1950,4-26-2022");
        sharedBookingsEditor.putString("u84", "u84,1900,1950,4-26-2022");
        sharedBookingsEditor.putString("l84", "l84,1900,1950,4-26-2022");
        sharedBookingsEditor.putString("v84", "v84,1900,1950,4-26-2022");
        sharedBookingsEditor.putString("h84", "h84,1900,1950,4-26-2022");
        sharedBookingsEditor.putString("r84", "r84,1900,1950,4-26-2022");
        sharedBookingsEditor.putString("c85", "c85,2000,2050,4-26-2022");
        sharedBookingsEditor.putString("u85", "u85,2000,2050,4-26-2022");
        sharedBookingsEditor.putString("l85", "l85,2000,2050,4-26-2022");
        sharedBookingsEditor.putString("v85", "v85,2000,2050,4-26-2022");
        sharedBookingsEditor.putString("h85", "h85,2000,2050,4-26-2022");
        sharedBookingsEditor.putString("r85", "r85,2000,2050,4-26-2022");
        sharedBookingsEditor.putString("c86", "c86,2100,2150,4-26-2022");
        sharedBookingsEditor.putString("u86", "u86,2100,2150,4-26-2022");
        sharedBookingsEditor.putString("l86", "l86,2100,2150,4-26-2022");
        sharedBookingsEditor.putString("v86", "v86,2100,2150,4-26-2022");
        sharedBookingsEditor.putString("h86", "h86,2100,2150,4-26-2022");
        sharedBookingsEditor.putString("r86", "r86,2100,2150,4-26-2022");
        sharedBookingsEditor.putString("c87", "c87,1900,1950,4-27-2022");
        sharedBookingsEditor.putString("u87", "u87,1900,1950,4-27-2022");
        sharedBookingsEditor.putString("l87", "l87,1900,1950,4-27-2022");
        sharedBookingsEditor.putString("v87", "v87,1900,1950,4-27-2022");
        sharedBookingsEditor.putString("h87", "h87,1900,1950,4-27-2022");
        sharedBookingsEditor.putString("r87", "r87,1900,1950,4-27-2022");
        sharedBookingsEditor.putString("c88", "c88,2000,2050,4-27-2022");
        sharedBookingsEditor.putString("u88", "u88,2000,2050,4-27-2022");
        sharedBookingsEditor.putString("l88", "l88,2000,2050,4-27-2022");
        sharedBookingsEditor.putString("v88", "v88,2000,2050,4-27-2022");
        sharedBookingsEditor.putString("h88", "h88,2000,2050,4-27-2022");
        sharedBookingsEditor.putString("r88", "r88,2000,2050,4-27-2022");
        sharedBookingsEditor.putString("c89", "c89,2100,2150,4-27-2022");
        sharedBookingsEditor.putString("u89", "u89,2100,2150,4-27-2022");
        sharedBookingsEditor.putString("l89", "l89,2100,2150,4-27-2022");
        sharedBookingsEditor.putString("v89", "v89,2100,2150,4-27-2022");
        sharedBookingsEditor.putString("h89", "h89,2100,2150,4-27-2022");
        sharedBookingsEditor.putString("r89", "r89,2100,2150,4-27-2022");
        sharedBookingsEditor.putString("c90", "c90,1900,1950,4-28-2022");
        sharedBookingsEditor.putString("u90", "u90,1900,1950,4-28-2022");
        sharedBookingsEditor.putString("l90", "l90,1900,1950,4-28-2022");
        sharedBookingsEditor.putString("v90", "v90,1900,1950,4-28-2022");
        sharedBookingsEditor.putString("h90", "h90,1900,1950,4-28-2022");
        sharedBookingsEditor.putString("r90", "r90,1900,1950,4-28-2022");
        sharedBookingsEditor.putString("c91", "c91,2000,2050,4-28-2022");
        sharedBookingsEditor.putString("u91", "u91,2000,2050,4-28-2022");
        sharedBookingsEditor.putString("l91", "l91,2000,2050,4-28-2022");
        sharedBookingsEditor.putString("v91", "v91,2000,2050,4-28-2022");
        sharedBookingsEditor.putString("h91", "h91,2000,2050,4-28-2022");
        sharedBookingsEditor.putString("r91", "r91,2000,2050,4-28-2022");
        sharedBookingsEditor.putString("c92", "c92,2100,2150,4-28-2022");
        sharedBookingsEditor.putString("u92", "u92,2100,2150,4-28-2022");
        sharedBookingsEditor.putString("l92", "l92,2100,2150,4-28-2022");
        sharedBookingsEditor.putString("v92", "v92,2100,2150,4-28-2022");
        sharedBookingsEditor.putString("h92", "h92,2100,2150,4-28-2022");
        sharedBookingsEditor.putString("r92", "r92,2100,2150,4-28-2022");
        sharedBookingsEditor.putString("c93", "c93,1900,1950,4-29-2022");
        sharedBookingsEditor.putString("u93", "u93,1900,1950,4-29-2022");
        sharedBookingsEditor.putString("l93", "l93,1900,1950,4-29-2022");
        sharedBookingsEditor.putString("v93", "v93,1900,1950,4-29-2022");
        sharedBookingsEditor.putString("h93", "h93,1900,1950,4-29-2022");
        sharedBookingsEditor.putString("r93", "r93,1900,1950,4-29-2022");
        sharedBookingsEditor.putString("c94", "c94,2000,2050,4-29-2022");
        sharedBookingsEditor.putString("u94", "u94,2000,2050,4-29-2022");
        sharedBookingsEditor.putString("l94", "l94,2000,2050,4-29-2022");
        sharedBookingsEditor.putString("v94", "v94,2000,2050,4-29-2022");
        sharedBookingsEditor.putString("h94", "h94,2000,2050,4-29-2022");
        sharedBookingsEditor.putString("r94", "r94,2000,2050,4-29-2022");
        sharedBookingsEditor.putString("c95", "c95,2100,2150,4-29-2022");
        sharedBookingsEditor.putString("u95", "u95,2100,2150,4-29-2022");
        sharedBookingsEditor.putString("l95", "l95,2100,2150,4-29-2022");
        sharedBookingsEditor.putString("v95", "v95,2100,2150,4-29-2022");
        sharedBookingsEditor.putString("h95", "h95,2100,2150,4-29-2022");
        sharedBookingsEditor.putString("r95", "r95,2100,2150,4-29-2022");
        sharedBookingsEditor.putString("c96", "c96,1900,1950,4-30-2022");
        sharedBookingsEditor.putString("u96", "u96,1900,1950,4-30-2022");
        sharedBookingsEditor.putString("l96", "l96,1900,1950,4-30-2022");
        sharedBookingsEditor.putString("v96", "v96,1900,1950,4-30-2022");
        sharedBookingsEditor.putString("h96", "h96,1900,1950,4-30-2022");
        sharedBookingsEditor.putString("r96", "r96,1900,1950,4-30-2022");
        sharedBookingsEditor.putString("c97", "c97,2000,2050,4-30-2022");
        sharedBookingsEditor.putString("u97", "u97,2000,2050,4-30-2022");
        sharedBookingsEditor.putString("l97", "l97,2000,2050,4-30-2022");
        sharedBookingsEditor.putString("v97", "v97,2000,2050,4-30-2022");
        sharedBookingsEditor.putString("h97", "h97,2000,2050,4-30-2022");
        sharedBookingsEditor.putString("r97", "r97,2000,2050,4-30-2022");

        sharedBookingsEditor.putString("c98", "c98,2100,2150,4-30-2022,-2");
        sharedBookingsEditor.putString("u98", "u98,2100,2150,4-30-2022,-2");
        sharedBookingsEditor.putString("l98", "l98,2100,2150,4-30-2022,-2");
        sharedBookingsEditor.putString("v98", "v98,2100,2150,4-30-2022,-2");
        sharedBookingsEditor.putString("h98", "h98,2100,2150,4-30-2022,-2");
        sharedBookingsEditor.putString("r98", "r98,2100,2150,4-30-2022,-2");

        sharedBookingsEditor.putString("r99", "r98,2100,2150,4-30-2021,-2");
        sharedBookingsEditor.putString("l99", "r98,2100,2150,4-30-2021,-2");
        sharedBookingsEditor.putString("h99", "r98,2100,2150,4-30-2021,-2");
        sharedBookingsEditor.putString("u99", "r98,2100,2150,4-30-2021,-2");
        sharedBookingsEditor.putString("v99", "r98,2100,2150,4-30-2021,-2");
        sharedBookingsEditor.putString("c99", "r98,2100,2150,4-30-2021,-2");
        sharedBookingsEditor.apply();
        System.out.println("Complete!");
    }
}
