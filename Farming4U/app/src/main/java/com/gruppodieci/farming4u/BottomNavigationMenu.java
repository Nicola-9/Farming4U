package com.gruppodieci.farming4u;

import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.gruppodieci.farming4u.activity.BasicActivity;
import com.gruppodieci.farming4u.fragments.CuraPianteFragment;
import com.gruppodieci.farming4u.fragments.GroundStatusFragment;
import com.gruppodieci.farming4u.fragments.SeminaFragment;
import com.gruppodieci.farming4u.fragments.GroundsFragment;
import com.gruppodieci.farming4u.fragments.RiepilogoFragment;

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
                        activeFragment = new RiepilogoFragment();
                        replaceFragment(activeFragment);
                        return true;
                    case R.id.groundStatus:
                        GroundStatusFragment.setSensor("beacon");

                        activeFragment = new GroundStatusFragment();
                        replaceFragment(activeFragment);

                        BasicActivity.getToolbar().setTitle("Stato Terreno");

                        return true;
                    case R.id.grounds:

                        activeFragment = new GroundsFragment();
                        replaceFragment(activeFragment);

                        BasicActivity.getToolbar().setTitle("Terreni");

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

    public static void replaceFragment(Fragment toReplace, boolean addToBackstack){
        FragmentManager fragmentManager = instance.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, toReplace);
        if (addToBackstack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

    public static void replaceFragment(int containerViewId, Fragment toReplace){
        FragmentManager fragmentManager = instance.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(containerViewId, toReplace);

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
