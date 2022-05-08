package com.example.myapp;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapp.data.DataUser;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentStateAdapter {


    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0 :
                return new HomeFragment();
            case 1 :
                return new HospitalFragment();
            case 2 :
                return new NewsFragment();
            case 3 :

                if( MyApplication.getMyApplication().getDataUser().getCarepayactive() ==1){
                    return new CarePayActiveFragment();
                }else {
                    return new CarePayFragment();
                }
            case 4 :
                return new ProfileFragment();
            default:
                return new HomeFragment();
        }
    }


    @Override
    public int getItemCount() {
        return 5;
    }
}
