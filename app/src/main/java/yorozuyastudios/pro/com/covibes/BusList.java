package yorozuyastudios.pro.com.covibes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class BusList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_list);

        Intent i=getIntent();
        ArrayList<Bus> list=(ArrayList<Bus>) i.getSerializableExtra("list");
        ListView lv=(ListView) findViewById(R.id.list_view);
        BusAdapter busAdapter=new BusAdapter(BusList.this,list);
        lv.setAdapter(busAdapter);

    }
}
