package com.example.project3mon;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class BookingFragment extends Fragment {

    private RecyclerView rcvData;
    private TrainerListAdapter trainerListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_booking, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        try {
            trainerListAdapter = new TrainerListAdapter(getActivity(),getListTrainer());
        } catch (Exception e) {
            e.printStackTrace();
        }
        rcvData = getActivity().findViewById(R.id.rcv_data_booking);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rcvData.setLayoutManager(linearLayoutManager);
        rcvData.setAdapter(trainerListAdapter);
    }


    private List<User> getListTrainer() throws Exception {
        List<User> listTrainer = new ArrayList<>();
        GetData data = new GetData();
        listTrainer = data.getListTrainer();
        return listTrainer;
    }
}