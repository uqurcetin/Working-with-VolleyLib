package com.example.uur.newtest;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import java.util.List;

/**
 * Created by Uğur on 11.2.2015.
 */
public class VenuesListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Venues> venuesitem;

    ImageLoader imageloader = AppControl.getInstance().getImageLoader();

    public VenuesListAdapter(Activity activity, List<Venues> venuesitem) {
        this.activity = activity;
        this.venuesitem = venuesitem;
    }

    @Override
    public int getCount() {
        return venuesitem.size();
    }

    @Override
    public Object getItem(int position) {
        return venuesitem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null)
            inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null)
            convertView = inflater.inflate(R.layout.list_item,null);

        if(imageloader == null)
            imageloader = AppControl.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView.findViewById(R.id.thumbnail);
        TextView id = (TextView) convertView.findViewById(R.id.txtid);
        TextView fkaccountid = (TextView)convertView.findViewById(R.id.txtfkaccountid);
        TextView createdat = (TextView)convertView.findViewById(R.id.txtcreatedat);
        TextView updatedat = (TextView)convertView.findViewById(R.id.txtupdatedat);
        TextView deletedat = (TextView)convertView.findViewById(R.id.txtdeletedat);
        TextView name = (TextView)convertView.findViewById(R.id.txtname);
        TextView adres = (TextView)convertView.findViewById(R.id.txtadres);
        TextView city = (TextView)convertView.findViewById(R.id.txtcity);
        TextView district = (TextView)convertView.findViewById(R.id.txtdistrict);
        TextView order = (TextView)convertView.findViewById(R.id.txtorder);
        TextView latitude = (TextView) convertView.findViewById(R.id.txtlatitude);


        Venues v = venuesitem.get(position);

        thumbNail.setImageUrl(v.getCoverImage(),imageloader);
        id.setText(v.getId());
        fkaccountid.setText(v.getFkAccountId());
        createdat.setText(v.getCreatedAt());
        deletedat.setText(v.getDeletedAt());
        updatedat.setText(v.getUpdatedAt());
        name.setText(v.getName());
        adres.setText(v.getAddress());
        city.setText(v.getCity());
        district.setText(v.getDistrict());
        order.setText(v.getOrder());
        latitude.setText(v.getLatitude());
        return  convertView;


    }
}
