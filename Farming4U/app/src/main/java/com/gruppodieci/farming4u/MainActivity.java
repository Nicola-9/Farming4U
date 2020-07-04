package com.gruppodieci.farming4u;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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

        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.settings_menu, menu);

        return true;
    }

    public static MaterialToolbar getToolbar(){
        return toolbar;
    }

    static MaterialToolbar toolbar;
    private BottomNavigationView bottomBar;
    private BottomNavigationMenu bottomNavigationMenu;
}
