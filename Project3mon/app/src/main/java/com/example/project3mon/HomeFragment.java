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

        userAdapter = new UserAdapter(getActivity(),getListUser());
        userAdapter_2 = new UserAdapter(getActivity(),getListExercise());
        userAdapter_3 = new UserAdapter(getActivity(),getListDiet());
        userAdapter_4 = new UserAdapter(getActivity(),getListFunctionalFoods());


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

    private List<User> getListUser() {
        List<User> listUser = new ArrayList<>();
        listUser.add(new User(R.drawable.hi, "Trọng Thắng-", "Personal Trainer"));
        listUser.add(new User(R.drawable.hi, "Công Danh-", "Personal Trainer"));
        listUser.add(new User(R.drawable.hi, "Kim Long-", "Personal Trainer"));
        listUser.add(new User(R.drawable.hi, "Đức Lợi-", "Personal Trainer"));

        return listUser;
    }

    private List<User> getListExercise() {
        List<User> listExercise = new ArrayList<>();
        listExercise.add(new User(R.drawable.pic_exercise_hitdat, "Bài tập chống đẩy", ""));
        listExercise.add(new User(R.drawable.pic_exercise_hitdat, "Chống đẩy chụm tay", ""));
        listExercise.add(new User(R.drawable.pic_exercise_hitdat, "Bài tập hít xà kép", ""));
        listExercise.add(new User(R.drawable.pic_exercise_hitdat, "Tập gập bụng", ""));

        return listExercise;
    }

    private List<User> getListDiet() {
        List<User> listDiet = new ArrayList<>();
        listDiet.add(new User(R.drawable.pic_diet_anchay, "Chế độ ăn lỏng", ""));
        listDiet.add(new User(R.drawable.pic_diet_anchay, "Chế độ ăn ít Calo", ""));
        listDiet.add(new User(R.drawable.pic_diet_anchay, "Chế độ giàu calo", ""));
        listDiet.add(new User(R.drawable.pic_diet_anchay, "Chế độ ăn chay", ""));

        return listDiet;
    }

    private List<User> getListFunctionalFoods() {
        List<User> listFunctional = new ArrayList<>();
        listFunctional.add(new User(R.drawable.pic_ff_whey, "Whey Protein", ""));
        listFunctional.add(new User(R.drawable.pic_ff_whey, "Casein", ""));
        listFunctional.add(new User(R.drawable.pic_ff_whey, "Creatine", ""));
        listFunctional.add(new User(R.drawable.pic_ff_whey, "BCAA", ""));

        return listFunctional;
    }

}