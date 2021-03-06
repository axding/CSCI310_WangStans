package com.example.csci310_wangstans;

import android.app.ActionBar;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;


import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
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
    private SharedPreferences bookingDB;
    private SharedPreferences waitDB;
    private SharedPreferences waitManager;
    private ImageButton home, about, profile, back;
    SharedPreferences.Editor editor;
    SharedPreferences.Editor waitEditor;
    SharedPreferences.Editor bookEditor;
    View idto1r, idto2r;

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


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        populateRes();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_reservation, container, false);
        binding = FragmentReservationBinding.inflate(inflater, container, false);

        idto1r=binding.idto1r;
        idto2r=binding.idto2r;
        return binding.getRoot();
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Nav bar
        home = binding.home;
        about = binding.about;
        profile = binding.profile;
        back = binding.back;

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(ReservationFragment.this)
                        .navigate(R.id.action_ReservationFragment_to_MapHomePage);
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(ReservationFragment.this)
                        .navigate(R.id.action_ReservationFragment_to_AboutFragment);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(ReservationFragment.this)
                        .navigate(R.id.action_ReservationFragment_to_ProfileFragment);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(ReservationFragment.this)
                        .navigate(R.id.action_ReservationFragment_to_MapHomePage);
            }
        });
        // End of Nav Bar

        Calendar today = Calendar.getInstance();

        idto1r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Populator p = new Populator(getContext());
                p.setDirect(-1);
                System.out.println("+++++++++++++++++++++++++++++++++++");

                p.populateRes();
                populateRes();

            }
        });

        idto2r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("---------------------------------------------");

                Populator m = new Populator(getContext());
                m.setDirect(-2);
                m.populateRes();
                populateRes();

            }
        });

        binding.pastResButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                populateRes();

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
            TextView noResText = new TextView(getContext());
            noResText.setLayoutParams((layoutParams));
            noResText.setGravity(Gravity.CENTER);
            noResText.setText("No upcoming reservations. Try scheduling one.");
            noResText.setTextColor(Color.WHITE);
            layout.addView(noResText);

