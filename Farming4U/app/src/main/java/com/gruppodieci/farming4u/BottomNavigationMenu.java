package com.gruppodieci.farming4u;

import android.util.Log;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.gruppodieci.farming4u.activity.BasicActivity;
import com.gruppodieci.farming4u.business.Warning;
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
                Log.d("DEBUG","Precedente frammento "+BottomNavigationMenu.getPreviousFragment());

                //pulizia backstack
                FragmentManager fm = BasicActivity.getBasicActivity().getSupportFragmentManager();
                for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                    fm.popBackStack();
                }
                switch (itemId){
                    case R.id.home:
                        activeFragment = new RiepilogoFragment();
                        replaceFragment(activeFragment);

                        BasicActivity.getToolbar().setNavigationIcon(null);
                        return true;
                    case R.id.groundStatus:
                        GroundStatusFragment.setSensor("beacon");

                        activeFragment = new GroundStatusFragment();
                        replaceFragment(activeFragment);

                        BasicActivity.getToolbar().setTitle("Stato Terreno");

                        BasicActivity.getToolbar().setNavigationIcon(null);
                        return true;
                    case R.id.grounds:

                        for(Warning warning: RiepilogoFragment.warnings) {

                            warning.setTagClicked(false);

                        }

                        activeFragment = new GroundsFragment();

                        GroundsFragment.setTab("cura");
                        replaceFragment(activeFragment);

                        BasicActivity.getIstance().getSupportActionBar().show();

                        BasicActivity.getToolbar().setTitle("Terreni");
                        BasicActivity.getToolbar().setElevation(0);

                        BasicActivity.getToolbar().setNavigationIcon(null);
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

    public static void setPreviousFragment(String fragment){
        previousFrag = fragment;
    }

    public static String getPreviousFragment(){
        return previousFrag;
    }

    static AppCompatActivity instance;
    static Fragment activeFragment;
    static String previousFrag;
}
