package com.example.project3mon;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project3mon.dto.Video;

import java.time.Year;
import java.util.Calendar;


public class HomeTrainerFragment extends Fragment {


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