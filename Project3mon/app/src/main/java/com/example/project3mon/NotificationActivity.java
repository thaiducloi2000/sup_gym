package com.example.project3mon;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project3mon.adapter.NotificationAdapter;
import com.example.project3mon.dto.Notification;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {

    List<Notification> listNotification;
    NotificationAdapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        listNotification = new ArrayList<>();
        listNotification.add(new Notification("Thái Đức Lợi", "đã theo dõi bạn"));
        listNotification.add(new Notification("Trịnh Gia Huy", "đã theo dõi bạn"));
        listNotification.add(new Notification("Dương Kim Long", "đã theo dõi bạn"));

        adapter = new NotificationAdapter(listNotification);
        listView = findViewById(R.id.listViewNotification);
        listView.setAdapter(adapter);
    }

    public void clicktoBack(View view) {
        finish();
    }
}