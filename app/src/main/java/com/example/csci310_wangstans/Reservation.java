package com.example.csci310_wangstans;

import android.content.Context;

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
    public Reservation(String resEncoding, Context context) {
        this.resEncoding=resEncoding;

        String filename=resEncoding.substring(0, 1)+"ResDB.txt";
        String resNum=resEncoding.substring(1,2);

        try {
            InputStream is = context.getAssets().open("db/"+filename);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line = reader.readLine();
            while(line != null){
                System.out.println(line);
                String[] resInfo = line.split(",");
                if(resInfo[0].equals(resNum)){
                    this.startTime=resInfo[1];
                    this.endTime=resInfo[2];
                    this.date=resInfo[3];
                    break;
                }
                line = reader.readLine();
            }

            is.close();
            reader.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
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

    public void printReservation(){
        System.out.println("Start");
        System.out.println(this.startTime);
        System.out.println(this.endTime);
        System.out.println(this.date);
        System.out.println(this.resEncoding);
        System.out.println("End");

    }

}
