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

    private SharedPreferences sharedBookings;
    private SharedPreferences.Editor sharedBookingsEditor;

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
        sharedBookings = getContext().getSharedPreferences("sharedBooking", Context.MODE_PRIVATE);
        sharedBookingsEditor = sharedBookings.edit();

        if(!isPopulated()){
            populateRes();
        }
        else{
            System.out.println("already made");
        }
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

    private void populateRes() {
        System.out.println("Generating...");
        sharedBookingsEditor.putString("c0", "c0, 1900, 1950, 3-29-2022");
        sharedBookingsEditor.putString("u0", "u0, 1900, 1950, 3-29-2022");
        sharedBookingsEditor.putString("l0", "l0, 1900, 1950, 3-29-2022");
        sharedBookingsEditor.putString("v0", "v0, 1900, 1950, 3-29-2022");
        sharedBookingsEditor.putString("h0", "h0, 1900, 1950, 3-29-2022");
        sharedBookingsEditor.putString("r0", "r0, 1900, 1950, 3-29-2022");
        sharedBookingsEditor.putString("c0", "c0, 2000, 2050, 3-29-2022");
        sharedBookingsEditor.putString("u0", "u0, 2000, 2050, 3-29-2022");
        sharedBookingsEditor.putString("l0", "l0, 2000, 2050, 3-29-2022");
        sharedBookingsEditor.putString("v0", "v0, 2000, 2050, 3-29-2022");
        sharedBookingsEditor.putString("h0", "h0, 2000, 2050, 3-29-2022");
        sharedBookingsEditor.putString("r0", "r0, 2000, 2050, 3-29-2022");
        sharedBookingsEditor.putString("c0", "c0, 2100, 2150, 3-29-2022");
        sharedBookingsEditor.putString("u0", "u0, 2100, 2150, 3-29-2022");
        sharedBookingsEditor.putString("l0", "l0, 2100, 2150, 3-29-2022");
        sharedBookingsEditor.putString("v0", "v0, 2100, 2150, 3-29-2022");
        sharedBookingsEditor.putString("h0", "h0, 2100, 2150, 3-29-2022");
        sharedBookingsEditor.putString("r0", "r0, 2100, 2150, 3-29-2022");
        sharedBookingsEditor.putString("c1", "c1, 1900, 1950, 3-30-2022");
        sharedBookingsEditor.putString("u1", "u1, 1900, 1950, 3-30-2022");
        sharedBookingsEditor.putString("l1", "l1, 1900, 1950, 3-30-2022");
        sharedBookingsEditor.putString("v1", "v1, 1900, 1950, 3-30-2022");
        sharedBookingsEditor.putString("h1", "h1, 1900, 1950, 3-30-2022");
        sharedBookingsEditor.putString("r1", "r1, 1900, 1950, 3-30-2022");
        sharedBookingsEditor.putString("c1", "c1, 2000, 2050, 3-30-2022");
        sharedBookingsEditor.putString("u1", "u1, 2000, 2050, 3-30-2022");
        sharedBookingsEditor.putString("l1", "l1, 2000, 2050, 3-30-2022");
        sharedBookingsEditor.putString("v1", "v1, 2000, 2050, 3-30-2022");
        sharedBookingsEditor.putString("h1", "h1, 2000, 2050, 3-30-2022");
        sharedBookingsEditor.putString("r1", "r1, 2000, 2050, 3-30-2022");
        sharedBookingsEditor.putString("c1", "c1, 2100, 2150, 3-30-2022");
        sharedBookingsEditor.putString("u1", "u1, 2100, 2150, 3-30-2022");
        sharedBookingsEditor.putString("l1", "l1, 2100, 2150, 3-30-2022");
        sharedBookingsEditor.putString("v1", "v1, 2100, 2150, 3-30-2022");
        sharedBookingsEditor.putString("h1", "h1, 2100, 2150, 3-30-2022");
        sharedBookingsEditor.putString("r1", "r1, 2100, 2150, 3-30-2022");
        sharedBookingsEditor.putString("c2", "c2, 1900, 1950, 3-31-2022");
        sharedBookingsEditor.putString("u2", "u2, 1900, 1950, 3-31-2022");
        sharedBookingsEditor.putString("l2", "l2, 1900, 1950, 3-31-2022");
        sharedBookingsEditor.putString("v2", "v2, 1900, 1950, 3-31-2022");
        sharedBookingsEditor.putString("h2", "h2, 1900, 1950, 3-31-2022");
        sharedBookingsEditor.putString("r2", "r2, 1900, 1950, 3-31-2022");
        sharedBookingsEditor.putString("c2", "c2, 2000, 2050, 3-31-2022");
        sharedBookingsEditor.putString("u2", "u2, 2000, 2050, 3-31-2022");
        sharedBookingsEditor.putString("l2", "l2, 2000, 2050, 3-31-2022");
        sharedBookingsEditor.putString("v2", "v2, 2000, 2050, 3-31-2022");
        sharedBookingsEditor.putString("h2", "h2, 2000, 2050, 3-31-2022");
        sharedBookingsEditor.putString("r2", "r2, 2000, 2050, 3-31-2022");
        sharedBookingsEditor.putString("c2", "c2, 2100, 2150, 3-31-2022");
        sharedBookingsEditor.putString("u2", "u2, 2100, 2150, 3-31-2022");
        sharedBookingsEditor.putString("l2", "l2, 2100, 2150, 3-31-2022");
        sharedBookingsEditor.putString("v2", "v2, 2100, 2150, 3-31-2022");
        sharedBookingsEditor.putString("h2", "h2, 2100, 2150, 3-31-2022");
        sharedBookingsEditor.putString("r2", "r2, 2100, 2150, 3-31-2022");
        sharedBookingsEditor.putString("c3", "c3, 1900, 1950, 4-1-2022");
        sharedBookingsEditor.putString("u3", "u3, 1900, 1950, 4-1-2022");
        sharedBookingsEditor.putString("l3", "l3, 1900, 1950, 4-1-2022");
        sharedBookingsEditor.putString("v3", "v3, 1900, 1950, 4-1-2022");
        sharedBookingsEditor.putString("h3", "h3, 1900, 1950, 4-1-2022");
        sharedBookingsEditor.putString("r3", "r3, 1900, 1950, 4-1-2022");
        sharedBookingsEditor.putString("c3", "c3, 2000, 2050, 4-1-2022");
        sharedBookingsEditor.putString("u3", "u3, 2000, 2050, 4-1-2022");
        sharedBookingsEditor.putString("l3", "l3, 2000, 2050, 4-1-2022");
        sharedBookingsEditor.putString("v3", "v3, 2000, 2050, 4-1-2022");
        sharedBookingsEditor.putString("h3", "h3, 2000, 2050, 4-1-2022");
        sharedBookingsEditor.putString("r3", "r3, 2000, 2050, 4-1-2022");
        sharedBookingsEditor.putString("c3", "c3, 2100, 2150, 4-1-2022");
        sharedBookingsEditor.putString("u3", "u3, 2100, 2150, 4-1-2022");
        sharedBookingsEditor.putString("l3", "l3, 2100, 2150, 4-1-2022");
        sharedBookingsEditor.putString("v3", "v3, 2100, 2150, 4-1-2022");
        sharedBookingsEditor.putString("h3", "h3, 2100, 2150, 4-1-2022");
        sharedBookingsEditor.putString("r3", "r3, 2100, 2150, 4-1-2022");
        sharedBookingsEditor.putString("c4", "c4, 1900, 1950, 4-2-2022");
        sharedBookingsEditor.putString("u4", "u4, 1900, 1950, 4-2-2022");
        sharedBookingsEditor.putString("l4", "l4, 1900, 1950, 4-2-2022");
        sharedBookingsEditor.putString("v4", "v4, 1900, 1950, 4-2-2022");
        sharedBookingsEditor.putString("h4", "h4, 1900, 1950, 4-2-2022");
        sharedBookingsEditor.putString("r4", "r4, 1900, 1950, 4-2-2022");
        sharedBookingsEditor.putString("c4", "c4, 2000, 2050, 4-2-2022");
        sharedBookingsEditor.putString("u4", "u4, 2000, 2050, 4-2-2022");
        sharedBookingsEditor.putString("l4", "l4, 2000, 2050, 4-2-2022");
        sharedBookingsEditor.putString("v4", "v4, 2000, 2050, 4-2-2022");
        sharedBookingsEditor.putString("h4", "h4, 2000, 2050, 4-2-2022");
        sharedBookingsEditor.putString("r4", "r4, 2000, 2050, 4-2-2022");
        sharedBookingsEditor.putString("c4", "c4, 2100, 2150, 4-2-2022");
        sharedBookingsEditor.putString("u4", "u4, 2100, 2150, 4-2-2022");
        sharedBookingsEditor.putString("l4", "l4, 2100, 2150, 4-2-2022");
        sharedBookingsEditor.putString("v4", "v4, 2100, 2150, 4-2-2022");
        sharedBookingsEditor.putString("h4", "h4, 2100, 2150, 4-2-2022");
        sharedBookingsEditor.putString("r4", "r4, 2100, 2150, 4-2-2022");
        sharedBookingsEditor.putString("c5", "c5, 1900, 1950, 4-3-2022");
        sharedBookingsEditor.putString("u5", "u5, 1900, 1950, 4-3-2022");
        sharedBookingsEditor.putString("l5", "l5, 1900, 1950, 4-3-2022");
        sharedBookingsEditor.putString("v5", "v5, 1900, 1950, 4-3-2022");
        sharedBookingsEditor.putString("h5", "h5, 1900, 1950, 4-3-2022");
        sharedBookingsEditor.putString("r5", "r5, 1900, 1950, 4-3-2022");
        sharedBookingsEditor.putString("c5", "c5, 2000, 2050, 4-3-2022");
        sharedBookingsEditor.putString("u5", "u5, 2000, 2050, 4-3-2022");
        sharedBookingsEditor.putString("l5", "l5, 2000, 2050, 4-3-2022");
        sharedBookingsEditor.putString("v5", "v5, 2000, 2050, 4-3-2022");
        sharedBookingsEditor.putString("h5", "h5, 2000, 2050, 4-3-2022");
        sharedBookingsEditor.putString("r5", "r5, 2000, 2050, 4-3-2022");
        sharedBookingsEditor.putString("c5", "c5, 2100, 2150, 4-3-2022");
        sharedBookingsEditor.putString("u5", "u5, 2100, 2150, 4-3-2022");
        sharedBookingsEditor.putString("l5", "l5, 2100, 2150, 4-3-2022");
        sharedBookingsEditor.putString("v5", "v5, 2100, 2150, 4-3-2022");
        sharedBookingsEditor.putString("h5", "h5, 2100, 2150, 4-3-2022");
        sharedBookingsEditor.putString("r5", "r5, 2100, 2150, 4-3-2022");
        sharedBookingsEditor.putString("c6", "c6, 1900, 1950, 4-4-2022");
        sharedBookingsEditor.putString("u6", "u6, 1900, 1950, 4-4-2022");
        sharedBookingsEditor.putString("l6", "l6, 1900, 1950, 4-4-2022");
        sharedBookingsEditor.putString("v6", "v6, 1900, 1950, 4-4-2022");
        sharedBookingsEditor.putString("h6", "h6, 1900, 1950, 4-4-2022");
        sharedBookingsEditor.putString("r6", "r6, 1900, 1950, 4-4-2022");
        sharedBookingsEditor.putString("c6", "c6, 2000, 2050, 4-4-2022");
        sharedBookingsEditor.putString("u6", "u6, 2000, 2050, 4-4-2022");
        sharedBookingsEditor.putString("l6", "l6, 2000, 2050, 4-4-2022");
        sharedBookingsEditor.putString("v6", "v6, 2000, 2050, 4-4-2022");
        sharedBookingsEditor.putString("h6", "h6, 2000, 2050, 4-4-2022");
        sharedBookingsEditor.putString("r6", "r6, 2000, 2050, 4-4-2022");
        sharedBookingsEditor.putString("c6", "c6, 2100, 2150, 4-4-2022");
        sharedBookingsEditor.putString("u6", "u6, 2100, 2150, 4-4-2022");
        sharedBookingsEditor.putString("l6", "l6, 2100, 2150, 4-4-2022");
        sharedBookingsEditor.putString("v6", "v6, 2100, 2150, 4-4-2022");
        sharedBookingsEditor.putString("h6", "h6, 2100, 2150, 4-4-2022");
        sharedBookingsEditor.putString("r6", "r6, 2100, 2150, 4-4-2022");
        sharedBookingsEditor.putString("c7", "c7, 1900, 1950, 4-5-2022");
        sharedBookingsEditor.putString("u7", "u7, 1900, 1950, 4-5-2022");
        sharedBookingsEditor.putString("l7", "l7, 1900, 1950, 4-5-2022");
        sharedBookingsEditor.putString("v7", "v7, 1900, 1950, 4-5-2022");
        sharedBookingsEditor.putString("h7", "h7, 1900, 1950, 4-5-2022");
        sharedBookingsEditor.putString("r7", "r7, 1900, 1950, 4-5-2022");
        sharedBookingsEditor.putString("c7", "c7, 2000, 2050, 4-5-2022");
        sharedBookingsEditor.putString("u7", "u7, 2000, 2050, 4-5-2022");
        sharedBookingsEditor.putString("l7", "l7, 2000, 2050, 4-5-2022");
        sharedBookingsEditor.putString("v7", "v7, 2000, 2050, 4-5-2022");
        sharedBookingsEditor.putString("h7", "h7, 2000, 2050, 4-5-2022");
        sharedBookingsEditor.putString("r7", "r7, 2000, 2050, 4-5-2022");
        sharedBookingsEditor.putString("c7", "c7, 2100, 2150, 4-5-2022");
        sharedBookingsEditor.putString("u7", "u7, 2100, 2150, 4-5-2022");
        sharedBookingsEditor.putString("l7", "l7, 2100, 2150, 4-5-2022");
        sharedBookingsEditor.putString("v7", "v7, 2100, 2150, 4-5-2022");
        sharedBookingsEditor.putString("h7", "h7, 2100, 2150, 4-5-2022");
        sharedBookingsEditor.putString("r7", "r7, 2100, 2150, 4-5-2022");
        sharedBookingsEditor.putString("c8", "c8, 1900, 1950, 4-6-2022");
        sharedBookingsEditor.putString("u8", "u8, 1900, 1950, 4-6-2022");
        sharedBookingsEditor.putString("l8", "l8, 1900, 1950, 4-6-2022");
        sharedBookingsEditor.putString("v8", "v8, 1900, 1950, 4-6-2022");
        sharedBookingsEditor.putString("h8", "h8, 1900, 1950, 4-6-2022");
        sharedBookingsEditor.putString("r8", "r8, 1900, 1950, 4-6-2022");
        sharedBookingsEditor.putString("c8", "c8, 2000, 2050, 4-6-2022");
        sharedBookingsEditor.putString("u8", "u8, 2000, 2050, 4-6-2022");
        sharedBookingsEditor.putString("l8", "l8, 2000, 2050, 4-6-2022");
        sharedBookingsEditor.putString("v8", "v8, 2000, 2050, 4-6-2022");
        sharedBookingsEditor.putString("h8", "h8, 2000, 2050, 4-6-2022");
        sharedBookingsEditor.putString("r8", "r8, 2000, 2050, 4-6-2022");
        sharedBookingsEditor.putString("c8", "c8, 2100, 2150, 4-6-2022");
        sharedBookingsEditor.putString("u8", "u8, 2100, 2150, 4-6-2022");
        sharedBookingsEditor.putString("l8", "l8, 2100, 2150, 4-6-2022");
        sharedBookingsEditor.putString("v8", "v8, 2100, 2150, 4-6-2022");
        sharedBookingsEditor.putString("h8", "h8, 2100, 2150, 4-6-2022");
        sharedBookingsEditor.putString("r8", "r8, 2100, 2150, 4-6-2022");
        sharedBookingsEditor.putString("c9", "c9, 1900, 1950, 4-7-2022");
        sharedBookingsEditor.putString("u9", "u9, 1900, 1950, 4-7-2022");
        sharedBookingsEditor.putString("l9", "l9, 1900, 1950, 4-7-2022");
        sharedBookingsEditor.putString("v9", "v9, 1900, 1950, 4-7-2022");
        sharedBookingsEditor.putString("h9", "h9, 1900, 1950, 4-7-2022");
        sharedBookingsEditor.putString("r9", "r9, 1900, 1950, 4-7-2022");
        sharedBookingsEditor.putString("c9", "c9, 2000, 2050, 4-7-2022");
        sharedBookingsEditor.putString("u9", "u9, 2000, 2050, 4-7-2022");
        sharedBookingsEditor.putString("l9", "l9, 2000, 2050, 4-7-2022");
        sharedBookingsEditor.putString("v9", "v9, 2000, 2050, 4-7-2022");
        sharedBookingsEditor.putString("h9", "h9, 2000, 2050, 4-7-2022");
        sharedBookingsEditor.putString("r9", "r9, 2000, 2050, 4-7-2022");
        sharedBookingsEditor.putString("c9", "c9, 2100, 2150, 4-7-2022");
        sharedBookingsEditor.putString("u9", "u9, 2100, 2150, 4-7-2022");
        sharedBookingsEditor.putString("l9", "l9, 2100, 2150, 4-7-2022");
        sharedBookingsEditor.putString("v9", "v9, 2100, 2150, 4-7-2022");
        sharedBookingsEditor.putString("h9", "h9, 2100, 2150, 4-7-2022");
        sharedBookingsEditor.putString("r9", "r9, 2100, 2150, 4-7-2022");
        sharedBookingsEditor.putString("c10", "c10, 1900, 1950, 4-8-2022");
        sharedBookingsEditor.putString("u10", "u10, 1900, 1950, 4-8-2022");
        sharedBookingsEditor.putString("l10", "l10, 1900, 1950, 4-8-2022");
        sharedBookingsEditor.putString("v10", "v10, 1900, 1950, 4-8-2022");
        sharedBookingsEditor.putString("h10", "h10, 1900, 1950, 4-8-2022");
        sharedBookingsEditor.putString("r10", "r10, 1900, 1950, 4-8-2022");
        sharedBookingsEditor.putString("c10", "c10, 2000, 2050, 4-8-2022");
        sharedBookingsEditor.putString("u10", "u10, 2000, 2050, 4-8-2022");
        sharedBookingsEditor.putString("l10", "l10, 2000, 2050, 4-8-2022");
        sharedBookingsEditor.putString("v10", "v10, 2000, 2050, 4-8-2022");
        sharedBookingsEditor.putString("h10", "h10, 2000, 2050, 4-8-2022");
        sharedBookingsEditor.putString("r10", "r10, 2000, 2050, 4-8-2022");
        sharedBookingsEditor.putString("c10", "c10, 2100, 2150, 4-8-2022");
        sharedBookingsEditor.putString("u10", "u10, 2100, 2150, 4-8-2022");
        sharedBookingsEditor.putString("l10", "l10, 2100, 2150, 4-8-2022");
        sharedBookingsEditor.putString("v10", "v10, 2100, 2150, 4-8-2022");
        sharedBookingsEditor.putString("h10", "h10, 2100, 2150, 4-8-2022");
        sharedBookingsEditor.putString("r10", "r10, 2100, 2150, 4-8-2022");
        sharedBookingsEditor.putString("c11", "c11, 1900, 1950, 4-9-2022");
        sharedBookingsEditor.putString("u11", "u11, 1900, 1950, 4-9-2022");
        sharedBookingsEditor.putString("l11", "l11, 1900, 1950, 4-9-2022");
        sharedBookingsEditor.putString("v11", "v11, 1900, 1950, 4-9-2022");
        sharedBookingsEditor.putString("h11", "h11, 1900, 1950, 4-9-2022");
        sharedBookingsEditor.putString("r11", "r11, 1900, 1950, 4-9-2022");
        sharedBookingsEditor.putString("c11", "c11, 2000, 2050, 4-9-2022");
        sharedBookingsEditor.putString("u11", "u11, 2000, 2050, 4-9-2022");
        sharedBookingsEditor.putString("l11", "l11, 2000, 2050, 4-9-2022");
        sharedBookingsEditor.putString("v11", "v11, 2000, 2050, 4-9-2022");
        sharedBookingsEditor.putString("h11", "h11, 2000, 2050, 4-9-2022");
        sharedBookingsEditor.putString("r11", "r11, 2000, 2050, 4-9-2022");
        sharedBookingsEditor.putString("c11", "c11, 2100, 2150, 4-9-2022");
        sharedBookingsEditor.putString("u11", "u11, 2100, 2150, 4-9-2022");
        sharedBookingsEditor.putString("l11", "l11, 2100, 2150, 4-9-2022");
        sharedBookingsEditor.putString("v11", "v11, 2100, 2150, 4-9-2022");
        sharedBookingsEditor.putString("h11", "h11, 2100, 2150, 4-9-2022");
        sharedBookingsEditor.putString("r11", "r11, 2100, 2150, 4-9-2022");
        sharedBookingsEditor.putString("c12", "c12, 1900, 1950, 4-10-2022");
        sharedBookingsEditor.putString("u12", "u12, 1900, 1950, 4-10-2022");
        sharedBookingsEditor.putString("l12", "l12, 1900, 1950, 4-10-2022");
        sharedBookingsEditor.putString("v12", "v12, 1900, 1950, 4-10-2022");
        sharedBookingsEditor.putString("h12", "h12, 1900, 1950, 4-10-2022");
        sharedBookingsEditor.putString("r12", "r12, 1900, 1950, 4-10-2022");
        sharedBookingsEditor.putString("c12", "c12, 2000, 2050, 4-10-2022");
        sharedBookingsEditor.putString("u12", "u12, 2000, 2050, 4-10-2022");
        sharedBookingsEditor.putString("l12", "l12, 2000, 2050, 4-10-2022");
        sharedBookingsEditor.putString("v12", "v12, 2000, 2050, 4-10-2022");
        sharedBookingsEditor.putString("h12", "h12, 2000, 2050, 4-10-2022");
        sharedBookingsEditor.putString("r12", "r12, 2000, 2050, 4-10-2022");
        sharedBookingsEditor.putString("c12", "c12, 2100, 2150, 4-10-2022");
        sharedBookingsEditor.putString("u12", "u12, 2100, 2150, 4-10-2022");
        sharedBookingsEditor.putString("l12", "l12, 2100, 2150, 4-10-2022");
        sharedBookingsEditor.putString("v12", "v12, 2100, 2150, 4-10-2022");
        sharedBookingsEditor.putString("h12", "h12, 2100, 2150, 4-10-2022");
        sharedBookingsEditor.putString("r12", "r12, 2100, 2150, 4-10-2022");
        sharedBookingsEditor.putString("c13", "c13, 1900, 1950, 4-11-2022");
        sharedBookingsEditor.putString("u13", "u13, 1900, 1950, 4-11-2022");
        sharedBookingsEditor.putString("l13", "l13, 1900, 1950, 4-11-2022");
        sharedBookingsEditor.putString("v13", "v13, 1900, 1950, 4-11-2022");
        sharedBookingsEditor.putString("h13", "h13, 1900, 1950, 4-11-2022");
        sharedBookingsEditor.putString("r13", "r13, 1900, 1950, 4-11-2022");
        sharedBookingsEditor.putString("c13", "c13, 2000, 2050, 4-11-2022");
        sharedBookingsEditor.putString("u13", "u13, 2000, 2050, 4-11-2022");
        sharedBookingsEditor.putString("l13", "l13, 2000, 2050, 4-11-2022");
        sharedBookingsEditor.putString("v13", "v13, 2000, 2050, 4-11-2022");
        sharedBookingsEditor.putString("h13", "h13, 2000, 2050, 4-11-2022");
        sharedBookingsEditor.putString("r13", "r13, 2000, 2050, 4-11-2022");
        sharedBookingsEditor.putString("c13", "c13, 2100, 2150, 4-11-2022");
        sharedBookingsEditor.putString("u13", "u13, 2100, 2150, 4-11-2022");
        sharedBookingsEditor.putString("l13", "l13, 2100, 2150, 4-11-2022");
        sharedBookingsEditor.putString("v13", "v13, 2100, 2150, 4-11-2022");
        sharedBookingsEditor.putString("h13", "h13, 2100, 2150, 4-11-2022");
        sharedBookingsEditor.putString("r13", "r13, 2100, 2150, 4-11-2022");
        sharedBookingsEditor.putString("c14", "c14, 1900, 1950, 4-12-2022");
        sharedBookingsEditor.putString("u14", "u14, 1900, 1950, 4-12-2022");
        sharedBookingsEditor.putString("l14", "l14, 1900, 1950, 4-12-2022");
        sharedBookingsEditor.putString("v14", "v14, 1900, 1950, 4-12-2022");
        sharedBookingsEditor.putString("h14", "h14, 1900, 1950, 4-12-2022");
        sharedBookingsEditor.putString("r14", "r14, 1900, 1950, 4-12-2022");
        sharedBookingsEditor.putString("c14", "c14, 2000, 2050, 4-12-2022");
        sharedBookingsEditor.putString("u14", "u14, 2000, 2050, 4-12-2022");
        sharedBookingsEditor.putString("l14", "l14, 2000, 2050, 4-12-2022");
        sharedBookingsEditor.putString("v14", "v14, 2000, 2050, 4-12-2022");
        sharedBookingsEditor.putString("h14", "h14, 2000, 2050, 4-12-2022");
        sharedBookingsEditor.putString("r14", "r14, 2000, 2050, 4-12-2022");
        sharedBookingsEditor.putString("c14", "c14, 2100, 2150, 4-12-2022");
        sharedBookingsEditor.putString("u14", "u14, 2100, 2150, 4-12-2022");
        sharedBookingsEditor.putString("l14", "l14, 2100, 2150, 4-12-2022");
        sharedBookingsEditor.putString("v14", "v14, 2100, 2150, 4-12-2022");
        sharedBookingsEditor.putString("h14", "h14, 2100, 2150, 4-12-2022");
        sharedBookingsEditor.putString("r14", "r14, 2100, 2150, 4-12-2022");
        sharedBookingsEditor.putString("c15", "c15, 1900, 1950, 4-13-2022");
        sharedBookingsEditor.putString("u15", "u15, 1900, 1950, 4-13-2022");
        sharedBookingsEditor.putString("l15", "l15, 1900, 1950, 4-13-2022");
        sharedBookingsEditor.putString("v15", "v15, 1900, 1950, 4-13-2022");
        sharedBookingsEditor.putString("h15", "h15, 1900, 1950, 4-13-2022");
        sharedBookingsEditor.putString("r15", "r15, 1900, 1950, 4-13-2022");
        sharedBookingsEditor.putString("c15", "c15, 2000, 2050, 4-13-2022");
        sharedBookingsEditor.putString("u15", "u15, 2000, 2050, 4-13-2022");
        sharedBookingsEditor.putString("l15", "l15, 2000, 2050, 4-13-2022");
        sharedBookingsEditor.putString("v15", "v15, 2000, 2050, 4-13-2022");
        sharedBookingsEditor.putString("h15", "h15, 2000, 2050, 4-13-2022");
        sharedBookingsEditor.putString("r15", "r15, 2000, 2050, 4-13-2022");
        sharedBookingsEditor.putString("c15", "c15, 2100, 2150, 4-13-2022");
        sharedBookingsEditor.putString("u15", "u15, 2100, 2150, 4-13-2022");
        sharedBookingsEditor.putString("l15", "l15, 2100, 2150, 4-13-2022");
        sharedBookingsEditor.putString("v15", "v15, 2100, 2150, 4-13-2022");
        sharedBookingsEditor.putString("h15", "h15, 2100, 2150, 4-13-2022");
        sharedBookingsEditor.putString("r15", "r15, 2100, 2150, 4-13-2022");
        sharedBookingsEditor.putString("c16", "c16, 1900, 1950, 4-14-2022");
        sharedBookingsEditor.putString("u16", "u16, 1900, 1950, 4-14-2022");
        sharedBookingsEditor.putString("l16", "l16, 1900, 1950, 4-14-2022");
        sharedBookingsEditor.putString("v16", "v16, 1900, 1950, 4-14-2022");
        sharedBookingsEditor.putString("h16", "h16, 1900, 1950, 4-14-2022");
        sharedBookingsEditor.putString("r16", "r16, 1900, 1950, 4-14-2022");
        sharedBookingsEditor.putString("c16", "c16, 2000, 2050, 4-14-2022");
        sharedBookingsEditor.putString("u16", "u16, 2000, 2050, 4-14-2022");
        sharedBookingsEditor.putString("l16", "l16, 2000, 2050, 4-14-2022");
        sharedBookingsEditor.putString("v16", "v16, 2000, 2050, 4-14-2022");
        sharedBookingsEditor.putString("h16", "h16, 2000, 2050, 4-14-2022");
        sharedBookingsEditor.putString("r16", "r16, 2000, 2050, 4-14-2022");
        sharedBookingsEditor.putString("c16", "c16, 2100, 2150, 4-14-2022");
        sharedBookingsEditor.putString("u16", "u16, 2100, 2150, 4-14-2022");
        sharedBookingsEditor.putString("l16", "l16, 2100, 2150, 4-14-2022");
        sharedBookingsEditor.putString("v16", "v16, 2100, 2150, 4-14-2022");
        sharedBookingsEditor.putString("h16", "h16, 2100, 2150, 4-14-2022");
        sharedBookingsEditor.putString("r16", "r16, 2100, 2150, 4-14-2022");
        sharedBookingsEditor.putString("c17", "c17, 1900, 1950, 4-15-2022");
        sharedBookingsEditor.putString("u17", "u17, 1900, 1950, 4-15-2022");
        sharedBookingsEditor.putString("l17", "l17, 1900, 1950, 4-15-2022");
        sharedBookingsEditor.putString("v17", "v17, 1900, 1950, 4-15-2022");
        sharedBookingsEditor.putString("h17", "h17, 1900, 1950, 4-15-2022");
        sharedBookingsEditor.putString("r17", "r17, 1900, 1950, 4-15-2022");
        sharedBookingsEditor.putString("c17", "c17, 2000, 2050, 4-15-2022");
        sharedBookingsEditor.putString("u17", "u17, 2000, 2050, 4-15-2022");
        sharedBookingsEditor.putString("l17", "l17, 2000, 2050, 4-15-2022");
        sharedBookingsEditor.putString("v17", "v17, 2000, 2050, 4-15-2022");
        sharedBookingsEditor.putString("h17", "h17, 2000, 2050, 4-15-2022");
        sharedBookingsEditor.putString("r17", "r17, 2000, 2050, 4-15-2022");
        sharedBookingsEditor.putString("c17", "c17, 2100, 2150, 4-15-2022");
        sharedBookingsEditor.putString("u17", "u17, 2100, 2150, 4-15-2022");
        sharedBookingsEditor.putString("l17", "l17, 2100, 2150, 4-15-2022");
        sharedBookingsEditor.putString("v17", "v17, 2100, 2150, 4-15-2022");
        sharedBookingsEditor.putString("h17", "h17, 2100, 2150, 4-15-2022");
        sharedBookingsEditor.putString("r17", "r17, 2100, 2150, 4-15-2022");
        sharedBookingsEditor.putString("c18", "c18, 1900, 1950, 4-16-2022");
        sharedBookingsEditor.putString("u18", "u18, 1900, 1950, 4-16-2022");
        sharedBookingsEditor.putString("l18", "l18, 1900, 1950, 4-16-2022");
        sharedBookingsEditor.putString("v18", "v18, 1900, 1950, 4-16-2022");
        sharedBookingsEditor.putString("h18", "h18, 1900, 1950, 4-16-2022");
        sharedBookingsEditor.putString("r18", "r18, 1900, 1950, 4-16-2022");
        sharedBookingsEditor.putString("c18", "c18, 2000, 2050, 4-16-2022");
        sharedBookingsEditor.putString("u18", "u18, 2000, 2050, 4-16-2022");
        sharedBookingsEditor.putString("l18", "l18, 2000, 2050, 4-16-2022");
        sharedBookingsEditor.putString("v18", "v18, 2000, 2050, 4-16-2022");
        sharedBookingsEditor.putString("h18", "h18, 2000, 2050, 4-16-2022");
        sharedBookingsEditor.putString("r18", "r18, 2000, 2050, 4-16-2022");
        sharedBookingsEditor.putString("c18", "c18, 2100, 2150, 4-16-2022");
        sharedBookingsEditor.putString("u18", "u18, 2100, 2150, 4-16-2022");
        sharedBookingsEditor.putString("l18", "l18, 2100, 2150, 4-16-2022");
        sharedBookingsEditor.putString("v18", "v18, 2100, 2150, 4-16-2022");
        sharedBookingsEditor.putString("h18", "h18, 2100, 2150, 4-16-2022");
        sharedBookingsEditor.putString("r18", "r18, 2100, 2150, 4-16-2022");
        sharedBookingsEditor.putString("c19", "c19, 1900, 1950, 4-17-2022");
        sharedBookingsEditor.putString("u19", "u19, 1900, 1950, 4-17-2022");
        sharedBookingsEditor.putString("l19", "l19, 1900, 1950, 4-17-2022");
        sharedBookingsEditor.putString("v19", "v19, 1900, 1950, 4-17-2022");
        sharedBookingsEditor.putString("h19", "h19, 1900, 1950, 4-17-2022");
        sharedBookingsEditor.putString("r19", "r19, 1900, 1950, 4-17-2022");
        sharedBookingsEditor.putString("c19", "c19, 2000, 2050, 4-17-2022");
        sharedBookingsEditor.putString("u19", "u19, 2000, 2050, 4-17-2022");
        sharedBookingsEditor.putString("l19", "l19, 2000, 2050, 4-17-2022");
        sharedBookingsEditor.putString("v19", "v19, 2000, 2050, 4-17-2022");
        sharedBookingsEditor.putString("h19", "h19, 2000, 2050, 4-17-2022");
        sharedBookingsEditor.putString("r19", "r19, 2000, 2050, 4-17-2022");
        sharedBookingsEditor.putString("c19", "c19, 2100, 2150, 4-17-2022");
        sharedBookingsEditor.putString("u19", "u19, 2100, 2150, 4-17-2022");
        sharedBookingsEditor.putString("l19", "l19, 2100, 2150, 4-17-2022");
        sharedBookingsEditor.putString("v19", "v19, 2100, 2150, 4-17-2022");
        sharedBookingsEditor.putString("h19", "h19, 2100, 2150, 4-17-2022");
        sharedBookingsEditor.putString("r19", "r19, 2100, 2150, 4-17-2022");
        sharedBookingsEditor.putString("c20", "c20, 1900, 1950, 4-18-2022");
        sharedBookingsEditor.putString("u20", "u20, 1900, 1950, 4-18-2022");
        sharedBookingsEditor.putString("l20", "l20, 1900, 1950, 4-18-2022");
        sharedBookingsEditor.putString("v20", "v20, 1900, 1950, 4-18-2022");
        sharedBookingsEditor.putString("h20", "h20, 1900, 1950, 4-18-2022");
        sharedBookingsEditor.putString("r20", "r20, 1900, 1950, 4-18-2022");
        sharedBookingsEditor.putString("c20", "c20, 2000, 2050, 4-18-2022");
        sharedBookingsEditor.putString("u20", "u20, 2000, 2050, 4-18-2022");
        sharedBookingsEditor.putString("l20", "l20, 2000, 2050, 4-18-2022");
        sharedBookingsEditor.putString("v20", "v20, 2000, 2050, 4-18-2022");
        sharedBookingsEditor.putString("h20", "h20, 2000, 2050, 4-18-2022");
        sharedBookingsEditor.putString("r20", "r20, 2000, 2050, 4-18-2022");
        sharedBookingsEditor.putString("c20", "c20, 2100, 2150, 4-18-2022");
        sharedBookingsEditor.putString("u20", "u20, 2100, 2150, 4-18-2022");
        sharedBookingsEditor.putString("l20", "l20, 2100, 2150, 4-18-2022");
        sharedBookingsEditor.putString("v20", "v20, 2100, 2150, 4-18-2022");
        sharedBookingsEditor.putString("h20", "h20, 2100, 2150, 4-18-2022");
        sharedBookingsEditor.putString("r20", "r20, 2100, 2150, 4-18-2022");
        sharedBookingsEditor.putString("c21", "c21, 1900, 1950, 4-19-2022");
        sharedBookingsEditor.putString("u21", "u21, 1900, 1950, 4-19-2022");
        sharedBookingsEditor.putString("l21", "l21, 1900, 1950, 4-19-2022");
        sharedBookingsEditor.putString("v21", "v21, 1900, 1950, 4-19-2022");
        sharedBookingsEditor.putString("h21", "h21, 1900, 1950, 4-19-2022");
        sharedBookingsEditor.putString("r21", "r21, 1900, 1950, 4-19-2022");
        sharedBookingsEditor.putString("c21", "c21, 2000, 2050, 4-19-2022");
        sharedBookingsEditor.putString("u21", "u21, 2000, 2050, 4-19-2022");
        sharedBookingsEditor.putString("l21", "l21, 2000, 2050, 4-19-2022");
        sharedBookingsEditor.putString("v21", "v21, 2000, 2050, 4-19-2022");
        sharedBookingsEditor.putString("h21", "h21, 2000, 2050, 4-19-2022");
        sharedBookingsEditor.putString("r21", "r21, 2000, 2050, 4-19-2022");
        sharedBookingsEditor.putString("c21", "c21, 2100, 2150, 4-19-2022");
        sharedBookingsEditor.putString("u21", "u21, 2100, 2150, 4-19-2022");
        sharedBookingsEditor.putString("l21", "l21, 2100, 2150, 4-19-2022");
        sharedBookingsEditor.putString("v21", "v21, 2100, 2150, 4-19-2022");
        sharedBookingsEditor.putString("h21", "h21, 2100, 2150, 4-19-2022");
        sharedBookingsEditor.putString("r21", "r21, 2100, 2150, 4-19-2022");
        sharedBookingsEditor.putString("c22", "c22, 1900, 1950, 4-20-2022");
        sharedBookingsEditor.putString("u22", "u22, 1900, 1950, 4-20-2022");
        sharedBookingsEditor.putString("l22", "l22, 1900, 1950, 4-20-2022");
        sharedBookingsEditor.putString("v22", "v22, 1900, 1950, 4-20-2022");
        sharedBookingsEditor.putString("h22", "h22, 1900, 1950, 4-20-2022");
        sharedBookingsEditor.putString("r22", "r22, 1900, 1950, 4-20-2022");
        sharedBookingsEditor.putString("c22", "c22, 2000, 2050, 4-20-2022");
        sharedBookingsEditor.putString("u22", "u22, 2000, 2050, 4-20-2022");
        sharedBookingsEditor.putString("l22", "l22, 2000, 2050, 4-20-2022");
        sharedBookingsEditor.putString("v22", "v22, 2000, 2050, 4-20-2022");
        sharedBookingsEditor.putString("h22", "h22, 2000, 2050, 4-20-2022");
        sharedBookingsEditor.putString("r22", "r22, 2000, 2050, 4-20-2022");
        sharedBookingsEditor.putString("c22", "c22, 2100, 2150, 4-20-2022");
        sharedBookingsEditor.putString("u22", "u22, 2100, 2150, 4-20-2022");
        sharedBookingsEditor.putString("l22", "l22, 2100, 2150, 4-20-2022");
        sharedBookingsEditor.putString("v22", "v22, 2100, 2150, 4-20-2022");
        sharedBookingsEditor.putString("h22", "h22, 2100, 2150, 4-20-2022");
        sharedBookingsEditor.putString("r22", "r22, 2100, 2150, 4-20-2022");
        sharedBookingsEditor.putString("c23", "c23, 1900, 1950, 4-21-2022");
        sharedBookingsEditor.putString("u23", "u23, 1900, 1950, 4-21-2022");
        sharedBookingsEditor.putString("l23", "l23, 1900, 1950, 4-21-2022");
        sharedBookingsEditor.putString("v23", "v23, 1900, 1950, 4-21-2022");
        sharedBookingsEditor.putString("h23", "h23, 1900, 1950, 4-21-2022");
        sharedBookingsEditor.putString("r23", "r23, 1900, 1950, 4-21-2022");
        sharedBookingsEditor.putString("c23", "c23, 2000, 2050, 4-21-2022");
        sharedBookingsEditor.putString("u23", "u23, 2000, 2050, 4-21-2022");
        sharedBookingsEditor.putString("l23", "l23, 2000, 2050, 4-21-2022");
        sharedBookingsEditor.putString("v23", "v23, 2000, 2050, 4-21-2022");
        sharedBookingsEditor.putString("h23", "h23, 2000, 2050, 4-21-2022");
        sharedBookingsEditor.putString("r23", "r23, 2000, 2050, 4-21-2022");
        sharedBookingsEditor.putString("c23", "c23, 2100, 2150, 4-21-2022");
        sharedBookingsEditor.putString("u23", "u23, 2100, 2150, 4-21-2022");
        sharedBookingsEditor.putString("l23", "l23, 2100, 2150, 4-21-2022");
        sharedBookingsEditor.putString("v23", "v23, 2100, 2150, 4-21-2022");
        sharedBookingsEditor.putString("h23", "h23, 2100, 2150, 4-21-2022");
        sharedBookingsEditor.putString("r23", "r23, 2100, 2150, 4-21-2022");
        sharedBookingsEditor.putString("c24", "c24, 1900, 1950, 4-22-2022");
        sharedBookingsEditor.putString("u24", "u24, 1900, 1950, 4-22-2022");
        sharedBookingsEditor.putString("l24", "l24, 1900, 1950, 4-22-2022");
        sharedBookingsEditor.putString("v24", "v24, 1900, 1950, 4-22-2022");
        sharedBookingsEditor.putString("h24", "h24, 1900, 1950, 4-22-2022");
        sharedBookingsEditor.putString("r24", "r24, 1900, 1950, 4-22-2022");
        sharedBookingsEditor.putString("c24", "c24, 2000, 2050, 4-22-2022");
        sharedBookingsEditor.putString("u24", "u24, 2000, 2050, 4-22-2022");
        sharedBookingsEditor.putString("l24", "l24, 2000, 2050, 4-22-2022");
        sharedBookingsEditor.putString("v24", "v24, 2000, 2050, 4-22-2022");
        sharedBookingsEditor.putString("h24", "h24, 2000, 2050, 4-22-2022");
        sharedBookingsEditor.putString("r24", "r24, 2000, 2050, 4-22-2022");
        sharedBookingsEditor.putString("c24", "c24, 2100, 2150, 4-22-2022");
        sharedBookingsEditor.putString("u24", "u24, 2100, 2150, 4-22-2022");
        sharedBookingsEditor.putString("l24", "l24, 2100, 2150, 4-22-2022");
        sharedBookingsEditor.putString("v24", "v24, 2100, 2150, 4-22-2022");
        sharedBookingsEditor.putString("h24", "h24, 2100, 2150, 4-22-2022");
        sharedBookingsEditor.putString("r24", "r24, 2100, 2150, 4-22-2022");
        sharedBookingsEditor.putString("c25", "c25, 1900, 1950, 4-23-2022");
        sharedBookingsEditor.putString("u25", "u25, 1900, 1950, 4-23-2022");
        sharedBookingsEditor.putString("l25", "l25, 1900, 1950, 4-23-2022");
        sharedBookingsEditor.putString("v25", "v25, 1900, 1950, 4-23-2022");
        sharedBookingsEditor.putString("h25", "h25, 1900, 1950, 4-23-2022");
        sharedBookingsEditor.putString("r25", "r25, 1900, 1950, 4-23-2022");
        sharedBookingsEditor.putString("c25", "c25, 2000, 2050, 4-23-2022");
        sharedBookingsEditor.putString("u25", "u25, 2000, 2050, 4-23-2022");
        sharedBookingsEditor.putString("l25", "l25, 2000, 2050, 4-23-2022");
        sharedBookingsEditor.putString("v25", "v25, 2000, 2050, 4-23-2022");
        sharedBookingsEditor.putString("h25", "h25, 2000, 2050, 4-23-2022");
        sharedBookingsEditor.putString("r25", "r25, 2000, 2050, 4-23-2022");
        sharedBookingsEditor.putString("c25", "c25, 2100, 2150, 4-23-2022");
        sharedBookingsEditor.putString("u25", "u25, 2100, 2150, 4-23-2022");
        sharedBookingsEditor.putString("l25", "l25, 2100, 2150, 4-23-2022");
        sharedBookingsEditor.putString("v25", "v25, 2100, 2150, 4-23-2022");
        sharedBookingsEditor.putString("h25", "h25, 2100, 2150, 4-23-2022");
        sharedBookingsEditor.putString("r25", "r25, 2100, 2150, 4-23-2022");
        sharedBookingsEditor.putString("c26", "c26, 1900, 1950, 4-24-2022");
        sharedBookingsEditor.putString("u26", "u26, 1900, 1950, 4-24-2022");
        sharedBookingsEditor.putString("l26", "l26, 1900, 1950, 4-24-2022");
        sharedBookingsEditor.putString("v26", "v26, 1900, 1950, 4-24-2022");
        sharedBookingsEditor.putString("h26", "h26, 1900, 1950, 4-24-2022");
        sharedBookingsEditor.putString("r26", "r26, 1900, 1950, 4-24-2022");
        sharedBookingsEditor.putString("c26", "c26, 2000, 2050, 4-24-2022");
        sharedBookingsEditor.putString("u26", "u26, 2000, 2050, 4-24-2022");
        sharedBookingsEditor.putString("l26", "l26, 2000, 2050, 4-24-2022");
        sharedBookingsEditor.putString("v26", "v26, 2000, 2050, 4-24-2022");
        sharedBookingsEditor.putString("h26", "h26, 2000, 2050, 4-24-2022");
        sharedBookingsEditor.putString("r26", "r26, 2000, 2050, 4-24-2022");
        sharedBookingsEditor.putString("c26", "c26, 2100, 2150, 4-24-2022");
        sharedBookingsEditor.putString("u26", "u26, 2100, 2150, 4-24-2022");
        sharedBookingsEditor.putString("l26", "l26, 2100, 2150, 4-24-2022");
        sharedBookingsEditor.putString("v26", "v26, 2100, 2150, 4-24-2022");
        sharedBookingsEditor.putString("h26", "h26, 2100, 2150, 4-24-2022");
        sharedBookingsEditor.putString("r26", "r26, 2100, 2150, 4-24-2022");
        sharedBookingsEditor.putString("c27", "c27, 1900, 1950, 4-25-2022");
        sharedBookingsEditor.putString("u27", "u27, 1900, 1950, 4-25-2022");
        sharedBookingsEditor.putString("l27", "l27, 1900, 1950, 4-25-2022");
        sharedBookingsEditor.putString("v27", "v27, 1900, 1950, 4-25-2022");
        sharedBookingsEditor.putString("h27", "h27, 1900, 1950, 4-25-2022");
        sharedBookingsEditor.putString("r27", "r27, 1900, 1950, 4-25-2022");
        sharedBookingsEditor.putString("c27", "c27, 2000, 2050, 4-25-2022");
        sharedBookingsEditor.putString("u27", "u27, 2000, 2050, 4-25-2022");
        sharedBookingsEditor.putString("l27", "l27, 2000, 2050, 4-25-2022");
        sharedBookingsEditor.putString("v27", "v27, 2000, 2050, 4-25-2022");
        sharedBookingsEditor.putString("h27", "h27, 2000, 2050, 4-25-2022");
        sharedBookingsEditor.putString("r27", "r27, 2000, 2050, 4-25-2022");
        sharedBookingsEditor.putString("c27", "c27, 2100, 2150, 4-25-2022");
        sharedBookingsEditor.putString("u27", "u27, 2100, 2150, 4-25-2022");
        sharedBookingsEditor.putString("l27", "l27, 2100, 2150, 4-25-2022");
        sharedBookingsEditor.putString("v27", "v27, 2100, 2150, 4-25-2022");
        sharedBookingsEditor.putString("h27", "h27, 2100, 2150, 4-25-2022");
        sharedBookingsEditor.putString("r27", "r27, 2100, 2150, 4-25-2022");
        sharedBookingsEditor.putString("c28", "c28, 1900, 1950, 4-26-2022");
        sharedBookingsEditor.putString("u28", "u28, 1900, 1950, 4-26-2022");
        sharedBookingsEditor.putString("l28", "l28, 1900, 1950, 4-26-2022");
        sharedBookingsEditor.putString("v28", "v28, 1900, 1950, 4-26-2022");
        sharedBookingsEditor.putString("h28", "h28, 1900, 1950, 4-26-2022");
        sharedBookingsEditor.putString("r28", "r28, 1900, 1950, 4-26-2022");
        sharedBookingsEditor.putString("c28", "c28, 2000, 2050, 4-26-2022");
        sharedBookingsEditor.putString("u28", "u28, 2000, 2050, 4-26-2022");
        sharedBookingsEditor.putString("l28", "l28, 2000, 2050, 4-26-2022");
        sharedBookingsEditor.putString("v28", "v28, 2000, 2050, 4-26-2022");
        sharedBookingsEditor.putString("h28", "h28, 2000, 2050, 4-26-2022");
        sharedBookingsEditor.putString("r28", "r28, 2000, 2050, 4-26-2022");
        sharedBookingsEditor.putString("c28", "c28, 2100, 2150, 4-26-2022");
        sharedBookingsEditor.putString("u28", "u28, 2100, 2150, 4-26-2022");
        sharedBookingsEditor.putString("l28", "l28, 2100, 2150, 4-26-2022");
        sharedBookingsEditor.putString("v28", "v28, 2100, 2150, 4-26-2022");
        sharedBookingsEditor.putString("h28", "h28, 2100, 2150, 4-26-2022");
        sharedBookingsEditor.putString("r28", "r28, 2100, 2150, 4-26-2022");
        sharedBookingsEditor.putString("c29", "c29, 1900, 1950, 4-27-2022");
        sharedBookingsEditor.putString("u29", "u29, 1900, 1950, 4-27-2022");
        sharedBookingsEditor.putString("l29", "l29, 1900, 1950, 4-27-2022");
        sharedBookingsEditor.putString("v29", "v29, 1900, 1950, 4-27-2022");
        sharedBookingsEditor.putString("h29", "h29, 1900, 1950, 4-27-2022");
        sharedBookingsEditor.putString("r29", "r29, 1900, 1950, 4-27-2022");
        sharedBookingsEditor.putString("c29", "c29, 2000, 2050, 4-27-2022");
        sharedBookingsEditor.putString("u29", "u29, 2000, 2050, 4-27-2022");
        sharedBookingsEditor.putString("l29", "l29, 2000, 2050, 4-27-2022");
        sharedBookingsEditor.putString("v29", "v29, 2000, 2050, 4-27-2022");
        sharedBookingsEditor.putString("h29", "h29, 2000, 2050, 4-27-2022");
        sharedBookingsEditor.putString("r29", "r29, 2000, 2050, 4-27-2022");
        sharedBookingsEditor.putString("c29", "c29, 2100, 2150, 4-27-2022");
        sharedBookingsEditor.putString("u29", "u29, 2100, 2150, 4-27-2022");
        sharedBookingsEditor.putString("l29", "l29, 2100, 2150, 4-27-2022");
        sharedBookingsEditor.putString("v29", "v29, 2100, 2150, 4-27-2022");
        sharedBookingsEditor.putString("h29", "h29, 2100, 2150, 4-27-2022");
        sharedBookingsEditor.putString("r29", "r29, 2100, 2150, 4-27-2022");
        sharedBookingsEditor.putString("c30", "c30, 1900, 1950, 4-28-2022");
        sharedBookingsEditor.putString("u30", "u30, 1900, 1950, 4-28-2022");
        sharedBookingsEditor.putString("l30", "l30, 1900, 1950, 4-28-2022");
        sharedBookingsEditor.putString("v30", "v30, 1900, 1950, 4-28-2022");
        sharedBookingsEditor.putString("h30", "h30, 1900, 1950, 4-28-2022");
        sharedBookingsEditor.putString("r30", "r30, 1900, 1950, 4-28-2022");
        sharedBookingsEditor.putString("c30", "c30, 2000, 2050, 4-28-2022");
        sharedBookingsEditor.putString("u30", "u30, 2000, 2050, 4-28-2022");
        sharedBookingsEditor.putString("l30", "l30, 2000, 2050, 4-28-2022");
        sharedBookingsEditor.putString("v30", "v30, 2000, 2050, 4-28-2022");
        sharedBookingsEditor.putString("h30", "h30, 2000, 2050, 4-28-2022");
        sharedBookingsEditor.putString("r30", "r30, 2000, 2050, 4-28-2022");
        sharedBookingsEditor.putString("c30", "c30, 2100, 2150, 4-28-2022");
        sharedBookingsEditor.putString("u30", "u30, 2100, 2150, 4-28-2022");
        sharedBookingsEditor.putString("l30", "l30, 2100, 2150, 4-28-2022");
        sharedBookingsEditor.putString("v30", "v30, 2100, 2150, 4-28-2022");
        sharedBookingsEditor.putString("h30", "h30, 2100, 2150, 4-28-2022");
        sharedBookingsEditor.putString("r30", "r30, 2100, 2150, 4-28-2022");
        sharedBookingsEditor.putString("c31", "c31, 1900, 1950, 4-29-2022");
        sharedBookingsEditor.putString("u31", "u31, 1900, 1950, 4-29-2022");
        sharedBookingsEditor.putString("l31", "l31, 1900, 1950, 4-29-2022");
        sharedBookingsEditor.putString("v31", "v31, 1900, 1950, 4-29-2022");
        sharedBookingsEditor.putString("h31", "h31, 1900, 1950, 4-29-2022");
        sharedBookingsEditor.putString("r31", "r31, 1900, 1950, 4-29-2022");
        sharedBookingsEditor.putString("c31", "c31, 2000, 2050, 4-29-2022");
        sharedBookingsEditor.putString("u31", "u31, 2000, 2050, 4-29-2022");
        sharedBookingsEditor.putString("l31", "l31, 2000, 2050, 4-29-2022");
        sharedBookingsEditor.putString("v31", "v31, 2000, 2050, 4-29-2022");
        sharedBookingsEditor.putString("h31", "h31, 2000, 2050, 4-29-2022");
        sharedBookingsEditor.putString("r31", "r31, 2000, 2050, 4-29-2022");
        sharedBookingsEditor.putString("c31", "c31, 2100, 2150, 4-29-2022");
        sharedBookingsEditor.putString("u31", "u31, 2100, 2150, 4-29-2022");
        sharedBookingsEditor.putString("l31", "l31, 2100, 2150, 4-29-2022");
        sharedBookingsEditor.putString("v31", "v31, 2100, 2150, 4-29-2022");
        sharedBookingsEditor.putString("h31", "h31, 2100, 2150, 4-29-2022");
        sharedBookingsEditor.putString("r31", "r31, 2100, 2150, 4-29-2022");
        sharedBookingsEditor.apply();
        System.out.println("Complete...");

    }

    private boolean isPopulated() {

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