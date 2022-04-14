package com.example.csci310_wangstans;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.csci310_wangstans.databinding.FragmentMapHomePageBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Vector;

public class MapHomePage extends Fragment {

    View hsc, lyon, racquetball, uac, village, track, idto1, idto2;
    LinearLayout reservations;

    Vector<Reservation> allUserRes = new Vector<>();
    Vector<Reservation> comingRes = new Vector<>();
    private FragmentMapHomePageBinding binding;

    private SharedPreferences sharedBookings;
    private SharedPreferences.Editor sharedBookingsEditor;

    private SharedPreferences userDB;
    private SharedPreferences waitManager;
    private SharedPreferences.Editor waitEditor;

    public MapHomePage() {
        // Required empty public constructor
    }

    public static MapHomePage newInstance(String param1, String param2) {
        MapHomePage fragment = new MapHomePage();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //View view = inflater.inflate(R.layout.fragment_map_home_page, container, false);
        binding = FragmentMapHomePageBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedBookings = getContext().getSharedPreferences("sharedBooking", Context.MODE_PRIVATE);
        sharedBookingsEditor = sharedBookings.edit();

        if(!isPopulated()){
            populateRes();
        }
        else{
            System.out.println("already made");
        }

        //makeUsers(-1);
        checkNotif();
        loadQuickView();

        reservations = binding.reservations;
        reservations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(MapHomePage.this)
                        .navigate(R.id.action_MapHomePage_to_ReservationFragment);
            }
        });

        hsc = binding.hsc;
        uac = binding.uac;
        track = binding.track;
        village = binding.village;
        lyon = binding.lyon;
        racquetball = binding.racquetball;
        idto1 = binding.idto1;
        idto2 = binding.idto2;

        hsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(MapHomePage.this)
                        .navigate(R.id.action_MapHomePage_to_HRecCenterFragment);
            }
        });

        uac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(MapHomePage.this)
                        .navigate(R.id.action_MapHomePage_to_URecCenterFragment);
            }
        });

        track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(MapHomePage.this)
                        .navigate(R.id.action_MapHomePage_to_CRecCenterFragment);
            }
        });

        village.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(MapHomePage.this)
                        .navigate(R.id.action_MapHomePage_to_VRecCenterFragment);
            }
        });

        lyon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(MapHomePage.this)
                        .navigate(R.id.action_MapHomePage_to_LRecCenterFragment);
            }
        });

        racquetball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(MapHomePage.this)
                        .navigate(R.id.action_MapHomePage_to_RRecCenterFragment);
            }
        });

        idto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Populator p = new Populator(getContext());
                p.setDirect(-1);
                loadQuickView();

            }
        });

        idto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Populator p = new Populator(getContext());
                p.setDirect(-2);
                loadQuickView();

            }
        });
    }

    private void checkNotif() {
        userDB=getContext().getSharedPreferences("usersFile",Context.MODE_PRIVATE);
        String currUser=userDB.getInt("currUser", -1)+"";

        waitManager = getContext().getSharedPreferences( "waitManager", Context.MODE_PRIVATE);
        if(waitManager.getString(currUser, "none").equals("true")){
            System.out.println("notify user "+currUser);
            Toast.makeText(getActivity().getApplicationContext(), "A session you were on the waitlist for has opened!", Toast.LENGTH_LONG).show();
            waitManager.edit().remove(currUser);
            waitManager.edit().apply();

        }

    }

    private void makeUsers(int i) {
        Populator p = new Populator(getContext());
        p.setCurrentUser(i);
    }


    private void populateRes(){
        Populator p = new Populator(getContext());
        p.populateRes();
    }


    public boolean isPopulated() {

        if(sharedBookings.getString("c0", "none").equals("none")){
            return false;
        }
        return true;
    }

    private void loadQuickView() {
        //load info

        allUserRes.clear();
        comingRes.clear();
        getUpcomingEvents();
        loadWindow();
    }

    private void loadWindow() {
        LinearLayout layout=binding.reservations;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10,10,10,0);

        if(comingRes.size()==0){
            TextView noResText = new TextView(getContext());
            noResText.setLayoutParams((layoutParams));
            noResText.setGravity(Gravity.CENTER);
            noResText.setText("No upcoming reservations.\nTry scheduling one.");
            layout.addView(noResText);

            TextView title = binding.upcomingReservations;
            title.setText("No Upcoming Reservations");
            return;
        }
        for(int i=0;i<comingRes.size();i++){

            Reservation res=comingRes.get(i);
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
            loc.setText(res.getLoc());

            TextView dateText = new TextView(getContext());
            dateText.setLayoutParams((layoutParams));
            dateText.setGravity(Gravity.CENTER);
            dateText.setText(res.getDate());

            TextView buffer = new TextView(getContext());
            buffer.setLayoutParams(layoutParams);
            buffer.setGravity(Gravity.CENTER);
            buffer.setText("-----------------------------------------");

            if (layout != null) {
                layout.addView(loc);
                layout.addView(dateText);
                layout.addView(timeText);
                layout.addView(buffer);
            }
        }
        TextView title = binding.upcomingReservations;
        title.setText("Upcoming Reservations");
    }

    public void getUpcomingEvents() {
        userDB=getContext().getSharedPreferences("usersFile", Context.MODE_PRIVATE);
        String userID=userDB.getInt("currUser",1)+"";
        String upcoming[]=userDB.getString(userID, "none").split(",");

        for(int i=0;i<upcoming.length;i++){
            if(i<=3){
                continue;
            }
            else{
                Reservation res= new Reservation(upcoming[i], getContext());
                if(isUpcoming(res)){
                    comingRes.add(res);
                }
            }
        }
    }

    public Vector<Reservation> getComingRes() {
        return comingRes;
    }

    private boolean isUpcoming(Reservation res) {
//
//        LocalDate date = LocalDate.now();
//
//        int currYr = date.getYear();
//        int currMonth = date.getMonthValue();
//        int currDay = date.getDayOfMonth();
//
//        String resDate = res.getDate();
//
//        String dateHold[] = resDate.split("-");
//
//        int resYr = Integer.parseInt(dateHold[2]);
//        int resMonth = Integer.parseInt(dateHold[0]);
//        int resDay = Integer.parseInt(dateHold[1]);
//        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
//        Date resD = new Date();
//        Date currD = new Date();
//
//        try {
//            resD = sdformat.parse(resYr + "-" + resMonth + "-" + resDay);
//            currD = sdformat.parse(currYr + "-" + currMonth + "-" + currDay);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        if (resD.compareTo(currD) > 0) {
//            System.out.println("res occurs after curr");
//            return false;
//        } else if (resD.compareTo(currD) < 0) {
//            System.out.println("res occurs before curr");
//            return true;
//        } else if (resD.compareTo(currD) == 0) {
//
//            System.out.println("Both dates are equal");
//            LocalTime time = LocalTime.now();
//
//            int currH = time.getHour();
//            int currM = time.getMinute();
//            int resH = Integer.parseInt(res.getStartTime().substring(0, 2));
//            int resM = Integer.parseInt(res.getStartTime().substring(3, 5));
//            if (currH > resH) {
//                return true;
//            } else if (currH == resH) {
//                if (currM > resM) {
//                    return true;
//                } else {
//                    return false;
//                }
//            } else {
//                return false;
//            }
//        }
//        return true;
        return true;
    }

}