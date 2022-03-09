package com.example.project3mon.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.project3mon.R;
import com.example.project3mon.dto.Notification;

import java.util.List;

public class NotificationAdapter extends BaseAdapter {

    private List<Notification> listNotification;

    public NotificationAdapter(List<Notification> listNotification) {
        this.listNotification = listNotification;
    }

    @Override
    public int getCount() {
        return listNotification.size();
    }

    @Override
    public Object getItem(int i) {
        return listNotification.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listNotification.get(i).getID();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        View viewProduct;
        if (convertView == null) {
            viewProduct = View.inflate(viewGroup.getContext(), R.layout.notification_view, null);
        } else viewProduct = convertView;

        //Bind sữ liệu phần tử vào View
        Notification notification = (Notification) getItem(i);
        //((RoundedImageView) viewProduct.findViewById(R.id.img_notification)).setImageResource(notification.getID());
        ((TextView) viewProduct.findViewById(R.id.txtNotiSender)).setText(notification.getUserID());
        ((TextView) viewProduct.findViewById(R.id.txtNotiMessage)).setText(notification.getMessage());
        return viewProduct;
    }
}
