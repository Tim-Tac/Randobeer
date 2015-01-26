package be.timtac.perso.randobeer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {
    private String[] beers = {};
    private String[] persons = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button lancer = (Button)findViewById(R.id.lancer);
        final Button beer = (Button)findViewById(R.id.beer);
        final Button person = (Button)findViewById(R.id.person);


    /**********************************************************************************************/

    lancer.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
           if((beers.length == 0) || (persons.length == 0))
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
              input.setPadding(15,60,0,15);
              input.setTextSize(20);
              builder.setView(input);


              builder.setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                      String test = input.getText().toString();
                      Toast.makeText(getApplicationContext(),test,Toast.LENGTH_SHORT).show();
                  }
              });

              builder.setNegativeButton("Annuler",new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                      Toast.makeText(getApplicationContext(),"b",Toast.LENGTH_SHORT).show();
                  }
              });

            AlertDialog dialog = builder.create();
              dialog.show();



          }
      });

    /**********************************************************************************************/
    person.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        }
    });



    }
}
