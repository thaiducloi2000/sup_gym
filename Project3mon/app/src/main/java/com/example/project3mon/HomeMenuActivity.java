package com.example.project3mon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeMenuActivity extends AppCompatActivity {

    private BottomNavigationView botNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_menu);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        botNav=findViewById(R.id.botNav);
        getSupportFragmentManager().beginTransaction().replace(R.id.body_container,new HomeFragment()).commit();
        botNav.setSelectedItemId(R.id.action_home);
        botNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment=null;
                AppCompatActivity activity = null;
                switch (item.getItemId()){
                    case R.id.action_home:
                        fragment=new HomeFragment();
                        break;
                    case R.id.action_booking:
                        fragment=new BookingFragment();
                        break;
                    case R.id.action_view_calendar:
                        fragment=new ViewCalendarFragment();
                        break;
                    case R.id.action_profile:
                        fragment=new ProfileFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.body_container,fragment).commit();
                return true;
            }
        });
    }
    public void clickToEditProfile(View view) {
        Intent intent = new Intent(this,EditProfileActivity.class);
        startActivity(intent);
    }

    public void clickToChangeAccountSetting(View view) {
        Intent intent = new Intent(this, ChangeSettingActivity.class);
        startActivity(intent);
    }

    public void clickToTrainer1(View view) {
        Intent intent = new Intent(this , Trainer1Activity.class);
        startActivity(intent);
    }
}