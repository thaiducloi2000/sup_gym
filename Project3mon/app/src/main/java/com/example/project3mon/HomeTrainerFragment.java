package com.example.project3mon;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.time.Year;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeTrainerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeTrainerFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeTrainerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeTrainerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeTrainerFragment newInstance(String param1, String param2) {
        HomeTrainerFragment fragment = new HomeTrainerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_trainer, container, false);
        TextView txtToday=view.findViewById(R.id.txtToday);
        Calendar calendar=Calendar.getInstance();
        int day=calendar.get(Calendar.DATE);
        int month=calendar.get(Calendar.MONTH);
        int year=calendar.get(Calendar.YEAR);
        int dayInweek=calendar.get(Calendar.DAY_OF_WEEK);
        if(dayInweek==1){
            String s="Chủ Nhật,"+day+"/"+month+"/"+ year;
            txtToday.setText(s);
        }if(dayInweek==2){
            String s="Thứ 2,"+day+"/"+month+"/"+ year;
            txtToday.setText(s);
        }if(dayInweek==3){
            String s="Thứ 3,"+day+"/"+month+"/"+ year;
            txtToday.setText(s);
        }if(dayInweek==4){
            String s="Thứ 4,"+day+"/"+month+"/"+ year;
            txtToday.setText(s);
        }if(dayInweek==5){
            String s="Thứ 5,"+day+"/"+month+"/"+ year;
            txtToday.setText(s);
        }if(dayInweek==6){
            String s="Thứ 6,"+day+"/"+month+"/"+ year;
            txtToday.setText(s);
        }if(dayInweek==7){
            String s="Thứ 7,"+day+"/"+month+"/"+ year;
            txtToday.setText(s);
        }
        return view;
    }
}