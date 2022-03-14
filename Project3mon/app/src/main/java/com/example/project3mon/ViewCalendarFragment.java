package com.example.project3mon;

import android.graphics.Color;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewCalendarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class ViewCalendarFragment extends Fragment {

    private LinearLayout layoutView;
    private BottomNavigationItemView viewCalendar;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ViewCalendarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewCalendarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewCalendarFragment newInstance(String param1, String param2) {
        ViewCalendarFragment fragment = new ViewCalendarFragment();
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
        View view= inflater.inflate(R.layout.fragment_view_calendar, container, false);
        viewCalendar = getActivity().findViewById(R.id.action_view_calendar);
        viewCalendar.setEnabled(false);
        List<EventDay> events = new ArrayList<>();
        GetData dao=new GetData();
        Bundle bundle=getActivity().getIntent().getExtras();
        List<Calendar> list=new ArrayList<>();
        if(!(bundle==null)){
            String id=(String)bundle.get("ID");
            try {
                list=dao.getListday(id);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        for (Calendar calendar:list) {
            events.add(new EventDay(calendar, R.drawable.ic_baseline_event_available_24));
        }

        CalendarView calendarView = (CalendarView) view.findViewById(R.id.calendarView);
        calendarView.setEvents(events);
        layoutView=view.findViewById(R.id.layoutView);

        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                int date=eventDay.getCalendar().getTime().getDate();
                for (int i = 0; i < events.size(); i++) {
                    int event=events.get(i).getCalendar().getTime().getDate();
                    if (event==date){
                        List<String> string=new ArrayList<>();
                        List<User> list=new ArrayList<>();
                        try {
                            string=dao.getSchedulesCustomer((String)bundle.get("ID"),events.get(i).getCalendar().getTime());
                            list=dao.getPersonalTrainer((String)bundle.get("ID"));
                            TextView txtFromTo=view.findViewById(R.id.txtFromTo);
                            txtFromTo.setText(string+"");
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        layoutView.setVisibility(View.VISIBLE);
                        break;
                    }else {
                        layoutView.setVisibility(View.GONE);
                    }
                }


            }
        });
        TextView txtlink =(TextView) view.findViewById(R.id.txtlink);
        txtlink.setMovementMethod(LinkMovementMethod.getInstance());
        txtlink.setLinkTextColor(Color.GREEN);
        return view;
    }
}