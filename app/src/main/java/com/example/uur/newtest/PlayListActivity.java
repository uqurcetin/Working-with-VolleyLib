package com.example.uur.newtest;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Uğur on 16.2.2015.
 */
public class PlayListActivity extends Activity {

    private ProgressDialog pDialog;
    private List<PlayListModel> playlist = new ArrayList<PlayListModel>();
    private PlayListAdapter PLadapter;
    private ListView listview;


    private static final String TAG_ID = "id";
    private static final String TAG_TITLE = "title";
    private static final String TAG_TOTALSONGS ="total_songs";
    private static final String TAG_ISAVAILABLE = "is_available";
    private static final String TAG_CREATEDAT = "created_at";
    private static final String TAG_UPDATEDAT = "updated_at";
    private static final String TAG_DELETEDAT ="deleted_at";
    private static final String TAG_RESULTS = "results";
    private static final String url = "http://dev.arkenus.com:5005/playlists?venue_id=";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        Bundle extra = new Bundle();
        extra = getIntent().getExtras();
        String newid = extra.getString("id");
        if(newid==null) {
            Toast.makeText(getApplicationContext(),"id boş geldi",Toast.LENGTH_SHORT).show();
        }


        listview = (ListView)findViewById(R.id.lvPlayList);
        PLadapter = new PlayListAdapter(this,playlist);
        listview.setAdapter(PLadapter);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
                JsonObjectRequest PlayListReq = new JsonObjectRequest
                (Request.Method.GET,url + newid, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        HideDialog();
                        try{
                            parseJSON(jsonObject);
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                        PLadapter.notifyDataSetChanged();
                    }
                },new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        HideDialog();
                        Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
                    }
                });
        AppControl.getInstance().addToRequestQueue(PlayListReq);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                View curr = parent.getChildAt((int) id);
                TextView c = (TextView)curr.findViewById(R.id.txtplaylistid);
                String playerChanged = c.getText().toString();

                Intent intent = new Intent(getApplicationContext(),TrackListActivity.class);
                intent.putExtra("playlistid",playerChanged);
                startActivity(intent);
            }
        });
    }
    private void parseJSON(JSONObject json) throws JSONException {
        JSONArray items =json.getJSONArray(TAG_RESULTS);
        for(int i = 0 ; i<items.length();i++) {
            JSONObject obj = items.getJSONObject(i);
            PlayListModel plmodel = new PlayListModel();
            plmodel.setPlayList_id(obj.getString(TAG_ID));
            plmodel.setTitle(obj.getString(TAG_TITLE));
            plmodel.setTotal_songs(obj.getString(TAG_TOTALSONGS));
            plmodel.setIs_available(obj.getString(TAG_ISAVAILABLE));
            plmodel.setCreated_at(obj.getString(TAG_CREATEDAT));
            plmodel.setUpdated_at(obj.getString(TAG_UPDATEDAT));
            plmodel.setDeleted_at(obj.getString(TAG_DELETEDAT));
            playlist.add(plmodel);
        }
    }
    private void HideDialog(){
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        HideDialog();
    }

}
