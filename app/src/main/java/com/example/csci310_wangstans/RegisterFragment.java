package com.example.csci310_wangstans;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {
    Button buttonLogin, buttonRegister;
    EditText editUserName, editName, editPassword, editEmail;
    String username, name, email, password;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        sharedPreferences = context.getSharedPreferences( "usersFile", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        super.onAttach(context);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
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
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        editUserName = view.findViewById(R.id.editUserName);
        editName = view.findViewById(R.id.editName);
        editPassword = view.findViewById(R.id.editPassword);
        editEmail = view.findViewById(R.id.editEmail);

        buttonRegister = view.findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = editUserName.getText().toString();
                name = editName.getText().toString();
                email = editEmail.getText().toString();
                password = editPassword.getText().toString();
                String value = username + ", " + name + ", " + email + ", " + password;
                int count = sharedPreferences.getInt( "count", 0);
                String count_string = "" + count;

                editor.putString(count_string, value);
                editor.putInt("count", count+1);
                editor.apply();
                Toast.makeText(getContext(), "Registered", Toast.LENGTH_SHORT).show();

                NavHostFragment.findNavController(RegisterFragment.this)
                        .navigate(R.id.action_RegisterFragment_to_LoginFragment);
            }
        });

        return view;
    }

}