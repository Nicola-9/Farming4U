package com.gruppodieci.farming4u.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.text.Editable;
import android.text.method.ScrollingMovementMethod;
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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.gruppodieci.farming4u.R;
import com.gruppodieci.farming4u.activity.BasicActivity;
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
    //private int quant;


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

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        // Inflate Fragment layout
        this.view = inflater.inflate(R.layout.informazioni_specifiche_colture_fragment, container, false);

        periodo_coltivazione = this.view.findViewById(R.id.periodo_coltivazione);

        quantita_coltivazione = this.view.findViewById(R.id.Quntità_da_coltivare);

        salva = this.view.findViewById(R.id.confirm);

        img = this.view.findViewById(R.id.icona_prodotto);

        textView = this.view.findViewById(R.id.descrzione);

        cancella = this.view.findViewById(R.id.cancel);

        freccia = this.view.findViewById(R.id.freccia);

        if(i == 1) {
            img.setBackgroundResource(R.drawable.icona_albero);
            textView.setText(consigli_gelso);
            textView.setMovementMethod(new ScrollingMovementMethod());
        }
        else if(i == 2) {
            img.setBackgroundResource(R.drawable.icona_ciliegia);
            textView.setText(consigli_ciliegia);
            textView.setMovementMethod(new ScrollingMovementMethod());
        }
        else if(i == 3) {
            img.setBackgroundResource(R.drawable.icona_mela);
            textView.setText(consigli_mela);
            textView.setMovementMethod(new ScrollingMovementMethod());
        }
        else if(i == 4) {
            img.setBackgroundResource(R.drawable.icona_patata);
            textView.setText(consigli_patata);
            textView.setMovementMethod(new ScrollingMovementMethod());
        }
        else if(i == 5) {
            img.setBackgroundResource(R.drawable.icona_kiwi);
            textView.setText(consigli_kiwi);
            textView.setMovementMethod(new ScrollingMovementMethod());
        }
        else if(i == 6) {
            img.setBackgroundResource(R.drawable.icona_piselli);
            textView.setText(consigli_piselli);
            textView.setMovementMethod(new ScrollingMovementMethod());
        }
        else if(i == 7) {
            img.setBackgroundResource(R.drawable.icona_uva);
            textView.setText(consigli_uva);
            textView.setMovementMethod(new ScrollingMovementMethod());
        }

        cancella.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
              Fragment fragment = new SeminaFragment();
              replaceFragment(R.id.mapContent, fragment);
            }
        });

        salva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Editable s = periodo_coltivazione.getText();
                Editable s2 = quantita_coltivazione.getText();

                periodo = s.toString();
               // quant = Integer.valueOf(s2.toString());
                createDialogSalvaColture(getContext());
            }
        });

        freccia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new SeminaFragment();
                replaceFragment(R.id.mapContent, fragment);
                BasicActivity.getIstance().getSupportActionBar().show();
            }
        });

        BasicActivity.getToolbar().setVisibility(View.GONE);
        GroundsFragment.getTab().setVisibility(View.GONE);
        view.invalidate();
        return this.view;
    }



    public void createDialogSalvaColture(final Context context){
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:

                        Editable s = periodo_coltivazione.getText();
                        Editable s2 = quantita_coltivazione.getText();

                        String periodo = s.toString();

                        Pattern pattern = Pattern.compile("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");

                        Matcher matcher = pattern.matcher(periodo);

                        Boolean resu = false;
                        Boolean resuN = isNumerico(s2.toString());

                        if (matcher.matches())
                            resu = true;
                        else
                            resu = false;

                        if(resu == true && resuN == true){
                            int quant = Integer.valueOf(s2.toString());
                            TerreniColtivati terreno = new TerreniColtivati(x_sel_inizio,  y_sel_inizio, x_sel_fine, y_sel_fine);

                            SavingFilesSeminaTerreni disco = new SavingFilesSeminaTerreni();

                            disco.clearOneTerreni(terreno, getContext());

                            arrayTerreni= disco.readFromFileTerreni(getContext());

                            arrayTerreni.add(new TerreniColtivati(x_sel_inizio,  y_sel_inizio, x_sel_fine, y_sel_fine, i, periodo, quant));

                            disco.saveTerreni(arrayTerreni, getContext());

                            System.out.println(arrayTerreni.toString());

                            Fragment fragment = new SeminaFragment();
                            replaceFragment(R.id.mapContent, fragment);

                            BasicActivity.getIstance().getSupportActionBar().show();

                        }

                        else if(resu == false && resuN == true){
                            Toast.makeText(getContext(), "Data non corretta" , Toast.LENGTH_SHORT).show();
                        }
                        else if(resu == true && resuN == false){
                            Toast.makeText(getContext(), "numero colture errato" , Toast.LENGTH_SHORT).show();
                        }
                        else if(resu == false && resuN == false){
                            Toast.makeText(getContext(), "Data e numero di coltivazioni errete" , Toast.LENGTH_SHORT).show();
                        }



                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };

        new MaterialAlertDialogBuilder(context)
                .setTitle("Attenzione")
                .setMessage("Stai per salvare una zona coltivata. Vuoi procedere?")
                .setNegativeButton("No",dialogClickListener)
                .setPositiveButton("Si",dialogClickListener)
                .show();
    }

    public static boolean isNumerico(String s) {

        boolean numerico = true;
        char[] sequenza = s.toCharArray();

        for (int i=0; i< sequenza.length; i++) {

            try {

                Integer.parseInt(Character.toString(sequenza[i]));

            } catch (Exception e) {

                numerico = false;

            }

        }

        return numerico;
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
