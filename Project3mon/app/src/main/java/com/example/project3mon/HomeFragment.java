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

public class HomeFragment extends Fragment {

    private RecyclerView rcvData, rcvData_2, rcvData_3, rcvData_4;
    private UserAdapter userAdapter, userAdapter_2, userAdapter_3, userAdapter_4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        userAdapter = new UserAdapter(getActivity(),getListTrainer());
        userAdapter_2 = new UserAdapter(getActivity(),getListFavoriteTrainer());
        userAdapter_3 = new UserAdapter(getActivity(),getListMostExpTrainer());
        userAdapter_4 = new UserAdapter(getActivity(),getListNewTrainer());


        rcvData = getActivity().findViewById(R.id.rcv_data_1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        rcvData.setLayoutManager(linearLayoutManager);
        rcvData.setAdapter(userAdapter);

        rcvData_2 = getActivity().findViewById(R.id.rcv_data_2);
        LinearLayoutManager linearLayoutManager_2 = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        rcvData_2.setLayoutManager(linearLayoutManager_2);
        rcvData_2.setAdapter(userAdapter_2);

        rcvData_3 = getActivity().findViewById(R.id.rcv_data_3);
        LinearLayoutManager linearLayoutManager_3 = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        rcvData_3.setLayoutManager(linearLayoutManager_3);
        rcvData_3.setAdapter(userAdapter_3);

        rcvData_4 = getActivity().findViewById(R.id.rcv_data_4);
        LinearLayoutManager linearLayoutManager_4 = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        rcvData_4.setLayoutManager(linearLayoutManager_4);
        rcvData_4.setAdapter(userAdapter_4);

    }

    private List<User> getListTrainer() {
        List<User> listTrainer = new ArrayList<>();
        listTrainer.add(
                new User(R.drawable.trainer1, "Trọng Thắng", "Personal Trainer",
                        "Sáu mui kinh nghiem pt duoc 7 nam", "Nam", "27","0351231230"));
        listTrainer.add(
                new User(R.drawable.trainer2, "Đức Lợi", "Personal Trainer",
                        "Sáu mui kinh nghiem pt duoc 10 nam, vô địch thế giới năm 2023", "Nam", "30","0354555666"));
        listTrainer.add(
                new User(R.drawable.trainer3, "Công Danh", "Personal Trainer",
                        "Trẻ Trung, Năng Động, Yêu Nghề", "Nam", "21","0351111111"));
        listTrainer.add(
                new User(R.drawable.trainer4, "Gia Huy", "Personal Trainer",
                        "Nhiệt Tình Với Công Việc", "Nam", "20","0342183282"));
        listTrainer.add(
                new User(R.drawable.trainer5, "Kim Long", "Personal Trainer",
                        "Sáu mui kinh nghiem pt duoc ba-bốn nam", "Nam", "23","0351231230"));
        listTrainer.add(
                new User(R.drawable.trainer1, "Tấn Phát", "Personal Trainer",
                        "Sáu mui kinh nghiem pt duoc 6 nam", "Nam", "25","03558345837"));

        return listTrainer;
    }

    private List<User> getListFavoriteTrainer() {
        List<User> listFavoriteTrainer = new ArrayList<>();
        listFavoriteTrainer.add(
                new User(R.drawable.trainer1, "Trọng Thắng", "Personal Trainer",
                        "Sáu mui kinh nghiem pt duoc ba-bốn nam", "Nam", "27","0351231230"));
        listFavoriteTrainer.add(
                new User(R.drawable.trainer2, "Đức Lợi", "Personal Trainer",
                        "Sáu mui kinh nghiem pt duoc 10 nam, vô địch thế giới năm 2023", "Nam", "30","0354555666"));
        listFavoriteTrainer.add(
                new User(R.drawable.trainer1, "Tấn Phát", "Personal Trainer",
                        "Sáu mui kinh nghiem pt duoc ba-bốn nam", "Nam", "25","03558345837"));

        return listFavoriteTrainer;
    }

    private List<User> getListMostExpTrainer() {
        List<User> listMostExpTrainer = new ArrayList<>();
        listMostExpTrainer.add(
                new User(R.drawable.trainer2, "Đức Lợi", "Personal Trainer",
                        "Sáu mui kinh nghiem pt duoc 10 nam, vô địch thế giới năm 2023", "Nam", "30","0354555666"));
        listMostExpTrainer.add(
                new User(R.drawable.trainer1, "Trọng Thắng", "Personal Trainer",
                        "Sáu mui kinh nghiem pt duoc 7 nam", "Nam", "27","0351231230"));
        listMostExpTrainer.add(
                new User(R.drawable.trainer1, "Tấn Phát", "Personal Trainer",
                        "Sáu mui kinh nghiem pt duoc 6 nam", "Nam", "25","03558345837"));

        return listMostExpTrainer;
    }

    private List<User> getListNewTrainer() {
        List<User> listNewTrainer = new ArrayList<>();
        listNewTrainer.add(
                new User(R.drawable.trainer3, "Công Danh", "Personal Trainer",
                        "Trẻ Trung, Năng Động, Yêu Nghề", "Nam", "21","0351111111"));
        listNewTrainer.add(
                new User(R.drawable.trainer4, "Gia Huy", "Personal Trainer",
                        "Nhiệt Tình Với Công Việc", "Nam", "20","0342183282"));

        return listNewTrainer;
    }

}