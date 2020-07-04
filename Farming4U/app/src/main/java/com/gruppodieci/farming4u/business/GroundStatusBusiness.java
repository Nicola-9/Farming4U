package com.gruppodieci.farming4u.business;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.gruppodieci.farming4u.BottomNavigationMenu;
import com.gruppodieci.farming4u.MainActivity;
import com.gruppodieci.farming4u.R;
import com.gruppodieci.farming4u.fragments.GroundStatusFragment;
import com.gruppodieci.farming4u.fragments.SensorInformationFragment;

import java.util.Random;

public class GroundStatusBusiness {
    public GroundStatusBusiness(ImageButton leftSwitcher, ImageView sensorImage, ImageButton rightSwitcher, TextView sensorName,
                                TextView firstButtonHeader, MaterialButton firstSensorInformationButton, TextView secondButtonHeader,
                                MaterialButton secondSensorInformationButton, TextView thirdButtonHeader,
                                MaterialButton thirdSensorInformationButton, ImageView warningHum, ImageView warningTemp,
                                ImageView warningPh) {

        this.leftSwitcher = leftSwitcher;
        this.sensorImage = sensorImage;
        this.rightSwitcher = rightSwitcher;
        this.sensorName = sensorName;
        this.firstButtonHeader = firstButtonHeader;
        this.firstSensorInformationButton = firstSensorInformationButton;
        this.secondButtonHeader = secondButtonHeader;
        this.secondSensorInformationButton = secondSensorInformationButton;
        this.thirdButtonHeader = thirdButtonHeader;
        this.thirdSensorInformationButton = thirdSensorInformationButton;
        this.warningHum = warningHum;
        this.warningTemp = warningTemp;
        this.warningPh = warningPh;
    }

    public void setUtils(String currentSensor) {
        this.currentSensor = currentSensor;

        this.instance = instance;

        this.handler = new Handler();

        this.warning = new String[]{"blue", "yellow", "red"};

        this.humidityMessage = new String[]{"Nessun problema con l'umidità rilevata. Valori ottimi." +
                                            " Nessun azione consigliata.",
                                            "Umidità media rilevata. Si consiglia di tenere sotto controllo" +
                                            " il tutto in modo di non incorrere in possibili formazioni di muffe.",
                                            "Probabilità di formazione muffe superiore al 50 %." +
                                            " Si consiglia di intervenire immediatamente."
        };

        this.temperatureMessage = new String[]{"Nessun problema con la temperatura rilevata. Temperatura ottima" +
                                                " per un proficuo raccolto. Nessun azione consigliata.",
                                                "Temperatura media rilevata. Si consiglia di tenere sotto controllo" +
                                                " il tutto in modo di non incorrere in rischi maggiori.",
                                                "Rilevata un'eccessiva temperatura del terreno." +
                                                " Elevato rischio di danneggiamento dell'apparato radicale della coltura." +
                                                " Si consiglia di intervenire immediatamente."
        };

        this.phMessage = new String[]{"Nessun problema con l'acidità rilevata. " +
                                      "Nessun azione consigliata.",
                                      "Acidità media rilevata. Si consiglia di tenere sotto controllo" +
                                      " il tutto in modo da non compromettere la coltivazione.",
                                      "Probabilità di compromissiome coltivazione elevata." +
                                      " Si consiglia di intervenire immediatamente."
        };

        this.humidityValueNormal = new String[]{"35.2 %", "20.9 %", "27.5 %", "17.3%", "23.7 %", "30.0 %", "15.5 %"};
        this.humidityValueMedium = new String[]{"55.2 %", "45.9 %", "50.5 %", "57.3%", "47.7 %", "43.0 %", "51.5 %"};
        this.humidityValueHigh = new String[]{"70.2 %", "78.7 %", "80.5 %", "83.3%", "73.7 %", "79.0 %", "68.5 %"};

        this.temperatureValueNormal = new String[]{"23.2 °C", "20.9 °C", "19.5 °C", "17.3 °C", "18.7 °C", "25.0 °C", "24.5 °C"};
        this.temperatureValueMedium = new String[]{"25.9 °C", "27.9 °C", "26.5 °C", "25.5 °C", "27.7 °C", "25.3 °C", "26.2 °C"};
        this.temperatureValueHigh = new String[]{"31.2 %", "33.7 %", "35.5 %", "40.3%", "37.7 %", "38.0 %", "39.5 %"};

        this.phValueNormal = new String[]{"ph 7", "ph 6.7", "ph 6.8", "ph 6.5", "ph 7.1", "ph 7.2", "ph 6.7"};
        this.phValueMedium = new String[]{"ph 8", "ph 8.1", "ph 8.2", "ph 7.5", "ph 8.3", "ph 8.7", "ph 7.6"};
        this.phValueHigh = new String[]{"ph 9", "ph 9.1", "ph 9.2", "ph 9.5", "ph 10.3", "ph 11.7", "ph 12.6"};

        this.random = new Random();
    }

