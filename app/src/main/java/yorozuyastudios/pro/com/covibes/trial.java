package yorozuyastudios.pro.com.covibes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class trial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trial);
        Intent i=getIntent();
        ArrayList<Hotel> list=(ArrayList<Hotel>) i.getSerializableExtra("list");
        ListView lv=(ListView) findViewById(R.id.list_view);
        HAdapter hotelsAdapter=new HAdapter(trial.this,list);
        lv.setAdapter(hotelsAdapter);

    }
}
