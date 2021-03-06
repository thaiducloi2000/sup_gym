package com.example.project3mon;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.sql.SQLException;

public class HomeMenuActivity extends AppCompatActivity {

    private BottomNavigationView botNav;
    ImageView checkmark;
    boolean check = false;
    private int CUSTOMER = 1;
    private int TRAINER = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_menu);

        checkmark = findViewById(R.id.checkMark);

        GetData data=new GetData();
        Bundle bundle=getIntent().getExtras();
        if(bundle==null){
            return;
        }
        String ID=(String)bundle.get("ID");
        int roleID=0;
        try {
            roleID=data.getRole(ID);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        int finalRoleID = roleID;
        if(finalRoleID==1){
            Fragment homeFragment = new HomeFragment();
            homeFragment.setArguments(bundle);
            botNav = findViewById(R.id.botNav);
            getSupportFragmentManager().beginTransaction().replace(R.id.body_container, homeFragment).commit();
            botNav.setSelectedItemId(R.id.action_home);
        }if(finalRoleID==2){
            Fragment homeFragment = new HomeTrainerFragment();
            homeFragment.setArguments(bundle);
            botNav = findViewById(R.id.botNav);
            getSupportFragmentManager().beginTransaction().replace(R.id.body_container, homeFragment).commit();
            botNav.setSelectedItemId(R.id.action_home);
            MenuItem item=botNav.getMenu().findItem(R.id.action_booking);
            item.setVisible(false);
        }
        botNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                    switch (item.getItemId()) {
                    case R.id.action_home:
                        if(finalRoleID==1){
                            fragment = new HomeFragment();
                        }if(finalRoleID==2){
                        fragment = new HomeTrainerFragment();
                        }
                        break;
                    case R.id.action_booking:
                        fragment = new BookingFragment();
                        break;
                    case R.id.action_view_calendar:
                        fragment = new ViewCalendarFragment();
                        if(finalRoleID >=1){
                        fragment=new ViewCalendarFragment();
                        }
                        break;
                    case R.id.action_profile:
                        if(finalRoleID == CUSTOMER){
                            fragment = new ProfileFragment();
                        }
                        if(finalRoleID == TRAINER){
                            fragment = new TrainerProfileFragment();
                        }
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.body_container, fragment).commit();
                return true;
            }
        });
    }

    public void clickToEditProfile(View view) {
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        User userProfile = (User) bundle.get("userProfile");
        bundle.putSerializable("userProfile", userProfile);
        Intent intent = new Intent(this, EditProfileActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void clickToChangeAccountSetting(View view) {
        Intent intent = new Intent(this, ChangeSettingActivity.class);
        startActivity(intent);
    }

    public void clickToTrainer1(View view) {
        Intent intent = new Intent(this, Trainer1Activity.class);
        startActivity(intent);
    }

    public void clickToViewAll(View view) {
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        int roleID = (int) bundle.get("roleID");
        bundle.putSerializable("roleID", roleID);
        Intent intent = new Intent(this, TrainerListActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void clickToEditTrainerProfile(View view) {
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        User userProfile = (User) bundle.get("userProfile");
        bundle.putSerializable("userProfile", userProfile);
        Intent intent = new Intent(this, EditTrainerProfileActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void clickToViewNotification(View view) {
        Intent intent = new Intent(HomeMenuActivity.this, NotificationActivity.class);
        startActivity(intent);
    }

    public void clickToViewVideo(View view) {
        Intent intent = new Intent(HomeMenuActivity.this, VideoList.class);
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        User userProfile = (User) bundle.get("userProfile");
        bundle.putSerializable("userProfile", userProfile);
        intent.putExtras(bundle);
        startActivity(intent);

        startActivity(intent);
    }

    public void clickToCheckMark(View view) {
        if(!check){
            checkmark.setImageResource(R.drawable.icon_checkmark_green);
            check = true;
        }else{
            checkmark.setImageResource(R.drawable.icon_checkmark_gray);
            check = false;
        }
    }

    public void clicktoViewWallet(View view) {
        Intent intent = new Intent(HomeMenuActivity.this, ViewWalletActivity.class);
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        String userID= (String) bundle.get("ID");
        bundle.putSerializable("userID", userID);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void clickToViewListVideoTrainer(View view) {
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        User userProfile = (User) bundle.get("userProfile");
        bundle.putSerializable("userProfile", userProfile);
        Intent intent = new Intent(this, VideoList.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void clickToViewListCustomer(View view) {
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        User userProfile = (User) bundle.get("userProfile");
        bundle.putSerializable("userProfile", userProfile);
        Intent intent = new Intent(this, CustomerList.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void clickToviewSchedules(View view) {
        Bundle bundle=getIntent().getExtras();
        if(bundle==null){
            return;
        }
        String ID=(String)bundle.get("ID");
        Intent intent=new Intent(this,ViewCustomer.class);
        intent.putExtra("ID",ID);
        startActivity(intent);
    }
}