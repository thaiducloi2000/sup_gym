package com.example.project3mon;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView rcvData, rcvData_2;
    private UserAdapter userAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        rcvData = getActivity().findViewById(R.id.rcv_data_1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        rcvData.setLayoutManager(linearLayoutManager);

        userAdapter = new UserAdapter(getActivity(),getListUser());
        rcvData.setAdapter(userAdapter);

        rcvData_2 = getActivity().findViewById(R.id.rcv_data_2);
        LinearLayoutManager linearLayoutManager_2 = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        rcvData_2.setLayoutManager(linearLayoutManager_2);

        rcvData_2.setAdapter(userAdapter);

    }

    private List<User> getListUser() {
        List<User> listUser = new ArrayList<>();
        listUser.add(new User(R.drawable.pic7, "Gia Huy 1", "HCM"));
        listUser.add(new User(R.drawable.hi, "Gia Huy 2", "Ha Noi"));
        listUser.add(new User(R.drawable.pic7, "Gia Huy 3", "Da Nang"));
        listUser.add(new User(R.drawable.hi, "Gia Huy 4", "Can Tho"));

        return listUser;
    }
}