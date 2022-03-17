package com.example.project3mon.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.project3mon.R;
import com.example.project3mon.dto.HistoryRecharge;
import com.example.project3mon.dto.Notification;

import java.util.List;

public class HistoryRechargeAdapter extends BaseAdapter {
    private List<HistoryRecharge> listHistory;

    public HistoryRechargeAdapter(List<HistoryRecharge> listHistory) {
        this.listHistory = listHistory;
    }

    @Override
    public int getCount() {
        if(listHistory.size() == 0){
            return 0;
        }
        return listHistory.size();
    }

    @Override
    public Object getItem(int i) {
        return listHistory.get(i);
    }

    @Override
    public long getItemId(int i) {
        return Long.parseLong(listHistory.get(i).getID());
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View viewProduct;
        if (convertView == null) {
            viewProduct = View.inflate(viewGroup.getContext(), R.layout.list_history_recharge, null);
        } else viewProduct = convertView;
        HistoryRecharge history = (HistoryRecharge) getItem(i);
        ((TextView) viewProduct.findViewById(R.id.txtDateRecharge)).setText(history.getDate());
        ((TextView) viewProduct.findViewById(R.id.txtCastCharge)).setText((int)history.getCast()  + " VND");
        return viewProduct;
    }
}
