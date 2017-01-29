package yorozuyastudios.pro.com.covibes;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Hotels extends AppCompatActivity {

    EditText et;
    Button b1;
    TextView tv;
    ListView lv;
    public static final String query = "http://developer.goibibo.com/api/voyager/get_hotels_by_cityid/?app_id=b2f41b4c&app_key=949f76f49eca84daa9990c3ae7e42efa&city_id=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);
        InputStream inputStream = getResources().openRawResource(R.raw.city_list);
        CSVReader csvFile = new CSVReader(inputStream);
        final ArrayList<City> scoreList = csvFile.read();
        et = (EditText) findViewById(R.id.search_hotel);
        b1 = (Button) findViewById(R.id.submit_btn);
        //tv=(TextView) findViewById(R.id.json_text);
        lv = (ListView) findViewById(R.id.list);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String srch = et.getText().toString();
                int flag = 0;
                for (int i = 0; i < scoreList.size(); i++) {
                    String x = scoreList.get(i).getName();
                    if (x.equalsIgnoreCase(srch)) {
                        Toast.makeText(Hotels.this, scoreList.get(i).getId(), Toast.LENGTH_LONG).show();
                        HotelsAsyncTask hotelsAsyncTask = new HotelsAsyncTask();
                        hotelsAsyncTask.execute(query.concat(scoreList.get(i).getId()));
                    }
                }
            }
        });
    }

    private class HotelsAsyncTask extends AsyncTask<String, Void, ArrayList<Hotel>> {
        @Override
        protected ArrayList<Hotel> doInBackground(String... urls) {

            if (urls.length < 1 || urls[0] == null) {
                return null;
            }
            String jsonresp = QueryUtils.getData(urls[0]);
            if (jsonresp != null) {
                return getJson(jsonresp);
            } else return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Hotel> str) {
            if (str != null) {
               // ArrayAdapter<String> adapter=new ArrayAdapter<String>(Hotels.this,android.R.layout.simple_list_item_1,str);
                //lv.setAdapter(adapter);
                Intent i=new Intent(Hotels.this,trial.class);
                i.putExtra("list",(Serializable)str);
                startActivity(i);
            }
        }
    }

    private static ArrayList<Hotel> getJson(String jsonResp) {
        final ArrayList<Hotel> contactActivityInfoList=new ArrayList<>();
        try {
            final JSONObject bodyObject = new JSONObject(jsonResp);
            final JSONObject activities = bodyObject.getJSONObject("data");
            final Iterator<String> keys = activities.keys(); // you can iterate through all keys

            while (keys.hasNext()) {
                final String key = keys.next();

                JSONObject ids=activities.getJSONObject(key);
                JSONObject node=ids.getJSONObject("hotel_geo_node");
                String name =node.optString("name");
                JSONObject node2=ids.getJSONObject("hotel_data_node");
                JSONObject images=node2.getJSONObject("img_selected");
                JSONObject thumb=images.getJSONObject("thumb");
                String img_url=thumb.optString("l");
                JSONObject loc=node2.getJSONObject("loc");
                String location=loc.optString("location");
                Hotel obj=new Hotel(name,location,img_url);
                contactActivityInfoList.add(obj);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return contactActivityInfoList;
    }
}



