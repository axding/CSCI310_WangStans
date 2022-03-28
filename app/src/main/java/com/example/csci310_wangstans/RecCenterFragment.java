package com.example.csci310_wangstans;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.csci310_wangstans.databinding.FragmentReccenterBinding;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Vector;

public class RecCenterFragment extends Fragment {

    private FragmentReccenterBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentReccenterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DatePicker datePicker = binding.datePicker;

        Calendar today = Calendar.getInstance();
        datePicker.setMinDate(today.getTimeInMillis());

        Calendar threeDaysLater = (Calendar) today.clone();
        threeDaysLater.add(Calendar.DATE, 2);
        datePicker.setMaxDate(threeDaysLater.getTimeInMillis());

        binding.updateDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout ll = binding.bookingDisplay;
                ll.removeAllViews();
                String date = (datePicker.getMonth()+1) + "" + datePicker.getDayOfMonth() + "" + datePicker.getYear();
                readReservationFile(date);
            }
        });
    }

    private void readReservationFile(String date) {
        try {
            InputStream is = getContext().getAssets().open("db/cResDB.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line = reader.readLine();
            Vector<Booking> bookings = new Vector<>();
            while(line != null){
                String[] resInfo = line.split(",");
                if (resInfo[3].equals(date)) {
                    bookings.add(new Booking(resInfo));
                }
                line = reader.readLine();
            }

            System.out.println(bookings.size());
            updateBookings(bookings);

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

    private void updateBookings(Vector<Booking> bookings) {
        LinearLayout layout = binding.bookingDisplay;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        if (bookings.size()==0) {
            TextView noResText = new TextView(getContext());
            noResText.setLayoutParams((layoutParams));
            noResText.setGravity(Gravity.CENTER);
            noResText.setText("No times are available for this day. Please try another day.");
            layout.addView(noResText);
            return;
        }

        for(int i = 0; i< bookings.size(); i++) {
            Booking booking = bookings.get(i);

            String time = booking.getStartTime() + " - " + booking.getEndTime();
            TextView timeText = new TextView(getContext());
            timeText.setLayoutParams(layoutParams);
            timeText.setGravity(Gravity.CENTER);
            timeText.setText(time);

            int availSpots = 1 - booking.getNumUsers();
            TextView availText = new TextView(getContext());
            availText.setLayoutParams((layoutParams));
            availText.setGravity(Gravity.CENTER);
            availText.setText(availSpots + " spots open");

            Button actionButton = new Button(getContext());
            actionButton.setLayoutParams(layoutParams);

            if (availSpots == 0) {
                actionButton.setText("Notify Me");
                actionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        actionButton.setText("Added to the waitlist");
                    }
                });
            }
            else {
                actionButton.setText("Book Now");
                actionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        actionButton.setText("Booked!");
                        availText.setText((availSpots-1) + " spots open");
                    }
                });
            }

            if (layout != null) {
                layout.addView(timeText);
                layout.addView(availText);
                layout.addView(actionButton);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}