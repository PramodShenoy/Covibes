package yorozuyastudios.pro.com.covibes;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class BusSearchActivity extends AppCompatActivity {

    EditText et1;
    EditText et2;
    EditText et3;
    Button b1;
    public static final String query="http://developer.goibibo.com/api/bus/search/?app_id=b2f41b4c&app_key=949f76f49eca84daa9990c3ae7e42efa&format=json&";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_search);
        et1=(EditText) findViewById(R.id.from);
        et2=(EditText) findViewById(R.id.to);
        et3=(EditText) findViewById(R.id.dep_date);
        b1=(Button) findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String from="source="+et1.getText().toString();
                String to="&destination="+et2.getText().toString();
                String dep="&dateofdeparture="+et3.getText().toString();
                String url=query.concat(from).concat(to).concat(dep);
                BusAsyncTask busAsyncTask = new BusAsyncTask();
                busAsyncTask.execute(url);
            }
        });

    }

    private class BusAsyncTask extends AsyncTask<String, Void, ArrayList<Bus>> {
        @Override
        protected ArrayList<Bus> doInBackground(String... urls) {

            if (urls.length < 1 || urls[0] == null) {
                return null;
            }
            String jsonresp = QueryUtils.getData(urls[0]);
            if (jsonresp != null) {
                return getJson(jsonresp);
            } else return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Bus> str) {
            if (str != null) {
                Intent i=new Intent(BusSearchActivity.this,BusList.class);
                i.putExtra("list",(Serializable)str);
                startActivity(i);
            }
        }
    }

    private static ArrayList<Bus> getJson(String jsonResp) {
        final ArrayList<Bus> contactActivityInfoList = new ArrayList<>();
        Bus obj=null;
        try {
            JSONObject jsonObject = new JSONObject(jsonResp);
            JSONObject data=jsonObject.getJSONObject("data");
            JSONArray onward=data.optJSONArray("onwardflights");

            for(int i=0;i<onward.length();i++)
            {
                JSONObject base=onward.getJSONObject(i);
                String rating=base.optString("rating");
                String dep=base.getString("DepartureTime");
                String duration=base.optString("duration");
                String type=base.optString("BusType");
                String name = base.optString("TravelsName");
                String arrival=base.optString("ArrivalTime");
                obj=new Bus(dep,type,arrival,name,rating,duration);
                contactActivityInfoList.add(obj);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return contactActivityInfoList;
    }

}
