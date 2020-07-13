package com.gruppodieci.farming4u.fragments;

import android.graphics.Point;
import android.os.Bundle;
import android.text.Editable;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.gruppodieci.farming4u.R;
import com.gruppodieci.farming4u.business.SavingFilesSeminaTerreni;
import com.gruppodieci.farming4u.business.TerreniColtivati;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static com.gruppodieci.farming4u.BottomNavigationMenu.replaceFragment;

public class InformazioniSpecificheColtureFragment extends Fragment {

    private View view;
    private View view2;
    private float x_sel_inizio;
    private float y_sel_inizio;
    private float x_sel_fine;
    private float y_sel_fine;
    private int i;
    private ImageButton img;
    private ImageButton freccia;
    private TextInputEditText periodo_coltivazione;
    private TextInputEditText quantita_coltivazione;
    private Button salva;
    private Button salva2;
    private Button cancella;
    private ArrayList<TerreniColtivati> arrayTerreni = new ArrayList<TerreniColtivati>();
    private TextView textView;
    private String periodo;
    private int quant;


    public InformazioniSpecificheColtureFragment(){

    }

    public InformazioniSpecificheColtureFragment(int n, float x_inizio, float x_fine, float y_inizio, float y_fine){

        x_sel_inizio = x_inizio;
        x_sel_fine = x_fine;
        y_sel_inizio = y_inizio;
        y_sel_fine = y_fine;
        i = n;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate Fragment layout
        this.view = inflater.inflate(R.layout.informazioni_specifiche_colture_fragment, container, false);

        periodo_coltivazione = this.view.findViewById(R.id.periodo_coltivazione);

        quantita_coltivazione = this.view.findViewById(R.id.Quntità_da_coltivare);

        salva = this.view.findViewById(R.id.confirm);

        img = this.view.findViewById(R.id.icona_prodotto);

        textView = this.view.findViewById(R.id.descrzione);

        cancella = this.view.findViewById(R.id.cancel);

        freccia = this.view.findViewById(R.id.freccia);

       /* freccia.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Fragment fragment = new SeminaFragment();
                replaceFragment(R.id.mapContent,fragment);
            }
        });*/


        if(i == 1) {
            img.setBackgroundResource(R.drawable.icona_albero);
            textView.setText(consigli_gelso);
        }
        else if(i == 2) {
            img.setBackgroundResource(R.drawable.icona_ciliegia);
            textView.setText(consigli_ciliegia);
        }
        else if(i == 3) {
            img.setBackgroundResource(R.drawable.icona_mela);
            textView.setText(consigli_mela);
        }
        else if(i == 4) {
            img.setBackgroundResource(R.drawable.icona_patata);
            textView.setText(consigli_patata);
        }
        else if(i == 5) {
            img.setBackgroundResource(R.drawable.icona_kiwi);
            textView.setText(consigli_kiwi);
        }
        else if(i == 6) {
            img.setBackgroundResource(R.drawable.icona_piselli);
            textView.setText(consigli_piselli);
        }
        else if(i == 7) {
            img.setBackgroundResource(R.drawable.icona_uva);
            textView.setText(consigli_uva);
        }

        cancella.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Fragment fragment = new SeminaFragment();
                replaceFragment(R.id.mapContent,fragment);
            }
        });

        salva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Editable s = periodo_coltivazione.getText();
                Editable s2 = quantita_coltivazione.getText();

                periodo = s.toString();
                quant = Integer.valueOf(s2.toString());
                onButtonShowPopupWindowClick(view);
            }
        });

        return this.view;
    }




    public void onButtonShowPopupWindowClick(View view) {
        LayoutInflater inflater = (LayoutInflater)  getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_salvacoltura, null);

        Button salvaPopup = popupView.findViewById(R.id.salvas);
        Button annullaPopup = popupView.findViewById(R.id.cancels);



        WindowManager wm = (WindowManager) getContext().getSystemService(getContext().WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = (int) (size.x * 0.8f);
        int height = size.y/3;

        //int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        //int height = LinearLayout.LayoutParams.WRAP_CONTENT;


        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });

        salvaPopup.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {

                Editable s = periodo_coltivazione.getText();
                Editable s2 = quantita_coltivazione.getText();

                String periodo = s.toString();
                int quant = Integer.valueOf(s2.toString());

                Pattern pattern = Pattern.compile("((0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[-/.](19|20)\\d\\d)");//((0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[-/.](19|20)\\d\\d)")
                Matcher matcher = pattern.matcher(periodo);

                Boolean resu = false;
                if (matcher.matches())
                    resu = true;
                else
                    resu = false;

                if(resu == true){
                    TerreniColtivati terreno = new TerreniColtivati(x_sel_inizio,  y_sel_inizio, x_sel_fine, y_sel_fine);

                    SavingFilesSeminaTerreni disco = new SavingFilesSeminaTerreni();

                    disco.clearOneTerreni(terreno, getContext());

                    arrayTerreni= disco.readFromFileTerreni(getContext());

                    arrayTerreni.add(new TerreniColtivati(x_sel_inizio,  y_sel_inizio, x_sel_fine, y_sel_fine, i, periodo, quant));

                    disco.saveTerreni(arrayTerreni, getContext());

                    System.out.println(arrayTerreni.toString());

                    //restart fragment
                    popupWindow.dismiss();
                    Fragment fragment = new SeminaFragment();
                    replaceFragment(R.id.mapContent,fragment);
                }

                else{
                    Toast.makeText(getContext(), "Data non corretta" , Toast.LENGTH_SHORT).show();
                }



            }
        });

        annullaPopup.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

    }


    private String consigli_patata = "La patata è un tubero commestibile ottenuto dalle piante della specie Solanum tuberosum, molto utilizzato a scopo alimentare previa cottura. Originaria delle Ande, la patata fu domesticata nella regione del lago Titicaca e divenne uno degli alimenti principali degli Inca, che ne svilupparono un gran numero di varietà per adattarla ai diversi ambienti delle regioni da loro abitate.";
    private String consigli_kiwi = "Il kiwi o kivi è una bacca commestibile, prodotta da numerose specie di liane del genere Actinidia, famiglia delle Actinidiaceae. Le due principali varietà di questa bacca sono: la verde e la gialla (o gold). La prima, la più diffusa, ha la buccia marrone scuro con pelucchi e la polpa verde brillante, semi piccoli e neri disposti a raggiera intorno al centro della bacca, la forma è simile a un uovo o a una piccola patata. La varietà gold ha forma più allungata, la polpa è gialla e non ha pelucchi sulla buccia. Esistono altre varietà, ma sono poco diffuse, come ad esempio il kiwi con la polpa rossa e la buccia color mattone.";
    private String consigli_ciliegia = "La ciliegia è il frutto del ciliegio (Prunus avium). La pianta domesticata è stata ottenuta da ripetute ibridazioni della specie botanica.\n" +
            "\n" +
            "Il nome italiano di ciliegia (conosciuto in toscano come ciriègia) deriva direttamente dal latino volgare ceresia[1] che, dalla sua forma cerasia è presente in diverse lingue romanze e non come portoghese (cereja), francese (cerise), spagnolo (cereza), rumeno (cireş), sardo (cerexa o ceriasa/cariasa/cherèsia), romano (cerasa), lombardo (scirés), siciliano (cirasa), veneto (saresa) e inglese (cherry). Il termine italiano alternativo cerasa è presente in diversi dialetti, ed è una forma più comune in Italia Centrale e Meridionale.";
    private String consigli_uva = "Big Perlon Uva a bacca nera senza semi o con semi erbacei-legnosi a sapore neutro a maturazione precoce o medio-precoce (coperta fine Giugno e senza copertura a fine a Luglio), con albero a vigore medio-elevato,portamento assurgente ed elevata produttività . ";
    private String consigli_mela = "La mela, dal latino malum, è il frutto del melo. Il melo ha origine in Asia centrale (attuale Kazakistan) e l'evoluzione dei meli botanici risalirebbe al Neolitico. ";
    private String consigli_piselli = "Il pisello (Pisum sativum L., 1753) è una pianta erbacea annuale appartenente alla famiglia Fabaceae, originaria dell'area mediterranea e orientale.\n" +
            "\n" +
            "La pianta è coltivata per i suoi semi, consumata come alimento o utilizzata come alimento per il bestiame. Il termine designa anche il seme della pianta, ricco di amidi e proteine (dal 16 al 40%);";
    private String consigli_gelso = "Morus L. è un genere di piante della famiglia delle Moracee, originario dell'Asia, ma anche diffuso, allo stato naturale, in Africa e in Nord America. Comprende alberi o arbusti da frutto di taglia media, comunemente chiamati gelsi.";

}
