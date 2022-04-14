package com.example.csci310_wangstans;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Vector;
import java.io.File;

public class Reservation {

    private String resEncoding;
    private String startTime;
    private String endTime;
    private String date;
    //1,2100,2150,3282022
    private String recLoc;
    private SharedPreferences bookings;

    public Reservation(String resEncoding, Context context) {

        this.resEncoding=resEncoding;
        this.recLoc=resEncoding.substring(0,1);
        this.bookings=context.getSharedPreferences("sharedBooking", Context.MODE_PRIVATE);
        String resString=bookings.getString(resEncoding, "no res");

        String resArr[]=resString.split(",");

       // this.date=resArr[3];
        this.date=resArr[3];
        this.startTime=resArr[1];
        this.endTime=resArr[2];
        if(recLoc.equals("c")){
            recLoc="Cromwell Track";
        }
        else if(recLoc.equals("l")){
            recLoc="Lyon Center";

        }
        else if(recLoc.equals("u")){
            recLoc="Swimming Pool";

        }
        else if(recLoc.equals("r")){
            recLoc="Racquetball Courts";

        }
        else if(recLoc.equals("v")){
            recLoc="Village Gym";

        }
        else if(recLoc.equals("h")){
            recLoc="HSC Gym";

        }

    }

    public String getResEnc() { return resEncoding; }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getDate() {
        return date;
    }
    public String getLoc() {
        return recLoc;
    }

    public void printReservation(){
//        System.out.println("Start");
//        System.out.println(this.startTime);
//        System.out.println(this.endTime);
//        System.out.println(this.date);
//        System.out.println(this.resEncoding);
//        System.out.println("End");

    }

}
