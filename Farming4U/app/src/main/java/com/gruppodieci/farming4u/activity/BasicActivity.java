package com.gruppodieci.farming4u.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
p
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.os.Handler;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.gruppodieci.farming4u.BottomNavigationMenu;
import com.gruppodieci.farming4u.R;
import com.gruppodieci.farming4u.business.SensorInformationBusiness;
import com.gruppodieci.farming4u.fragments.CuraPianteFragment;
import com.gruppodieci.farming4u.fragments.GroundStatusFragment;
import com.gruppodieci.farming4u.fragments.GroundsFragment;
import com.gruppodieci.farming4u.fragments.ImpostazioniSensori;
import com.gruppodieci.farming4u.fragments.ProblemInformationFragment;
import com.gruppodieci.farming4u.fragments.RiepilogoFragment;
import com.gruppodieci.farming4u.fragments.SeminaFragment;
import com.gruppodieci.farming4u.fragments.SensorInformationFragment;
import com.gruppodieci.farming4u.business.InstanziateFiles;
import com.gruppodieci.farming4u.business.SavingFiles;
import com.gruppodieci.farming4u.fragments.SettingsIrrigator;
import com.gruppodieci.farming4u.fragments.TrattamentoTerrenoFragment;

public class BasicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_layout);

        istance = this;

        toolbar = findViewById(R.id.toolbar);
        this.bottomBar = findViewById(R.id.bottomNavigationMenu);

        this.bottomBar.setSelectedItemId(R.id.home);

        this.bottomNavigationMenu = new BottomNavigationMenu(this);
        this.bottomNavigationMenu.onMenuItemClick(bottomBar);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment homeFragment = new RiepilogoFragment();

        fragmentTransaction.add(R.id.fragmentContainer, homeFragment);

        fragmentTransaction.commit();

        BottomNavigationMenu.setActiveFragment(homeFragment);

        new SavingFiles(getApplicationContext());
        InstanziateFiles.instanziateFiles();
        setSupportActionBar(toolbar);
        showToolbarMenu = true;

        instanceThis = this;

        handler = new Handler();

        runnable = new Runnable() {
            public void run() {
                String message="Sono stati riscontrati nuovi problemi.\nAvvia l'applicazione per risolverli.";
                Log.d("DEBUG_NOTIFICHE","Notifica creata");
                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_stat_logoium)
                        .setColor(113163187)
                        .setContentTitle("Attenzione")
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                        .setContentText(message)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setAutoCancel(true);



                createNotificationChannel();
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                notificationManager.notify(1,builder.build());
                handler.postDelayed(runnable, 1000*60*2);

            }
        };
        handler.postDelayed(runnable, 1000*60*2);
    }

    public void showToolbarMenu(boolean show){
        showToolbarMenu = show;
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
        }else
        if(BottomNavigationMenu.getActiveFragment() instanceof SeminaFragment){
            if(BottomNavigationMenu.getPreviousFragment().equals("home")){
                bottomBar.setSelectedItemId(R.id.home);

                Fragment newFrag = new RiepilogoFragment();

                toolbar.setNavigationIcon(null);
                toolbar.setNavigationOnClickListener(null);

                BottomNavigationMenu.replaceFragment(newFrag);
                BottomNavigationMenu.setActiveFragment(newFrag);
            } else{
                super.onBackPressed();
            }
        }else
        if(BottomNavigationMenu.getActiveFragment() instanceof CuraPianteFragment){
            if(BottomNavigationMenu.getPreviousFragment().equals("home")){
                bottomBar.setSelectedItemId(R.id.home);

                Fragment newFrag = new RiepilogoFragment();

                toolbar.setNavigationIcon(null);
                toolbar.setNavigationOnClickListener(null);

                BottomNavigationMenu.replaceFragment(newFrag);
                BottomNavigationMenu.setActiveFragment(newFrag);
            } else{
                super.onBackPressed();
            }
        }else
        if(BottomNavigationMenu.getActiveFragment() instanceof TrattamentoTerrenoFragment){
            if(BottomNavigationMenu.getPreviousFragment().equals("home")){
                bottomBar.setSelectedItemId(R.id.home);

                Fragment newFrag = new RiepilogoFragment();

                toolbar.setNavigationIcon(null);
                toolbar.setNavigationOnClickListener(null);

                BottomNavigationMenu.replaceFragment(newFrag);
                BottomNavigationMenu.setActiveFragment(newFrag);
            } else{
                super.onBackPressed();
            }
        } else if( BottomNavigationMenu.getActiveFragment() instanceof ProblemInformationFragment) {

            Fragment newFragment = new GroundsFragment();

            BottomNavigationMenu.replaceFragment(newFragment);
            BottomNavigationMenu.setActiveFragment(newFragment);

        } else{
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int itemId = item.getItemId();

        switch (itemId){
            case R.id.irrigatoriSettingsButton:
                Fragment irrigator = new SettingsIrrigator();
                BottomNavigationMenu.setActiveFragment(irrigator);
                BottomNavigationMenu.replaceFragment(irrigator, true);
                return true;
            case R.id.sensoriSettingsButton:
                BottomNavigationMenu.replaceFragment(new ImpostazioniSensori(),true);
                return true;
            case R.id.logoutSettingsButton:
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                launchLogin = new Intent(BasicActivity.getBasicActivity(), LoginActivity.class);
                                startActivity(launchLogin);

                                finish();
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };

                new MaterialAlertDialogBuilder(this)
                        .setTitle("Attenzione")
                        .setMessage("Sei sicuro di voler effettuare il logout da Farming4U?")
                        .setNegativeButton("No",dialogClickListener)
                        .setPositiveButton("Si",dialogClickListener)
                        .show();

                return true;
            default:
                super.onOptionsItemSelected(item);
        }

        return false;
    }

    public static BasicActivity getIstance() {
        return istance;
    }

    public static AppCompatActivity getBasicActivity(){
        return instanceThis;
    }


    private static BasicActivity istance;


    public static void setSelectedItem(String select){
        if(select.equals("semina")){
            bottomBar.setSelectedItemId(R.id.grounds);
        }
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Farming4U";
            String description = "Notifiche di Farming4U";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private String CHANNEL_ID="Channel_1";
    private Runnable runnable;
    private Handler handler;
    private boolean showToolbarMenu;
    static MaterialToolbar toolbar;
    static BottomNavigationView bottomBar;
    private BottomNavigationMenu bottomNavigationMenu;
    private Intent launchLogin;
    static AppCompatActivity instanceThis;
}
