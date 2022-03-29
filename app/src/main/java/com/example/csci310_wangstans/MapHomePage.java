package com.example.csci310_wangstans;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MapHomePage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapHomePage extends Fragment {

    View hsc, lyon, racquetball, uac, village, track;
    LinearLayout reservations;

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
        View view = inflater.inflate(R.layout.fragment_map_home_page, container, false);

        reservations = (LinearLayout) view.findViewById(R.id.reservation);

        reservations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(MapHomePage.this)
                        .navigate(R.id.action_MapHomePage_to_ReservationFragment);
            }
        });

        hsc = view.findViewById(R.id.hsc);
        uac = view.findViewById(R.id.uac);
        track = view.findViewById(R.id.track);
        village = view.findViewById(R.id.village);
        lyon = view.findViewById(R.id.lyon);
        racquetball = view.findViewById(R.id.racquetball);

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

        return view;
    }
}