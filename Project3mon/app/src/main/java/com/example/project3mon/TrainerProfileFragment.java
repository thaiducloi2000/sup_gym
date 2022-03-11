package com.example.project3mon;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;


public class TrainerProfileFragment extends Fragment {
    private RoundedImageView avatar;
    private TextView txtName;

    public TrainerProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle bundle = getActivity().getIntent().getExtras();
        if(bundle == null){
            return;
        }
        User user = (User) bundle.get("userProfile");
        avatar = getActivity().findViewById(R.id.imageAvatarProfile);
        txtName = getActivity().findViewById(R.id.txtNameProfile);
        int imgResID = this.getResources().getIdentifier(user.getImage(), "drawable", getActivity().getPackageName());
        avatar.setImageResource(imgResID);
        txtName.setText(user.getName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trainer_profile, container, false);
    }
}