package be.timtac.perso.randobeer;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private List<String> array_beer = new ArrayList<>();
    private List<String> array_people = new ArrayList<>();

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
                array_beer.add("beverage");
                UpdateBeverage();
            }
        });


        addSomeOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                array_people.add("someone");
                UpdatePeople();
            }
        });


        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(array_beer.isEmpty() || array_people.isEmpty()) Toast.makeText(getApplicationContext(), "One or both array(s) are empty", Toast.LENGTH_LONG).show();
                else if(array_beer.size() < array_people.size()) Toast.makeText(getApplicationContext(), "Need to add more people", Toast.LENGTH_SHORT).show();
                else
                {
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
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.list_cell,parent,false);

            TextView element = (TextView)convertView.findViewById(R.id.element);
            ImageView delete = (ImageView)convertView.findViewById(R.id.delete);

            String temp_element  = array_beer.get(position);

            element.setText(temp_element);

            return convertView;
        }
    }


}
