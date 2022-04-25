package com.example.csci310_wangstans;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginFragment extends Fragment {

    Button buttonLogin, buttonRegister;
    EditText editUserName, editPassword;
    String username, password;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    SharedPreferences waitManager;
    SharedPreferences.Editor waitEditor;
    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {

        Populator p = new Populator(getContext());
        p.setCurrentUser(-2);

        sharedPreferences = context.getSharedPreferences( "usersFile", Context.MODE_PRIVATE);

        editor = sharedPreferences.edit();

//        editor.putString("-1", "student,student,student,student");
//        editor.apply();


        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        editUserName = view.findViewById(R.id.editUserName);
        editPassword = view.findViewById(R.id.editPassword);

        buttonLogin = view.findViewById(R.id.buttonLogin);
        buttonRegister = view.findViewById(R.id.buttonRegister);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = editUserName.getText().toString();
                password = editPassword.getText().toString();
                System.out.println("current username: " + username);
                System.out.println("curent password: " + password);
                if(TextUtils.isEmpty(username)) {
                    editUserName.setError("Username cannot be empty");
                    return;
                }
                if(TextUtils.isEmpty(password)) {
                    editPassword.setError("Password cannot be empty");
                    return;
                }

                int count = sharedPreferences.getInt("count", 0);

                for(int i = -2; i < count; i ++) {
                    String key = i + "";
                    String value = sharedPreferences.getString(key,"null");

                    if(!(value.equals("null"))) {
                        String[] userInfo = value.split(",");
                        if(userInfo.length<4)  break;
                        String uName = userInfo[0];
                        String uPass = userInfo[3];
                        System.out.println("checking username: " + uName);
                        System.out.println("checking password: " + uPass);

                        if(username.equals(uName) && password.equals(uPass)) {
                            editor.putInt("currUser", i);
                            editor.apply();
                            NavHostFragment.findNavController(LoginFragment.this)
                                    .navigate(R.id.action_LoginFragment_to_MapHomePage);
                        }

                    }
                }
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(LoginFragment.this)
                        .navigate(R.id.action_LoginFragment_to_RegisterFragment);
            }
        });
        return view;
    }
}