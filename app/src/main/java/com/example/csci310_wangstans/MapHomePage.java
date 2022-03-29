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

import com.example.csci310_wangstans.databinding.FragmentMapHomePageBinding;

import java.util.Vector;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MapHomePage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapHomePage extends Fragment {

    View hsc, lyon, racquetball, uac, village, track;
    LinearLayout reservations;

    Vector<Reservation> allUserRes = new Vector<>();
    Vector<Reservation> comingRes = new Vector<>();
    private FragmentMapHomePageBinding binding;

    private SharedPreferences userDB;

    public MapHomePage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MapHomePage.
     */
    // TODO: Rename and change types and number of parameters
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

        if(comingRes.size()==0){
            TextView noResText = new TextView(getContext());
            noResText.setLayoutParams((layoutParams));
            noResText.setGravity(Gravity.CENTER);
            noResText.setText("No upcoming reservations. Try scheduling one.");
            layout.addView(noResText);
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

            if (layout != null) {
                layout.addView(loc);
                layout.addView(dateText);
                layout.addView(timeText);
            }
        }
    }

    private void getUpcomingEvents() {
        userDB=getContext().getSharedPreferences("usersFile", Context.MODE_PRIVATE);
        String userID=userDB.getInt("currentUser",1)+"";
        String upcoming[]=userDB.getString(userID, "none").split(",");

        for(int i=0;i<upcoming.length;i++){
            if(i<=3){
                continue;
            }
            else{
                System.out.println("adding: "+upcoming[i]);
                Reservation res= new Reservation(upcoming[i], getContext());
                if(isUpcoming(res)){
                    comingRes.add(res);
                }
            }
        }
    }

    private boolean isUpcoming(Reservation res) {
        return true;
    }


}