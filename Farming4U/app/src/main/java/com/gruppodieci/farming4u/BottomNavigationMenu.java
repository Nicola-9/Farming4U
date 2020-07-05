package com.gruppodieci.farming4u;

import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.gruppodieci.farming4u.fragments.GroundStatusFragment;
import com.gruppodieci.farming4u.fragments.SeminaFragment;
import com.gruppodieci.farming4u.fragments.NewNoteFragment;
import com.gruppodieci.farming4u.fragments.NotesFragment;
import com.gruppodieci.farming4u.fragments.RiepilogoFragment;
import com.gruppodieci.farming4u.fragments.WarningFragment;

public class BottomNavigationMenu {

    public BottomNavigationMenu(AppCompatActivity instance){
        this.instance = instance;
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
                        activeFragment = new GroundStatusFragment();
                        replaceFragment(activeFragment);
                        return true;
                    case R.id.grounds:
                        activeFragment = new SeminaFragment();
                        replaceFragment(activeFragment);
                        return true;
                }

                return false;
            }
        });
    }

    public void replaceFragment(Fragment toReplace){
        FragmentManager fragmentManager = this.instance.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, toReplace);

        fragmentTransaction.commit();
    }

    private AppCompatActivity instance;
    public Fragment activeFragment;
}
