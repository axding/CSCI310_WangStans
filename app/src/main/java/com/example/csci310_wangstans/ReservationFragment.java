package com.example.csci310_wangstans;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;


import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import com.example.csci310_wangstans.databinding.FragmentReservationBinding;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link ReservationFragment#newInstance} factory method to
// * create an instance of this fragment.
// */


public class ReservationFragment extends Fragment {
    private SharedPreferences userDB;
    private SharedPreferences waitDB;
    SharedPreferences.Editor editor;

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
    private FragmentReservationBinding binding;
    Vector<Reservation> allUserRes = new Vector<>();
    Vector<Reservation> pastRes = new Vector<>();
    Vector<Reservation> comingRes = new Vector<>();

    public ReservationFragment() {
        // Required empty public constructor
    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment ReservationFragment.
//     */
    // TODO: Rename and change types and number of parameters
//    public static ReservationFragment newInstance(String param1, String param2) {
//        ReservationFragment fragment = new ReservationFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        populateRes();

//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_reservation, container, false);
        binding = FragmentReservationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        System.out.println("asdf");
        Calendar today = Calendar.getInstance();


        binding.pastResButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                populateRes();

//                LinearLayout ll = binding.bookingDisplay;
//                ll.removeAllViews();
//                String date = (datePicker.getMonth()+1) + "" + datePicker.getDayOfMonth() + "" + datePicker.getYear();
                //readReservationFile(date);
                LinearLayout ll = binding.resDisplay;
                ll.removeAllViews();
                showPastRes();
            }
        });
        binding.comingResButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                populateRes();

                LinearLayout ll = binding.resDisplay;
                ll.removeAllViews();
