package be.timtac.perso.randobeer;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private List<Element> array_beer = new ArrayList<>();
    private List<Element> array_people = new ArrayList<>();

    //UI declaration
    private Button go;
    private Button addSomeOne;
    private Button addBeverage;
    private ListView listBeverage;
    private ListView listSomeone;
    private TextView nobody;
    private TextView noBeverage;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //com

        go = (Button) findViewById(R.id.launch);
        addSomeOne = (Button)findViewById(R.id.someone);
        addBeverage = (Button)findViewById(R.id.beverage);
        listBeverage = (ListView)findViewById(R.id.list_beverage);
        listSomeone = (ListView)findViewById(R.id.list_someone);
        noBeverage = (TextView)findViewById(R.id.no_beverages);
        nobody = (TextView)findViewById(R.id.no_someone);


        addBeverage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
                View dialog_view = inflater.inflate(R.layout.dialog_element, null);

                final EditText new_element = (EditText)dialog_view.findViewById(R.id.new_element);

                builder.setView(dialog_view);

                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Element element = new Element();
                        element.element = new_element.getText().toString();
                        array_beer.add(element);
                        UpdateBeverage();
                    }
                });

                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //nothing to do
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                dialog.show();
            }
        });


        addSomeOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
                View dialog_view = inflater.inflate(R.layout.dialog_element, null);

                final EditText new_element = (EditText)dialog_view.findViewById(R.id.new_element);

                builder.setView(dialog_view);

                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Element element = new Element();
                        element.element = new_element.getText().toString();
                        array_people.add(element);
                        UpdatePeople();
                    }
                });

                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //nothing to do
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                dialog.show();


            }
        });


        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (array_beer.isEmpty() || array_people.isEmpty())
                    Toast.makeText(getApplicationContext(), "One or both array(s) are empty", Toast.LENGTH_LONG).show();
                else if (array_beer.size() > array_people.size())
                    Toast.makeText(getApplicationContext(), "Need to add more people", Toast.LENGTH_SHORT).show();
                else {
                    addSomeOne.setVisibility(View.GONE);
                    addBeverage.setVisibility(View.GONE);
                    go.setVisibility(View.GONE);
                }
            }
        });

    }


    public void UpdateBeverage()
    {
        if(!array_beer.isEmpty())
        {
            noBeverage.setVisibility(View.GONE);
            listBeverage.setVisibility(View.VISIBLE);
            ListViewAdapterBeverages adapter = new ListViewAdapterBeverages();
            listBeverage.setAdapter(adapter);
        }
        else
        {
            noBeverage.setVisibility(View.VISIBLE);
            listBeverage.setVisibility(View.GONE);
        }
    }


    public void UpdatePeople()
    {
        if(!array_people.isEmpty())
        {
            nobody.setVisibility(View.GONE);
            listSomeone.setVisibility(View.VISIBLE);
            ListViewAdapterPeople adapter = new ListViewAdapterPeople();
            listSomeone.setAdapter(adapter);
        }
        else
        {
            nobody.setVisibility(View.VISIBLE);
            listSomeone.setVisibility(View.GONE);
        }
    }


    public class ListViewAdapterBeverages extends BaseAdapter
    {

        @Override
        public int getCount() {
            return array_beer.size();
        }

        @Override
        public Object getItem(int position) {
            return array_beer.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.list_cell,parent,false);

            TextView element = (TextView)convertView.findViewById(R.id.element);
            ImageView delete = (ImageView)convertView.findViewById(R.id.delete);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(),String.valueOf(position),Toast.LENGTH_SHORT).show();
                    array_beer.remove(position);
                    UpdateBeverage();
                }
            });

            Element temp_element  = array_beer.get(position);

            element.setText(temp_element.element);

            return convertView;
        }
    }


    public class ListViewAdapterPeople extends BaseAdapter
    {

        @Override
        public int getCount() {
            return array_people.size();
        }

        @Override
        public Object getItem(int position) {
            return array_people.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.list_cell,parent,false);

            TextView element = (TextView)convertView.findViewById(R.id.element);
            ImageView delete = (ImageView)convertView.findViewById(R.id.delete);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(),String.valueOf(position),Toast.LENGTH_SHORT).show();
                    array_people.remove(position);
                    UpdatePeople();
                }
            });

            Element temp_element  = array_people.get(position);

            element.setText(temp_element.element);

            return convertView;
        }
    }


    public static class Element
    {
        String element;
    }

}
