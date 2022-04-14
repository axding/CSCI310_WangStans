package com.example.csci310_wangstans;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.csci310_wangstans.databinding.FragmentReccenterBinding;

import java.util.Calendar;
import java.util.Map;
import java.util.Vector;

public class HRecCenterFragment extends Fragment {

    private FragmentReccenterBinding binding;
    private Vector<Booking> bookings;

    private SharedPreferences sharedBookings;
    private SharedPreferences.Editor sharedBookingsEditor;

    private SharedPreferences sharedWaitlist;
    private SharedPreferences.Editor sharedWaitlistEditor;

    private SharedPreferences usersFile;
    private SharedPreferences.Editor usersFileEditor;

    private RecCenterUtil util;

    @Override
    public void onAttach(Context context) {
        sharedBookings = context.getSharedPreferences("sharedBooking", Context.MODE_PRIVATE);
        sharedBookingsEditor = sharedBookings.edit();

        sharedWaitlist = context.getSharedPreferences("sharedWaitlist", Context.MODE_PRIVATE);
        sharedWaitlistEditor = sharedWaitlist.edit();

        usersFile = context.getSharedPreferences("usersFile", Context.MODE_PRIVATE);
        usersFileEditor = usersFile.edit();

        util = new RecCenterUtil();

        super.onAttach(context);
    }

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

        Calendar twoDaysLater = (Calendar) today.clone();
        twoDaysLater.add(Calendar.DATE, 2);
        datePicker.setMaxDate(twoDaysLater.getTimeInMillis());

        binding.updateDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout ll = binding.bookingDisplay;
                ll.removeAllViews();
                String date = (datePicker.getMonth()+1) + "-" + datePicker.getDayOfMonth() + "-" + datePicker.getYear();
                readReservationFile(date);
            }
        });
    }

    private void readReservationFile(String date) {
        bookings = new Vector<>();
        Map<String,?> keys = sharedBookings.getAll();

        for(Map.Entry<String,?> entry : keys.entrySet()){
            String data = entry.getValue().toString();
            String[] dataVals = data.split(",");
            if (dataVals[3].equals(date) && dataVals[0].charAt(0) == 'h') {
                bookings.add(new Booking(dataVals));
            }
        }

        updateBookings(bookings);

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

            String userId = "" + usersFile.getInt("currUser", 0);

            if (util.userInRes(userId, booking, sharedBookings)) {
                System.out.println("enter");
                actionButton.setEnabled(false);
                actionButton.setText("Booked!");
            }
            else {
                System.out.println("did not enter");
                if (availSpots == 0) {
                    if (sharedWaitlist.getString(booking.getResId(), "").contains(userId)) {
                        actionButton.setEnabled(false);
                        actionButton.setText("Added to the waitlist");
                    }
                    else {
                        actionButton.setText("Notify Me");
                        actionButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                actionButton.setText("Added to the waitlist");
                                actionButton.setEnabled(false);
                                util.addToWaitlist(userId, booking, sharedWaitlist, sharedWaitlistEditor);
                            }
                        });
                    }
                }
                else {
                    actionButton.setText("Book Now");
                    actionButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            actionButton.setEnabled(false);
                            actionButton.setText("Booked!");
                            availText.setText((availSpots - 1) + " spots open");
                            addReservation(userId, booking);
                        }
                    });
                }
            }

            if (layout != null) {
                layout.addView(timeText);
                layout.addView(availText);
                layout.addView(actionButton);
            }
        }
    }

    private void addReservation(String userId, Booking booking) {
        sharedBookingsEditor.putString(booking.getResId(), sharedBookings.getString(booking.getResId(), "") + "," + userId);
        sharedBookingsEditor.apply();

        usersFileEditor.putString(userId, usersFile.getString(userId, "") + "," + booking.getResId());
        usersFileEditor.apply();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}