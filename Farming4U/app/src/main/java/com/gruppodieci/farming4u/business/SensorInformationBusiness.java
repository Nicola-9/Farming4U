package com.gruppodieci.farming4u.business;

import android.graphics.Color;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.gruppodieci.farming4u.R;

public class SensorInformationBusiness {
    public SensorInformationBusiness(TextView informationTitle, TextView informationPriority, FrameLayout framePriorityColor,
                                     ImageView informationParameterImage, ImageView informationWarningImage,
                                     TextView informationTextParameter, TextView informationDescriptionTitle, TextView informationText) {
        this.informationTitle = informationTitle;
        this.informationPriority = informationPriority;
        this.framePriorityColor = framePriorityColor;
        this.informationParameterImage = informationParameterImage;
        this.informationWarningImage = informationWarningImage;
        this.informationTextParameter = informationTextParameter;
        this.informationDescriptionTitle = informationDescriptionTitle;
        this.informationText = informationText;
    }

    public void setComponents(){
        this.setNameAndPriority();
        this.setImage();
        this.setTitleAndText();
    }

    public static void setInformation(String sensorNameP, String priorityP, String buttonPressedP, String valueP, String messageP){
        sensorName = sensorNameP;
        priority = priorityP;
        buttonPressed = buttonPressedP;
        value = valueP;
        message = messageP;
    }

    private void setNameAndPriority(){
        switch(sensorName){
            case "beacon":
                String title = "Sensore\nVigneto";

                this.informationTitle.setText(title);
                break;
            case "sonda":
                String titleS = "Sonda\nOliveto";

                this.informationTitle.setText(titleS);
                break;
        }

        switch (priority){
            case "normal":
                String priorityN = "Priorità: ---";

                this.informationPriority.setText(priorityN);
                this.framePriorityColor.setBackgroundColor(Color.rgb(186, 247, 255));
                this.informationWarningImage.setImageResource(R.drawable.ok_icon);
                break;
            case "medium":
                String priorityM = "Priorità: Media";

                this.informationPriority.setText(priorityM);
                this.framePriorityColor.setBackgroundColor(Color.rgb(239, 243, 119));
                this.informationWarningImage.setImageResource(R.drawable.esclamativo_giallo);
                break;
            case "high":
                String priorityH = "Priorità: Alta";

                this.informationPriority.setText(priorityH);
                this.framePriorityColor.setBackgroundColor(Color.rgb(246, 101, 101));
                this.informationWarningImage.setImageResource(R.drawable.esclamativo_rosso);
                break;
        }
    }

    private void setImage(){
        switch (buttonPressed){
            case "humidity":
                this.informationParameterImage.setImageResource(R.drawable.humidity_small);

                if(priority.equals("normal")){
                    String tit = "Umidità";
                    this.informationDescriptionTitle.setText(tit);
                } else
                    if (priority.equals("medium")){
                        String tit = "Umidità media";
                        this.informationDescriptionTitle.setText(tit);
                    } else{
                        String tit = "Umidità elevata";
                        this.informationDescriptionTitle.setText(tit);
                    }

                break;
            case "temperature":
                this.informationParameterImage.setImageResource(R.drawable.temperatura_small);

                if(priority.equals("normal")){
                    String tit = "Temperatura";
                    this.informationDescriptionTitle.setText(tit);
                } else
                    if (priority.equals("medium")){
                        String tit = "Temperatura media";
                        this.informationDescriptionTitle.setText(tit);
                    } else{
                        String tit = "Temperatura elevata";
                        this.informationDescriptionTitle.setText(tit);
                    }

                break;
            case "ph":
                this.informationParameterImage.setImageResource(R.drawable.ph);

                if(priority.equals("normal")){
                    String tit = "Acidità suolo";
                    this.informationDescriptionTitle.setText(tit);
                } else
                    if (priority.equals("medium")){
                        String tit = "Acidità suolo media";
                        this.informationDescriptionTitle.setText(tit);
                    } else{
                        String tit = "Acidità suolo elevata";
                        this.informationDescriptionTitle.setText(tit);
                    }

                break;
        }
    }

    private void setTitleAndText(){
        this.informationTextParameter.setText(value);
        this.informationText.setText(message);
    }

    private TextView informationTitle;
    private TextView informationPriority;
    private FrameLayout framePriorityColor;
    private ImageView informationParameterImage;
    private ImageView informationWarningImage;
    private TextView informationTextParameter;
    private TextView informationDescriptionTitle;
    private TextView informationText;
    static String sensorName;
    static String priority;
    static String buttonPressed;
    static String value;
    static String message;
}
