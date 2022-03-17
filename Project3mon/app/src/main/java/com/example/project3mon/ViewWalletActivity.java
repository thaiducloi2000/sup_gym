package com.example.project3mon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.project3mon.adapter.HistoryRechargeAdapter;
import com.example.project3mon.dao.HistoryRechargeDAO;
import com.example.project3mon.dto.HistoryRecharge;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewWalletActivity extends AppCompatActivity {
    private ListView listViewHistory;
    private List<HistoryRecharge> listHistory;
    private HistoryRechargeAdapter adapter;
    private TextView txtSupCoins;
    private int wallet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_wallet);
        listViewHistory = findViewById(R.id.listViewHistoryRecharge);
        txtSupCoins = findViewById(R.id.txtWallet);
        HistoryRechargeDAO dao = new HistoryRechargeDAO();
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        String userID = (String) bundle.get("userID");
        try {
            listHistory = dao.getListHistoryRecharge(userID);
            adapter = new HistoryRechargeAdapter(listHistory);
            listViewHistory.setAdapter(adapter);
            GetData data = new GetData();
            wallet = data.getWalletByID(userID);
            txtSupCoins.setText(wallet + " SUP Coins");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void clickToBack3(View view) {
        finish();
    }
}