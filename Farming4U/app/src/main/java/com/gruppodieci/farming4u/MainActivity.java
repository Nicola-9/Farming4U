package com.gruppodieci.farming4u;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.gruppodieci.farming4u.business.SensorInformationBusiness;
import com.gruppodieci.farming4u.fragments.GroundStatusFragment;
import com.gruppodieci.farming4u.fragments.SensorInformationFragment;
import com.gruppodieci.farming4u.business.InstanziateFiles;
import com.gruppodieci.farming4u.business.SavingFiles;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_layout);

        toolbar = findViewById(R.id.toolbar);
        this.bottomBar = findViewById(R.id.bottomNavigationMenu);

        this.bottomBar.setSelectedItemId(R.id.home);

        this.bottomNavigationMenu = new BottomNavigationMenu(this);
        this.bottomNavigationMenu.onMenuItemClick(bottomBar);

        new SavingFiles(getApplicationContext());
        InstanziateFiles.instanziateFiles();
        setSupportActionBar(toolbar);
        showToolbarMenu=true;
    }

    public void showToolbarMenu(boolean show){
        showToolbarMenu=show;
        invalidateOptionsMenu();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!showToolbarMenu)
            return false;
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.settings_menu, menu);

        return true;
    }

    public static MaterialToolbar getToolbar(){
        return toolbar;
    }

    @Override
    public void onBackPressed() {
        if (BottomNavigationMenu.getActiveFragment() instanceof SensorInformationFragment) {
            GroundStatusFragment.setSensor(SensorInformationBusiness.getSensorName());

            Fragment newFrag = new GroundStatusFragment();

            BottomNavigationMenu.replaceFragment(newFrag);
            BottomNavigationMenu.setActiveFragment(newFrag);
        } else {
            super.onBackPressed();
        }
    }

    private boolean showToolbarMenu;
    static MaterialToolbar toolbar;
    private BottomNavigationView bottomBar;
    private BottomNavigationMenu bottomNavigationMenu;
}
