package be.timtac.perso.randobeer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
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
    private List<Element> array_beer_randomized = new ArrayList<>();
    private List<Element> array_people_randomized = new ArrayList<>();

    //UI declaration
    private Button go;
    private Button addSomeOne;
    private Button addBeverage;
    private ListView listBeverage;
    private ListView listPeople;
    private TextView nobody;
    private TextView noBeverage;
    private Button back;
    private Button relaunch;
    private ListView listBeveragesRandomized;
    private ListView listPeopleRandomized;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        go = (Button) findViewById(R.id.launch);
        addSomeOne = (Button)findViewById(R.id.someone);
        addBeverage = (Button)findViewById(R.id.beverage);
        listBeverage = (ListView)findViewById(R.id.list_beverage);
        listPeople = (ListView)findViewById(R.id.list_people);
        noBeverage = (TextView)findViewById(R.id.no_beverages);
        nobody = (TextView)findViewById(R.id.no_someone);
        back = (Button)findViewById(R.id.back);
        relaunch = (Button)findViewById(R.id.relaunch);
        listBeveragesRandomized = (ListView)findViewById(R.id.list_beverages_randomized);
        listPeopleRandomized = (ListView)findViewById(R.id.list_people_randomized);


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
                {
                    Toast.makeText(getApplicationContext(), "One or both array(s) are empty", Toast.LENGTH_LONG).show();
                    return;
                }

                //hiding UI of first screen and show second
                addSomeOne.setVisibility(View.GONE);
                addBeverage.setVisibility(View.GONE);
                go.setVisibility(View.GONE);
                back.setVisibility(View.VISIBLE);
                relaunch.setVisibility(View.VISIBLE);
                listBeverage.setVisibility(View.GONE);
                listPeople.setVisibility(View.GONE);
                listBeveragesRandomized.setVisibility(View.VISIBLE);
                listPeopleRandomized.setVisibility(View.VISIBLE);

                MakeMatching();
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBeverage.setVisibility(View.VISIBLE);
                addSomeOne.setVisibility(View.VISIBLE);
                go.setVisibility(View.VISIBLE);
                back.setVisibility(View.GONE);
                relaunch.setVisibility(View.GONE);
                listBeveragesRandomized.setVisibility(View.GONE);
                listPeopleRandomized.setVisibility(View.GONE);
                listPeople.setVisibility(View.VISIBLE);
                listBeverage.setVisibility(View.VISIBLE);
            }
        });


        relaunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"soon",Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void MakeMatching()
    {
        array_beer_randomized.clear();
        array_people_randomized.clear();

        for (int i = 0 ; i < array_people.size() ; i++)
        {
            array_people_randomized.add(array_people.get(i));
        }

        for (int j = 0 ; j < array_beer.size() ; j++)
        {
            array_beer_randomized.add(array_beer.get(j));
        }


        ArrayAdapter<Element> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, array_beer_randomized);
        listBeveragesRandomized.setAdapter(arrayAdapter);
        ArrayAdapter<Element> arrayAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, array_people_randomized);
        listPeopleRandomized.setAdapter(arrayAdapter2);
        Toast.makeText(getApplicationContext(),array_beer_randomized.get(0).element,Toast.LENGTH_LONG).show();

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
            listPeople.setVisibility(View.VISIBLE);
            ListViewAdapterPeople adapter = new ListViewAdapterPeople();
            listPeople.setAdapter(adapter);
        }
        else
        {
            nobody.setVisibility(View.VISIBLE);
            listPeople.setVisibility(View.GONE);
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
