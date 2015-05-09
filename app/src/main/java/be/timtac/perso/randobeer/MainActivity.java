package be.timtac.perso.randobeer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private List<String> bieres = new ArrayList<String>();
    private List<String> personnes = new ArrayList<String>();
    private String tpers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button lancer = (Button)findViewById(R.id.lancer);
        final Button beer = (Button)findViewById(R.id.beer);
        final Button person = (Button)findViewById(R.id.person);
        final LinearLayout monlayout= (LinearLayout)findViewById(R.id.layout);


        TextView texte[] = new TextView[bieres.size()];
        int i=0;
        for(i=0;i<bieres.size();i++)
        {
            texte[i] = new TextView(this);
            texte[i].setTextSize(14);
            texte[i].setText("ho");

            RelativeLayout.LayoutParams textViewLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            textViewLayoutParams.setMargins(5, 30, 0, 0);
            texte[i].setLayoutParams(textViewLayoutParams);
            texte[i].setGravity(Gravity.RIGHT);
            monlayout.addView(texte[i]);
           Toast.makeText(getApplicationContext(),"passé",Toast.LENGTH_SHORT).show();
        }


    /**********************************************************************************************/

    lancer.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
           if((bieres.isEmpty()) || (personnes.isEmpty()))
           {
               Toast.makeText(getApplicationContext(),"Au moins une liste vide",Toast.LENGTH_SHORT).show();
           }
           else
           {
               Intent intent = new Intent(MainActivity.this, Resultat.class);
               startActivity(intent);
           }
        }
    });

    /**********************************************************************************************/
      beer.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
              builder.setTitle("Ajout de bière");

              final EditText input = new EditText(MainActivity.this);
              input.setHint("entrez votre bière");
              input.setPadding(20,60,0,20);
              input.setTextSize(20);
              builder.setView(input);


              builder.setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                      String tbiere = input.getText().toString();
                  }
              });

              builder.setNegativeButton("Annuler",new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                  }
              });

            AlertDialog dialog = builder.create();
            dialog.show();

            bieres.add("tbiere");
          }
      });

    /**********************************************************************************************/
    person.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Ajout de personne");

            final EditText input = new EditText(MainActivity.this);
            input.setHint("entrez un nom");
            input.setPadding(20,60,0,20);
            input.setTextSize(20);
            builder.setView(input);


            builder.setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    tpers = input.getText().toString();
                }
            });

            builder.setNegativeButton("Annuler",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();

            personnes.add(tpers);

        }
    });

    }
}
