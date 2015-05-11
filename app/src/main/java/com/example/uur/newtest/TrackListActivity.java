package com.example.uur.newtest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Uğur on 17.2.2015.
 */
public class TrackListActivity extends Activity {
    private static final String TAG = TrackListActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    private ArrayList<TrackModel> trackmodel = new ArrayList<TrackModel>();
    private TrackListAdapter trackadapter;
    private ListView listview;

    private static final String url = "http://dev.arkenus.com:5005/tracks?playlist_id=";
    private static final String TAG_RESULTS ="results";
    private static final String TAG_VOTEID =  "vote_id";
    private static final String TAG_PRIORITY = "priority";
    private static final String TAG_ID = "id";
    private static final String TAG_ARTISIMAGE = "artist_image";
    private static final String TAG_ARTISTNAME = "artist_name";
    private static final String TAG_NAME = "name";
    private static final String TAG_ALBUMNAME = "album_name";

    private static String TAG_CODE = "code";
    private static String TAG_ERRNO =  "errno";
    private static String TAG_SQLSTATE ="sqlState";
    private static String TAG_INDEX ="index";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracklist);

        Bundle extra = new Bundle();
        extra = getIntent().getExtras();
        String urlid = extra.getString("playlistid");
        if(urlid == null)
            Toast.makeText(TrackListActivity.this,"id boş geldi",Toast.LENGTH_SHORT).show();


        listview = (ListView)findViewById(R.id.lvTrackList);
        trackadapter = new TrackListAdapter(this,trackmodel);
        listview.setAdapter(trackadapter);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();


        JsonObjectRequest TrackListReq =  new JsonObjectRequest
                (Request.Method.GET,url+urlid,null,new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        hidePDialog();
                        try{
                            parseJSON(jsonObject);
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                        trackadapter.notifyDataSetChanged();
                    }
                },new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        VolleyLog.d(TAG, "Error : " + volleyError.getMessage());
                        hidePDialog();
                    }
                });
                AppControl.getInstance().addToRequestQueue(TrackListReq);
                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        View curr = parent.getChildAt((int) id);
                        TextView c = (TextView)curr.findViewById(R.id.txttrackvoteid);
                        String playerChanged = c.getText().toString();


            }
        });
    }



    private void parseJSON(JSONObject json) throws JSONException {
        JSONArray items = json.getJSONArray(TAG_RESULTS);
        for(int i = 0; i<items.length();i++) {
            JSONObject obj = items.getJSONObject(i);
            TrackModel model = new TrackModel();
            model.setVoteid(obj.getString(TAG_VOTEID));
            model.setAlbumname(obj.getString(TAG_ALBUMNAME));
            model.setArtistname(obj.getString(TAG_ARTISTNAME));
            model.setPriority(obj.getString(TAG_PRIORITY));
            model.setTrackid(obj.getString(TAG_ID));
            model.setTrackname(obj.getString(TAG_NAME));
            model.setArtistimage(obj.getString(TAG_ARTISIMAGE));
            trackmodel.add(model);

        }

    }
    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);

        SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView)menu.findItem(R.id.menu_search).getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);

        //TrackListActivity.this.trackadapter.getFilter().filter();
      //  SearchView.OnQueryTextListener textchangeLi
        return true;
    }
}
