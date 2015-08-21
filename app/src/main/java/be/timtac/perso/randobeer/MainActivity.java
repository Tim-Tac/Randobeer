package be.timtac.perso.randobeer;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private List<String> beer = new ArrayList<String>();
    private List<String> people = new ArrayList<String>();
    private String tpers;

    //UI declaration
    private Button lancer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lancer = (Button) findViewById(R.id.launch);
    }
}
