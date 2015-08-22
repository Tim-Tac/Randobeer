package be.timtac.perso.randobeer;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private List<String> beer = new ArrayList<>();
    private List<String> people = new ArrayList<>();
    private String tpers;

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
    }
}
