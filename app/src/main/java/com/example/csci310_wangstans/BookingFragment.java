package com.example.csci310_wangstans;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.example.csci310_wangstans.databinding.FragmentBookingBinding;

import java.util.Calendar;

public class BookingFragment extends Fragment {

    private FragmentBookingBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentBookingBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DatePicker datePicker = binding.datePicker.findViewById(R.id.datePicker);

        Calendar today = Calendar.getInstance();
        datePicker.setMinDate(today.getTimeInMillis());

        Calendar threeDaysLater = (Calendar) today.clone();
        threeDaysLater.add(Calendar.DATE, 3);
        datePicker.setMaxDate(threeDaysLater.getTimeInMillis());

        binding.updateDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // nothing for now
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}