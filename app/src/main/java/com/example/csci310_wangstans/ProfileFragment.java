package com.example.csci310_wangstans;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.csci310_wangstans.databinding.FragmentMapHomePageBinding;
import com.example.csci310_wangstans.databinding.FragmentProfileBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    private ImageButton home, about, profile;
    private TextView name, email, uscid, username, password;
    private FragmentProfileBinding binding;
    private SharedPreferences sp;
    private SharedPreferences.Editor ed;
    private Button buttonLogout;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        sp = context.getSharedPreferences( "usersFile", Context.MODE_PRIVATE);
        ed = sp.edit();
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Creating nav bar
        home = binding.home;
        about = binding.about;
        profile = binding.profile;
        name = binding.name2;
        uscid = binding.uscid2;
        email = binding.email2;
        username = binding.username2;
        password = binding.password2;

        int currUser=sp.getInt("currUser", -1);
        String value = sp.getString(currUser+"","null");

        String name2 = "N/A";
        String username2 = "N/A";
        String email2 = "N/A";
        String password2 = "N/A";
        String uscid2 = "N/A";

        if(!(value.equals("null"))) {
            String[] userInfo = value.split(",");
            username2 = userInfo[0];
            name2 = userInfo[1];
            email2 = userInfo[2];
            password2 = userInfo[3];
            uscid2 = userInfo[4];
        }

        name.setText(name2);
        username.setText(username2);
        email.setText(email2);
        uscid.setText(uscid2);
        password.setText(password2);

        buttonLogout = view.findViewById(R.id.buttonLogOut);

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(ProfileFragment.this)
                        .navigate(R.id.action_ProfileFragment_to_LoginPage);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(ProfileFragment.this)
                        .navigate(R.id.action_ProfileFragment_to_MapHomePage);
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(ProfileFragment.this)
                        .navigate(R.id.action_ProfileFragment_to_AboutFragment);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {}
        });
        // End of nav bar
    }
}