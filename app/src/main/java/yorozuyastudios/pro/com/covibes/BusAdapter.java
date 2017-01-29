package yorozuyastudios.pro.com.covibes;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.InputStream;
import java.util.ArrayList;

public class BusAdapter extends ArrayAdapter<Bus>{

    public BusAdapter(Activity context, ArrayList<Bus> MemeList) {
        //Constructor to initialise the adapter with the context and List of memes
        super(context, 0, MemeList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.bus_item_layout, parent, false);
        }


        Bus currentAndroidFlavor = getItem(position);
        TextView tv1=(TextView) listItemView.findViewById(R.id.operator_name);
        TextView tv2=(TextView) listItemView.findViewById(R.id.bus_type);
        TextView tv3=(TextView) listItemView.findViewById(R.id.rating);
        TextView tv4=(TextView) listItemView.findViewById(R.id.departure);
        TextView tv5=(TextView) listItemView.findViewById(R.id.arrival);
        TextView tv6=(TextView) listItemView.findViewById(R.id.duration);

        tv1.setText(currentAndroidFlavor.getOperator());
        tv2.setText(currentAndroidFlavor.getType());
        tv3.setText(currentAndroidFlavor.getRating());
        tv4.setText(currentAndroidFlavor.getDep());
        tv5.setText(currentAndroidFlavor.getArr());
        tv6.setText(currentAndroidFlavor.getDuration());


        return listItemView;
    }


}