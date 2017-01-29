package yorozuyastudios.pro.com.covibes;

import android.app.Activity;
import android.app.ProgressDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class HAdapter extends ArrayAdapter<Hotel>{
    ProgressDialog mProgressDialog;
    ImageView iconView;

    public HAdapter(Activity context, ArrayList<Hotel> MemeList) {
        //Constructor to initialise the adapter with the context and List of memes
        super(context, 0, MemeList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_layout, parent, false);
        }


        Hotel currentAndroidFlavor = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.name);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView


        nameTextView.setText(currentAndroidFlavor.getName());
        TextView locTV=(TextView) listItemView.findViewById(R.id.location);
        locTV.setText(currentAndroidFlavor.getLocation());


        // Find the ImageView in the row_layout.xml layout with the ID meme_icon
         iconView = (ImageView) listItemView.findViewById(R.id.thumbnail);
        // Get the image resource ID from the current AndroidFlavor object and
        // set the image to iconView
        String url=currentAndroidFlavor.getImg_url();

        Glide.with(getContext()).load(url).into(iconView);


        return listItemView;
    }


}