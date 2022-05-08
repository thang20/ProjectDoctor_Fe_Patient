package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {
    private  BottomNavigationView mbottomNavigationView;
    private ViewPager2 mviewPager;
    private TextView textView_toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mbottomNavigationView = findViewById(R.id.bottom_nav);
        mviewPager = findViewById(R.id.view_paper);
        textView_toolbar = findViewById(R.id.txt_toolbar);
        textView_toolbar.setText("Y.DT");
        setUpViewPaper();





        mbottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.firstFragment:
                        mviewPager.setCurrentItem(0);
                        textView_toolbar.setText("Y.DT");
                        break;
                    case R.id.secondFragment:
                        mviewPager.setCurrentItem(1);
                        textView_toolbar.setText("Pay Hospital Fees");
                        break;
                    case R.id.thirstFragment:
                        mviewPager.setCurrentItem(2);
                        textView_toolbar.setText("News");
                        break;
                    case R.id.fourFragment:
                        mviewPager.setCurrentItem(3);
                        textView_toolbar.setText("Care Pay");
                        break;
                    case R.id.fiveFragment:
                        mviewPager.setCurrentItem(4);
                        textView_toolbar.setText("Your Personal Profile");
                        break;
                }

                return true;
            }
        });

    }

    private void setUpViewPaper() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(Home.this);
        mviewPager.setAdapter(viewPagerAdapter);

    }


}