//                String date = (datePicker.getMonth()+1) + "" + datePicker.getDayOfMonth() + "" + datePicker.getYear();
//                readReservationFile(date);
                showComingRes();
            }
        });
    }
    private void showComingRes(){
        LinearLayout layout=binding.resDisplay;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        if(comingRes.size()==0){
            System.out.println("no upcoming");
            TextView noResText = new TextView(getContext());
            noResText.setLayoutParams((layoutParams));
            noResText.setGravity(Gravity.CENTER);
            noResText.setText("No upcoming reservations. Try scheduling one.");
            layout.addView(noResText);
            return;
        }
        for(int i = 0; i< comingRes.size(); i++) {

            Reservation res = comingRes.get(i);
            String recLoc=res.getResEnc().substring(0,1);
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
                recLoc="Raquetball Courts";

            }
            else if(recLoc.equals("v")){
                recLoc="Village Gym";

            }
            else if(recLoc.equals("h")){
                recLoc="HSC Gym";

            }
            String start=res.getStartTime().substring(0,2)+":"+res.getStartTime().substring(2,4);
            String end=res.getEndTime().substring(0,2)+":"+res.getEndTime().substring(2,4);
            String time = start+ " - " + end;

            TextView timeText = new TextView(getContext());
            timeText.setLayoutParams(layoutParams);
            timeText.setGravity(Gravity.CENTER);
            timeText.setText(time);

            TextView loc = new TextView(getContext());
            loc.setLayoutParams((layoutParams));
            loc.setGravity(Gravity.CENTER);
            loc.setText(recLoc);

            TextView dateText = new TextView(getContext());
            dateText.setLayoutParams((layoutParams));
            dateText.setGravity(Gravity.CENTER);
            dateText.setText(res.getDate());


            Button cancelButton = new Button(getContext());
            cancelButton.setLayoutParams(layoutParams);

            cancelButton.setText("Cancel Reservation");
            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    cancelButton.setEnabled(false);
                    cancelButton.setText("Reservation Cancelled.");
                    System.out.println(res.getResEnc());

                    String cancelEnc=res.getResEnc();

                    //get user string
                    int currUser=userDB.getInt("currentUser", -1);
                    String userString=userDB.getString(currUser+"", "none");

                    //remove the enc
                    String newString="";
                    String userArr[]=userString.split(",");
                    for(int i=0;i<userArr.length;i++){
                        if(userArr[i].equals(cancelEnc)){
                           continue;
                        }
                        else{
                            newString+=userArr[i]+",";
                        }
                    }
                    newString=newString.substring(0,newString.length()-1);
                    System.out.println(newString);
                    //push back into pref
                    editor = userDB.edit();
                    editor.putString(currUser+"", newString);
                    editor.apply();


                    //get waitlistpref
                    waitDB=getContext().getSharedPreferences("sharedWaitlist",Context.MODE_PRIVATE);
                    String waitUsers=waitDB.getString(cancelEnc, "nowait");

                    //notify users if any
                    if(waitUsers.equals("nowait")){
                        System.out.println("No users on waitlist!");
                    }
                    else{
                        System.out.println("Notifying"+waitUsers);

                        //lets notify users
                    }
                }
            });



            if (layout != null) {
                layout.addView(loc);
                layout.addView(dateText);
                layout.addView(timeText);
                layout.addView(cancelButton);
            }
        }
    }

    private void showPastRes(){
        LinearLayout layout=binding.resDisplay;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        if(pastRes.size()==0){
            System.out.println("no past");
            TextView noResText = new TextView(getContext());
            noResText.setLayoutParams((layoutParams));
            noResText.setGravity(Gravity.CENTER);
            noResText.setText("No past reservations.");
            layout.addView(noResText);
            return;
        }
        for(int i = 0; i< pastRes.size(); i++) {

            Reservation res = pastRes.get(i);
            String recLoc=res.getResEnc().substring(0,1);
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
                recLoc="Raquetball Courts";

            }
            else if(recLoc.equals("v")){
                recLoc="Village Gym";

            }
            else if(recLoc.equals("h")){
                recLoc="HSC Gym";

            }

            String start=res.getStartTime().substring(0,2)+":"+res.getStartTime().substring(2,4);
            String end=res.getEndTime().substring(0,2)+":"+res.getEndTime().substring(2,4);
            String time = start+ " - " + end;
            TextView timeText = new TextView(getContext());
            timeText.setLayoutParams(layoutParams);
            timeText.setGravity(Gravity.CENTER);
            timeText.setText(time);

            TextView loc = new TextView(getContext());
            loc.setLayoutParams((layoutParams));
            loc.setGravity(Gravity.CENTER);
            loc.setText(recLoc);

            TextView dateText = new TextView(getContext());
            dateText.setLayoutParams((layoutParams));
            dateText.setGravity(Gravity.CENTER);
            dateText.setText(res.getDate());

            TextView buffer = new TextView(getContext());
            buffer.setLayoutParams((layoutParams));
            buffer.setGravity(Gravity.CENTER);
            buffer.setText("------------");

            if (layout != null) {
                layout.addView(loc);
                layout.addView(dateText);

                layout.addView(timeText);
                layout.addView(buffer);

            }
        }

    }
    private void populateRes(){
        allUserRes.clear();
        pastRes.clear();
        comingRes.clear();
        int userID=1;
        String info=findUserInfo();

        //Vector<Booking> userRes = new Vector<>();
        Vector<String> resIDs = new Vector<>();

        String[] userInfo = info.split(",");
        System.out.println(info);
        for(int i=0;i<userInfo.length;i++){
            System.out.println(userInfo[i]);
        }

        if(userInfo.length==4){
            System.out.println("No reservations!");
            return;
        }

        System.out.println("We have something");

        for(int i=0;i<userInfo.length;i++){
            if(i<=3){
                continue;
            }
            else{
                resIDs.add(userInfo[i]);
            }
        }

        System.out.println("Here are the reservation id's");
        for(int i=0;i<resIDs.size();i++){
            System.out.println(resIDs.get(i));
        }

        String test="c0";

        System.out.println("Making reservations");

        for(int i=0;i<resIDs.size();i++){
            allUserRes.add(new Reservation(resIDs.get(i), getContext()));
            allUserRes.get(i).printReservation();
        }


        //get current time
        Calendar today = Calendar.getInstance();
        LocalDate date=LocalDate.now();
        LocalTime time=LocalTime.now();
        System.out.println(date); // Display the current date

        //organize reservations

        for(int i=0;i<allUserRes.size();i++){

            if(isBeforeNow(allUserRes.get(i), date, time)){
                System.out.println("added to past");
                pastRes.add(allUserRes.get(i));
            }
            else{
                comingRes.add(allUserRes.get(i));
                System.out.println("added to coming");

            }
        }



        //0 is userId
        //1 is name
        //2 is email
        //3 password
        //4+ is reservations


    }

    private boolean isBeforeNow(Reservation reservation, LocalDate date, LocalTime time){
        int currYr=date.getYear();
        int currMonth=date.getMonthValue();
        int currDay=date.getDayOfMonth();

        String resDate=reservation.getDate();

        String dateHold[]=resDate.split("-");

        int resYr=Integer.parseInt(dateHold[2]);
        int resMonth=Integer.parseInt(dateHold[0]);
        int resDay=Integer.parseInt(dateHold[1]);
        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
        Date resD = new Date();
        Date currD = new Date();

        try {
            resD = sdformat.parse(resYr+"-"+resMonth+"-"+resDay);
            currD = sdformat.parse(currYr+"-"+currMonth+"-"+currDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(resD.compareTo(currD) > 0) {
            System.out.println("res occurs after curr");
            return false;
        } else if(resD.compareTo(currD) < 0) {
            System.out.println("res occurs before curr");
            return true;
        } else if(resD.compareTo(currD) == 0) {

            System.out.println("Both dates are equal");
            int currH= time.getHour();
            int currM= time.getMinute();
            int resH = Integer.parseInt(reservation.getStartTime().substring(0,2));
            int resM = Integer.parseInt(reservation.getStartTime().substring(3,5));
            if(currH<resH){
                return true;
            }
            else if(currH==resH){
                if(currM<resM){
                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                return false;
            }


//            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
//            Date d1 = new Date();
//            Date d2 = new Date();
//
//            try {
//                d1 = sdf.parse(startTime);
//                d2 = sdf.parse(startTime);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//            long elapsed = d2.getTime() - d1.getTime();


        }


        return false;
    }



    private String findUserInfo(){
        String results="";


        userDB = getContext().getSharedPreferences("usersFile", Context.MODE_PRIVATE);
        int currUserID=userDB.getInt("currentUser", -1);
        String userString=userDB.getString(currUserID+"","none");
        System.out.println("user info here: "+userString);
        return userString;

//        try {
//            InputStream is = getContext().getAssets().open("db/user.txt");
//            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//            String line = reader.readLine();
//            while(line != null){
//                //System.out.println("Next line:"+line);
//                String[] resInfo = line.split(", ");
////                for(int i=0;i<resInfo.length;i++) {
////                    System.out.println(resInfo[i]);
////                }
//                //System.out.println();
//                if(resInfo[0].equals((userID+""))){
//                    //found the user
//                    //System.out.println("user found!");
//                    results=line;
//                    return results;
//                }
//                line = reader.readLine();
//
//            }
//
//
//            is.close();
//            reader.close();
//        }
//        catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return "noUser";

    }

}