    public void setSensorImage() {
        if(this.currentSensor.equals("beacon")) {
            this.sensorImage.setImageResource(R.drawable.beacon_ble);

            String sensor = "Sensore Vigneto";

            this.sensorName.setText(sensor);
        } else {
            String sensor = "Sonda Oliveto";
            this.sensorName.setText(sensor);
            this.sensorImage.setImageResource(R.drawable.sensore_sonda);
        }
    }

    public void setSwitchSensorListener() {
        this.leftSwitcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentSensor.equals("beacon")){
                    currentSensor = "sonda";

                    sensorImage.setImageResource(R.drawable.sensore_sonda);

                    String newText = "Sonda Oliveto";
                    sensorName.setText(newText);

                    setValue();
                } else {
                    currentSensor = "beacon";
                    sensorImage.setImageResource(R.drawable.beacon_ble);

                    String newText = "Sensore Vigneto";
                    sensorName.setText(newText);

                    setValue();
                }
            }
        });

        this.rightSwitcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentSensor.equals("beacon")){
                    currentSensor = "sonda";
                    sensorImage.setImageResource(R.drawable.sensore_sonda);

                    String newText = "Sonda Oliveto";
                    sensorName.setText(newText);

                    setValue();
                } else {
                    currentSensor = "beacon";
                    sensorImage.setImageResource(R.drawable.beacon_ble);

                    String newText = "Sensore Vigneto";
                    sensorName.setText(newText);

                    setValue();
                }
            }
        });
    }

    public void setInformationButtonsListeners() {
        firstSensorInformationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String warningValue = null;

                switch (humidityMessagePriority){
                    case "normal":
                        warningValue = humidityMessage[0];
                        break;
                    case "medium":
                        warningValue = humidityMessage[1];
                        break;
                    case "high":
                        warningValue = humidityMessage[2];
                        break;
                }

                SensorInformationBusiness.setInformation(currentSensor, humidityMessagePriority, "humidity",
                                                        firstSensorInformationButton.getText().toString(), warningValue);

                Fragment information = new SensorInformationFragment();

                BottomNavigationMenu.replaceFragment(information);
                BottomNavigationMenu.setActiveFragment(information);
            }
        });

        secondSensorInformationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String warningValue = null;

                switch (temperatureMessagePriority){
                    case "normal":
                        warningValue = temperatureMessage[0];
                        break;
                    case "medium":
                        warningValue = temperatureMessage[1];
                        break;
                    case "high":
                        warningValue = temperatureMessage[2];
                        break;
                }

                SensorInformationBusiness.setInformation(currentSensor, temperatureMessagePriority, "temperature",
                        secondSensorInformationButton.getText().toString(), warningValue);

                Fragment information = new SensorInformationFragment();

                BottomNavigationMenu.replaceFragment(information);
                BottomNavigationMenu.setActiveFragment(information);
            }
        });

        thirdSensorInformationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String warningValue = null;

                switch (phMessagePriority){
                    case "normal":
                        warningValue = phMessage[0];
                        break;
                    case "medium":
                        warningValue = phMessage[1];
                        break;
                    case "high":
                        warningValue = phMessage[2];
                        break;
                }

                SensorInformationBusiness.setInformation(currentSensor, phMessagePriority, "ph",
                        thirdSensorInformationButton.getText().toString(), warningValue);

                Fragment information = new SensorInformationFragment();

                BottomNavigationMenu.replaceFragment(information);
                BottomNavigationMenu.setActiveFragment(information);
            }
        });
    }

    public void updateData(){
        Runnable refresh = new Runnable() {
            @Override
            public void run() {
                if(!(BottomNavigationMenu.getActiveFragment() instanceof GroundStatusFragment))
                    handler.removeCallbacks(this);
                else {
                    setValue();
                    handler.postDelayed(this, 3000);
                }
            }
        };

        this.handler.postDelayed(refresh, 3000);
    }

    public void setValue(){
        int humidityData = random.nextInt(3);
        int temperatureData = random.nextInt(3);
        int phData = random.nextInt(3);

        switch (humidityData){
            case 0:
                firstSensorInformationButton.setStrokeColor(ColorStateList.valueOf(Color.GREEN));

                float pixelsWidth =  23 * BottomNavigationMenu.getInstance().getResources().getDisplayMetrics().density;

                warningHum.setImageResource(R.drawable.ok_icon);

                warningHum.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
                warningHum.getLayoutParams().width = (int) pixelsWidth;

                warningHum.requestLayout();

                int humidityValue = random.nextInt(this.humidityValueNormal.length);

                String value = basicSpace + humidityValueNormal[humidityValue];
                firstSensorInformationButton.setText(value);

                humidityMessagePriority = "normal";
                break;
            case 1:
                firstSensorInformationButton.setStrokeColor(ColorStateList.valueOf(Color.rgb(255, 171, 0)));

                float pixelsWidthMedium =  17 * BottomNavigationMenu.getInstance().getResources().getDisplayMetrics().density;
                float pixelsHeightMedium =  52 * BottomNavigationMenu.getInstance().getResources().getDisplayMetrics().density;

                warningHum.setImageResource(R.drawable.esclamativo_giallo);

                warningHum.getLayoutParams().height = (int) pixelsHeightMedium;
                warningHum.getLayoutParams().width = (int) pixelsWidthMedium;

                warningHum.requestLayout();

                int humidityValueM = random.nextInt(this.humidityValueMedium.length);

                String valueM = basicSpace + humidityValueMedium[humidityValueM];
                firstSensorInformationButton.setText(valueM);

                humidityMessagePriority = "medium";
                break;

            case 2:
                firstSensorInformationButton.setStrokeColor(ColorStateList.valueOf(Color.RED));

                float pixelsWidthHigh =  17 * BottomNavigationMenu.getInstance().getResources().getDisplayMetrics().density;
                float pixelsHeightHigh =  52 * BottomNavigationMenu.getInstance().getResources().getDisplayMetrics().density;

                warningHum.setImageResource(R.drawable.esclamativo_rosso);

                warningHum.getLayoutParams().height = (int) pixelsHeightHigh;
                warningHum.getLayoutParams().width = (int) pixelsWidthHigh;

                warningHum.requestLayout();

                int humidityValueH = random.nextInt(this.humidityValueHigh.length);

                String valueH = basicSpace + humidityValueHigh[humidityValueH];
                firstSensorInformationButton.setText(valueH);

                humidityMessagePriority = "high";
                break;
        }

        switch (temperatureData){
            case 0:
                secondSensorInformationButton.setStrokeColor(ColorStateList.valueOf(Color.GREEN));

                float pixelsWidth =  23 * BottomNavigationMenu.getInstance().getResources().getDisplayMetrics().density;

                warningTemp.setImageResource(R.drawable.ok_icon);

                warningTemp.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
                warningTemp.getLayoutParams().width = (int) pixelsWidth;

                warningTemp.requestLayout();

                int tempValue = random.nextInt(this.temperatureValueNormal.length);

                String value = basicSpace + temperatureValueNormal[tempValue];
                secondSensorInformationButton.setText(value);

                temperatureMessagePriority = "normal";
                break;
            case 1:
                secondSensorInformationButton.setStrokeColor(ColorStateList.valueOf(Color.rgb(255, 171, 0)));

                float pixelsWidthMedium =  17 * BottomNavigationMenu.getInstance().getResources().getDisplayMetrics().density;
                float pixelsHeightMedium =  52 * BottomNavigationMenu.getInstance().getResources().getDisplayMetrics().density;

                warningTemp.setImageResource(R.drawable.esclamativo_giallo);

                warningTemp.getLayoutParams().height = (int) pixelsHeightMedium;
                warningTemp.getLayoutParams().width = (int) pixelsWidthMedium;

                warningTemp.requestLayout();

                int tempValueM = random.nextInt(this.temperatureValueMedium.length);

                String valueM = basicSpace + temperatureValueMedium[tempValueM];
                secondSensorInformationButton.setText(valueM);

                temperatureMessagePriority = "medium";
                break;

            case 2:
                secondSensorInformationButton.setStrokeColor(ColorStateList.valueOf(Color.RED));

                float pixelsWidthHigh =  17 * BottomNavigationMenu.getInstance().getResources().getDisplayMetrics().density;
                float pixelsHeightHigh =  52 * BottomNavigationMenu.getInstance().getResources().getDisplayMetrics().density;

                warningTemp.setImageResource(R.drawable.esclamativo_rosso);

                warningTemp.getLayoutParams().height = (int) pixelsHeightHigh;
                warningTemp.getLayoutParams().width = (int) pixelsWidthHigh;

                warningTemp.requestLayout();

                int tempValueH = random.nextInt(this.temperatureValueHigh.length);

                String valueH = basicSpace + temperatureValueHigh[tempValueH];
                secondSensorInformationButton.setText(valueH);

                temperatureMessagePriority = "high";
                break;
        }

        switch (phData){
            case 0:
                thirdSensorInformationButton.setStrokeColor(ColorStateList.valueOf(Color.GREEN));

                float pixelsWidth =  23 * BottomNavigationMenu.getInstance().getResources().getDisplayMetrics().density;

                warningPh.setImageResource(R.drawable.ok_icon);

                warningPh.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
                warningPh.getLayoutParams().width = (int) pixelsWidth;

                warningPh.requestLayout();

                int phValue = random.nextInt(this.phValueNormal.length);

                String value = basicSpace + phValueNormal[phValue];
                thirdSensorInformationButton.setText(value);

                phMessagePriority = "normal";
                break;
            case 1:
                thirdSensorInformationButton.setStrokeColor(ColorStateList.valueOf(Color.rgb(255, 171, 0)));

                float pixelsWidthMedium =  17 * BottomNavigationMenu.getInstance().getResources().getDisplayMetrics().density;
                float pixelsHeightMedium =  52 * BottomNavigationMenu.getInstance().getResources().getDisplayMetrics().density;

                warningPh.setImageResource(R.drawable.esclamativo_giallo);

                warningPh.getLayoutParams().height = (int) pixelsHeightMedium;
                warningPh.getLayoutParams().width = (int) pixelsWidthMedium;

                warningPh.requestLayout();

                int phValueM = random.nextInt(this.phValueMedium.length);

                String valueM = basicSpace + phValueMedium[phValueM];
                thirdSensorInformationButton.setText(valueM);

                phMessagePriority = "medium";
                break;

            case 2:
                thirdSensorInformationButton.setStrokeColor(ColorStateList.valueOf(Color.RED));

                float pixelsWidthHigh =  17 * BottomNavigationMenu.getInstance().getResources().getDisplayMetrics().density;
                float pixelsHeightHigh =  52 * BottomNavigationMenu.getInstance().getResources().getDisplayMetrics().density;

                warningPh.setImageResource(R.drawable.esclamativo_rosso);

                warningPh.getLayoutParams().height = (int) pixelsHeightHigh;
                warningPh.getLayoutParams().width = (int) pixelsWidthHigh;

                warningPh.requestLayout();

                int phValueH = random.nextInt(this.phValueHigh.length);

                String valueH = basicSpace + phValueHigh[phValueH];
                thirdSensorInformationButton.setText(valueH);

                phMessagePriority = "high";
                break;
        }
    }

    private String[] warning;
    private String[] humidityMessage;
    private String[] temperatureMessage;
    private String[] phMessage;
    private String[] humidityValueNormal;
    private String[] temperatureValueNormal;
    private String[] phValueNormal;
    private String[] humidityValueMedium;
    private String[] temperatureValueMedium;
    private String[] phValueMedium;
    private String[] humidityValueHigh;
    private String[] temperatureValueHigh;
    private String[] phValueHigh;
    private Random random;
    private Handler handler;
    private String currentSensor;
    private ImageButton leftSwitcher;
    private ImageView sensorImage;
    private ImageButton rightSwitcher;
    private TextView sensorName;
    private TextView firstButtonHeader;
    private MaterialButton firstSensorInformationButton;
    private TextView secondButtonHeader;
    private MaterialButton secondSensorInformationButton;
    private TextView thirdButtonHeader;
    private MaterialButton thirdSensorInformationButton;
    private ImageView warningHum;
    private ImageView warningTemp;
    private ImageView warningPh;
    private AppCompatActivity instance;
    private String basicSpace = "   ";
    private String humidityMessagePriority;
    private String temperatureMessagePriority;
    private String phMessagePriority;
}
