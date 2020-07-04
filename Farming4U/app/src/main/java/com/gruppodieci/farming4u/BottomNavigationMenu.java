package com.gruppodieci.farming4u;

import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.gruppodieci.farming4u.fragments.GroundStatusFragment;

public class BottomNavigationMenu {

    public BottomNavigationMenu(AppCompatActivity instanceA){
        instance = instanceA;
    }

    public void onMenuItemClick(BottomNavigationView bottomBar){
        bottomBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                switch (itemId){
                    case R.id.home:

                        return true;
                    case R.id.groundStatus:
                        activeFragment = new GroundStatusFragment();
                        replaceFragment(activeFragment);
                        return true;
                    case R.id.grounds:
                        activeFragment = null;
                        return true;
                }

                return false;
            }
        });
    }

    public static void replaceFragment(Fragment toReplace){
        FragmentManager fragmentManager = instance.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, toReplace);

        fragmentTransaction.commit();
    }

    public static AppCompatActivity getInstance(){
        return instance;
    }

    public static Fragment getActiveFragment(){
        return activeFragment;
    }

    public static void setActiveFragment(Fragment fragment){
        activeFragment = fragment;
    }

    static AppCompatActivity instance;
    static Fragment activeFragment;
}
