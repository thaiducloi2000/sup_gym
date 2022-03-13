package com.example.project3mon;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.makeramen.roundedimageview.RoundedImageView;


public class TrainerProfileFragment extends Fragment {
    private RoundedImageView avatar;
    private TextView txtName;
    private BottomNavigationItemView trainerProfile;

    public TrainerProfileFragment() {
        // Required empty public constructor
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onStart() {
        super.onStart();
        trainerProfile = getActivity().findViewById(R.id.action_profile);
        trainerProfile.setEnabled(false);
        Bundle bundle = getActivity().getIntent().getExtras();
        if(bundle == null){
            return;
        }
        GetData data = new GetData();
        User userProfile = (User) bundle.get("userProfile");
        User user = null;
        try {
            user = data.getUserProfile(userProfile.getID()); // load lại db sau khi update
            avatar = getActivity().findViewById(R.id.imageAvatarProfile);
            txtName = getActivity().findViewById(R.id.txtNameProfile);
            int imgResID = this.getResources().getIdentifier(user.getImage(), "drawable", getActivity().getPackageName());
            avatar.setImageResource(imgResID);
            txtName.setText(user.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trainer_profile, container, false);
    }
}