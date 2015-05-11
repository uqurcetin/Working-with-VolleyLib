package com.example.uur.newtest;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

import java.util.List;

/**
 * Created by Uğur on 16.2.2015.
 */
public class PlayListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<PlayListModel> playlistmodel;

    public PlayListAdapter(Activity activity, List<PlayListModel> playlistmodel) {
        this.activity = activity;
        this.playlistmodel = playlistmodel;
    }

    @Override
    public int getCount() {
        return playlistmodel.size();
    }

    @Override
    public Object getItem(int position) {
        return playlistmodel.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null)
            inflater  = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null)
            convertView = inflater.inflate(R.layout.playlist_item,null);

        TextView playlist_id = (TextView)convertView.findViewById(R.id.txtplaylistid);
        TextView playlist_title = (TextView)convertView.findViewById(R.id.txtplaylisttitle);
        TextView playlist_totalsongs = (TextView)convertView.findViewById(R.id.txtplaylisttotalsongs);
        TextView playlist_isavailable = (TextView)convertView.findViewById(R.id.txtplaylistisavailable);
        TextView playlist_createdat = (TextView)convertView.findViewById(R.id.txtplaylistcreatedat);
        TextView playlist_updatedat = (TextView)convertView.findViewById(R.id.txtplaylistupdatedat);
        TextView playlist_deletedat = (TextView) convertView.findViewById(R.id.txtplaylistdeletedat);

        PlayListModel plModel = playlistmodel.get(position);

        playlist_id.setText(plModel.getPlayList_Id());
        playlist_title.setText(plModel.getTitle());
        playlist_totalsongs.setText(plModel.getTotal_songs());
        playlist_isavailable.setText(plModel.getIs_available());
        playlist_createdat.setText(plModel.getCreated_at());
        playlist_updatedat.setText(plModel.getUpdated_at());
        playlist_deletedat.setText(plModel.getDeleted_at());
        return convertView;
    }
}
