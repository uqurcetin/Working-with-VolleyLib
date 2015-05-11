package com.example.uur.newtest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity
{

    private static final String TAG = MainActivity.class.getSimpleName();

    private static String url = "http://dev.arkenus.com:5005/venues";
    private ProgressDialog pDialog ;
    private List<Venues> venuesList = new ArrayList<Venues>();
    private VenuesListAdapter adapter;
    private ListView listView;

    private static final String TAG_ID = "id";
    private static final String TAG_FK_ACcOUNT_ID = "fk_account_id";
    private static final String TAG_NAME = "name";
    private static final String TAG_ADDRESS = "address";
    private static final String TAG_CITY = "city";
    private static final String TAG_DISTRICT = "district";
    private static final String TAG_CREATED_AT = "created_at";
    private static final String TAG_UPDATED_AT = "updated_at";
    private static final String TAG_DELETED_AT = "deleted_at";
    private static final String TAG_ORDER = "order";
    private static final String TAG_LATITUDE = "latitude";
    private static final String TAG_COVER_IMAGE = "cover_image";
    private static final String TAG_CONTACTS = "results";



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.lvVenues);
        adapter = new VenuesListAdapter(this,venuesList);
        listView.setAdapter(adapter);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonObjectRequest venuesReq = new JsonObjectRequest
        (url, null, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response) {
                hidePDialog();
                try{
                    parseJSON(response);
                }catch(JSONException e) {
                    e.printStackTrace();
                }

                adapter.notifyDataSetChanged();
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                VolleyLog.d(TAG, "Error: " + volleyError.getMessage());
                hidePDialog();
            }
        });
        AppControl.getInstance().addToRequestQueue(venuesReq);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String  newid = (id == 0) ? "5" : "7";
                Intent intent = new Intent(getApplicationContext(),PlayListActivity.class);
                intent.putExtra("id",newid);
                startActivity(intent);
            }
        });
    }

   private void parseJSON(JSONObject json) throws JSONException {
       JSONArray items = json.getJSONArray(TAG_CONTACTS);
       for(int i  = 0; i<items.length();i++) {
           JSONObject obj = items.getJSONObject(i);
           Venues venue = new Venues();
           venue.setId(obj.getString(TAG_ID));
           venue.setFkAccountId(obj.getString(TAG_FK_ACcOUNT_ID));
           venue.setName(obj.getString(TAG_NAME));
           venue.setAddress(obj.getString(TAG_ADDRESS));
           venue.setCity(obj.getString(TAG_CITY));
           venue.setDistrict(obj.getString(TAG_DISTRICT));
           venue.setCreatedAt(obj.getString(TAG_CREATED_AT));
           venue.setUpdatedAt(obj.getString(TAG_UPDATED_AT));
           venue.setDeletedAt(obj.getString(TAG_DELETED_AT));
           venue.setOrder(obj.getString(TAG_ORDER));
           venue.setLatitude(obj.getString(TAG_LATITUDE));
           venue.setCoverImage(obj.getString(TAG_COVER_IMAGE));
           venuesList.add(venue);
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

}




