package com.example.loginactivity.ConsumerActivities.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.loginactivity.Authentication.Login;
import com.example.loginactivity.R;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileFragment extends Fragment {

    Button button;
    FirebaseAuth mAuth;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        mAuth = FirebaseAuth.getInstance();

        return root;
    }
}