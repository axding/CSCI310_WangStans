package com.example.csci310_wangstans;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class RegisterFragment extends Fragment {
    Button buttonLogin, buttonRegister, buttonRegisterToLogin;
    EditText editUserName, editUSCID, editName, editPassword, editEmail;
    String username, uscid, name, email, password;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    int confirmReg = 0;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        sharedPreferences = context.getSharedPreferences("usersFile", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        super.onAttach(context);
    }

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
        editUSCID = view.findViewById(R.id.editUserID);
        editName = view.findViewById(R.id.editName);
        editPassword = view.findViewById(R.id.editPassword);
        editEmail = view.findViewById(R.id.editEmail);

        buttonRegisterToLogin = view.findViewById(R.id.buttonRegisterToLogin);

        buttonRegisterToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(RegisterFragment.this)
                        .navigate(R.id.action_RegisterFragment_to_LoginFragment);
            }
        });

        buttonRegister = view.findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = editUserName.getText().toString();
                uscid = editUSCID.getText().toString();
                name = editName.getText().toString();
                email = editEmail.getText().toString();
                password = editPassword.getText().toString();

                if (TextUtils.isEmpty(username)) {
                    editUserName.setError("Username cannot be empty");
                    return;
                }
                if (TextUtils.isEmpty(uscid)) {
                    editUSCID.setError("USC ID cannot be empty");
                    return;
                }
                if (TextUtils.isEmpty(name)) {
                    editName.setError("Name cannot be empty");
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    editEmail.setError("Email cannot be empty");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    editPassword.setError("Password cannot be empty");
                    return;
                }
                if (!isEmailValid(email) || !isUSCEmailValid(email)) {
                    editEmail.setError("Invalid email");
                    return;
                }
                if (!isUSCIDValid(uscid)) {
                    editUSCID.setError("Invalid ID");
                    return;
                }
                if(duplicateEmail(email)) {
                    editEmail.setError("Email address already exists");
                    return;
                }
                if(duplicateUSCID(uscid)) {
                    editUSCID.setError("USC ID already exists");
                    return;
                }

                String value = username + "," + name + "," + email + "," + password + ", " + uscid;
                int count = sharedPreferences.getInt("count", 0);
                String count_string = "" + count;

                editor.putString(count_string, value);
                editor.putInt("count", count + 1);
                editor.apply();

                Toast.makeText(getContext(), "Registered", Toast.LENGTH_LONG).show();

                NavHostFragment.findNavController(RegisterFragment.this)
                        .navigate(R.id.action_RegisterFragment_to_LoginFragment);


            }
        });

        return view;
    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    boolean isUSCEmailValid(String email) {
        return email.substring(email.length() - 8).equals("@usc.edu");
    }

    boolean isUSCIDValid(String id) {
        return id.length() == 10;
    }

    public boolean duplicateEmail(String newEmail) {
        System.out.println("current email: " + newEmail);
        int count = sharedPreferences.getInt("count", 0);
        for (int i = -2; i < count; i++) {
            String key = i + "";
            String value = sharedPreferences.getString(key, "null");

            if (!(value.equals("null"))) {
                String[] userInfo = value.split(",");
                if (userInfo.length < 4) break;
                String email = userInfo[2];
                System.out.println("checking email: " + email);

                if (newEmail.equals(email)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean duplicateUSCID(String newUSCID) {
        System.out.println("current usc id: " + newUSCID);
        int count = sharedPreferences.getInt("count", 0);
        for (int i = -2; i < count; i++) {
            String key = i + "";
            String value = sharedPreferences.getString(key, "null");

            if (!(value.equals("null"))) {
                String[] userInfo = value.split(",");
                if (userInfo.length < 4) break;
                String uscid = userInfo[4];
                System.out.println("checking usc id: " + uscid);

                if (newUSCID.equals(uscid.trim())) {
                    return true;
                }
            }
        }
        return false;
    }
}