//            Button button = binding.comingResButton;
//            button.setText("No Upcoming Reservations");
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
            timeText.setTextColor(Color.WHITE);

            TextView loc = new TextView(getContext());
            loc.setLayoutParams((layoutParams));
            loc.setGravity(Gravity.CENTER);
            loc.setText(recLoc);
            loc.setTextColor(Color.WHITE);

            TextView dateText = new TextView(getContext());
            dateText.setLayoutParams((layoutParams));
            dateText.setGravity(Gravity.CENTER);
            dateText.setText(res.getDate());
            dateText.setTextColor(Color.WHITE);

            Button cancelButton = new Button(getContext());
            LinearLayout.LayoutParams buttonLP = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            buttonLP.gravity = Gravity.CENTER;
            buttonLP.setMargins(0,10,0,50);
            cancelButton.setLayoutParams(buttonLP);
            cancelButton.setWidth(500);
            cancelButton.setBackgroundColor(getResources().getColor(R.color.yellow));

            cancelButton.setText("Cancel Reservation");
            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    cancelButton.setEnabled(false);
                    cancelButton.setText("Reservation Cancelled.");

                    String cancelEnc=res.getResEnc();

                    //get user string
                    int currUser=userDB.getInt("currUser", -1);
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
                    //push back into pref
                    editor = userDB.edit();
                    //System.out.println(newString);
                    editor.putString(currUser+"", newString);
                    editor.apply();

                    //remove user from bookings db
                    String bookingString="";
                    bookingDB = getContext().getSharedPreferences( "sharedBooking", Context.MODE_PRIVATE);

                    bookingString=bookingDB.getString(cancelEnc, "can't find one");
                    //System.out.println(bookingString);
                    String bookArr[]=bookingString.split(",");
                    bookingString="";

                    //remove int currUser
                    for(int i=0;i<bookArr.length;i++){
                        if(bookArr[i].equals(currUser+"")){
                            continue;
                        }
                        else{
                            bookingString+=bookArr[i]+",";
                        }
                    }


                    bookingString=bookingString.substring(0,bookingString.length()-1);

                    bookEditor = bookingDB.edit();
                    bookEditor.putString(cancelEnc+"", bookingString);
                    bookEditor.apply();

                    //get waitlistpref
                    waitDB=getContext().getSharedPreferences("sharedWaitlist",Context.MODE_PRIVATE);
                    String waitUsers=waitDB.getString(cancelEnc, "nowait");

                    //notify users if any
                    if(waitUsers.equals("nowait")){
                        System.out.println("No users on waitlist!");
                    }
                    else{
                        System.out.println("Notifying user(s): "+waitUsers);
                        String waitingUsers[]=waitUsers.split(",");
                        waitManager = getContext().getSharedPreferences( "waitManager", Context.MODE_PRIVATE);
                        waitEditor = waitManager.edit();

                        for(int i=0;i<waitingUsers.length;i++){
                            waitEditor.putString(waitingUsers[i]+"", cancelEnc);
                        }
                        waitEditor.apply();

                    }
                }
            });

            if (layout != null) {
                Button button = binding.comingResButton;
                button.setText("Upcoming Reservations");
                layout.addView(loc);
                layout.addView(dateText);
                layout.addView(timeText);
                layout.addView(cancelButton);
            }
        }

        Button button = binding.comingResButton;
        button.setText("Upcoming Reservations");
    }

    private void showPastRes(){
        LinearLayout layout=binding.resDisplay;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        if(pastRes.size()==0){
            TextView noResText = new TextView(getContext());
            noResText.setLayoutParams((layoutParams));
            noResText.setGravity(Gravity.CENTER);
            noResText.setText("No past reservations.");
            noResText.setTextColor(Color.WHITE);
            layout.addView(noResText);

//            Button button = binding.pastResButton;
//            button.setText("No Past Reservations");
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
            timeText.setTextColor(Color.WHITE);

            TextView loc = new TextView(getContext());
            loc.setLayoutParams((layoutParams));
            loc.setGravity(Gravity.CENTER);
            loc.setText(recLoc);
            loc.setTextColor(Color.WHITE);

            TextView dateText = new TextView(getContext());
            dateText.setLayoutParams((layoutParams));
            dateText.setGravity(Gravity.CENTER);
            dateText.setText(res.getDate());
            dateText.setTextColor(Color.WHITE);

            TextView buffer = new TextView(getContext());
            buffer.setLayoutParams((layoutParams));
            buffer.setGravity(Gravity.CENTER);
            buffer.setText("------------");
            buffer.setTextColor(Color.WHITE);

            if (layout != null) {
                Button button = binding.pastResButton;
                button.setText("Past Reservations");
                layout.addView(loc);
                layout.addView(dateText);

                layout.addView(timeText);
                layout.addView(buffer);

            }
        }

        Button button = binding.pastResButton;
        button.setText("Past Reservations");

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

        if(userInfo.length==5){
            return;
        }


        for(int i=5;i<userInfo.length;i++) {
            resIDs.add(userInfo[i]);
        }

        String test="c0";


        for(int i=0;i<resIDs.size();i++){
            allUserRes.add(new Reservation(resIDs.get(i), getContext()));
            //allUserRes.get(i).printReservation();
        }


        //get current time
        Calendar today = Calendar.getInstance();
        LocalDate date=LocalDate.now();
        LocalTime time=LocalTime.now();

        //organize reservations

        for(int i=0;i<allUserRes.size();i++){

            if(isBeforeNow(allUserRes.get(i), date, time)){
                pastRes.add(allUserRes.get(i));
            }
            else{
                comingRes.add(allUserRes.get(i));

            }
        }

        //0 is userId
        //1 is name
        //2 is email
        //3 password
        //4 is usc id
        //5+ is reservations

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
            return false;
        } else if(resD.compareTo(currD) < 0) {
            return true;
        } else if(resD.compareTo(currD) == 0) {
            return false;

//            int currH= time.getHour();
//            int currM= time.getMinute();
//            int resH = Integer.parseInt(reservation.getStartTime().substring(0,2));
//            int resM = Integer.parseInt(reservation.getStartTime().substring(3,5));
//            if(currH>resH){
//                return true;
//            }
//            else if(currH==resH){
//                if(currM>resM){
//                    return true;
//                }
//                else{
//                    return false;
//                }
//            }
//            else{
//                return false;
//            }


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
        int currUserID=userDB.getInt("currUser", -1);
        String userString=userDB.getString(currUserID+"","none");
        //System.out.println("used here:" +userString);
        return userString;


    }

}