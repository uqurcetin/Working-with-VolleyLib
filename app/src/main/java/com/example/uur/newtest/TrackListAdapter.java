package com.example.uur.newtest;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Uğur on 17.2.2015.
 */
public class TrackListAdapter extends BaseAdapter{
    private Activity activity;
    LayoutInflater inflater;
    private ArrayList<TrackModel> TrackModel;
    ArrayList<TrackModel> temporaryList = new ArrayList<TrackModel>();

    ImageLoader imageloader = AppControl.getInstance().getImageLoader();

    public TrackListAdapter(Activity activity, ArrayList<TrackModel> TrackModel){
        super();
        this.activity=activity;
        this.TrackModel = new ArrayList<TrackModel>();
        this.temporaryList = TrackModel;
        this.temporaryList.addAll(temporaryList);

    }

    @Override
    public int getCount() {
        return TrackModel.size();
    }

    @Override
    public Object getItem(int position) {
        return TrackModel.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        Log.v("convertView",String.valueOf(position));

        if(inflater == null)
            inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView==null) {
            convertView = inflater.inflate(R.layout.tracklist_item, null);

            if (imageloader == null)
                imageloader = AppControl.getInstance().getImageLoader();
            holder = new ViewHolder();
            holder.trackartistimage = (NetworkImageView) convertView.findViewById(R.id.trackimage);
            holder.trackvoteid = (TextView) convertView.findViewById(R.id.txttrackvoteid);
            holder.trackpriority = (TextView) convertView.findViewById(R.id.txttrackpriority);
            holder.trackid = (TextView) convertView.findViewById(R.id.txttrackid);
            holder.trackartistname = (TextView) convertView.findViewById(R.id.txtartistname);
            holder.trackname = (TextView) convertView.findViewById(R.id.txttrackname);
            holder.trackalbumname = (TextView) convertView.findViewById(R.id.txtalbumname);
            holder.tracklike = (ImageButton) convertView.findViewById(R.id.btnlike);
            holder.trackunlike = (ImageButton) convertView.findViewById(R.id.btnunlike);
            convertView.setTag(holder);

            holder.tracklike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShowRatingDialog();

                }
            });

            holder.trackunlike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShowRatingDialogUnlike();
                }
            });


        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        TrackModel t = TrackModel.get(position);
        holder.trackartistimage.setImageUrl(t.getArtistimage(),imageloader);
        holder.trackvoteid.setText(t.getVoteid());
        holder.trackpriority.setText(t.getPriority());
        holder.trackid.setText(t.getTrackid());
        holder.trackartistname.setText(t.getArtistname());
        holder.trackname.setText(t.getTrackname());
        holder.trackalbumname.setText(t.getAlbumname());

        return convertView;
    }

    private void ShowRatingDialogUnlike() {
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.dialog_vote);

        RatingBar RTBUnlike = (RatingBar)dialog.findViewById(R.id.RBVote);
        final TextView txtUUID = (TextView)dialog.findViewById(R.id.txtUUID);

        RTBUnlike.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                switch ((int)rating) {
                    case 1:dialog.setTitle("Başarısız Şarkı"); txtUUID.setText(""); break;
                    case 2:dialog.setTitle("Başarısıza yakın bir şarkı"); txtUUID.setText("");break;
                    case 3:dialog.setTitle("Başarısız ama dinlerim"); txtUUID.setText(""); break;
                    case 4:dialog.setTitle("Başarılı değil başarısız da değil"); txtUUID.setText(""); break;
                    case 5:dialog.setTitle("Başarılı olabilir"); txtUUID.setText("");break;
                    default :txtUUID.setText("Oy başarısız"); dialog.setTitle("Seçiminiz...");break;
                }
            }
        });
        dialog.show();
        final Button btnunlike=(Button)dialog.findViewById(R.id.btnvote);
        btnunlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity,"UnLike içindeyiz",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }


    private void ShowRatingDialog() {
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.dialog_vote);

        RatingBar RTB = (RatingBar)dialog.findViewById(R.id.RBVote);
        final TextView txtUUID = (TextView)dialog.findViewById(R.id.txtUUID);

        RTB.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                switch ((int) rating) {

                    case 1 :dialog.setTitle("Ayda bir kez dinlenenler");txtUUID.setText(""); break;
                    case 2 :dialog.setTitle("Ayda iki kez dinlenenler");txtUUID.setText("");break;
                    case 3 :dialog.setTitle("Haftada bir kez dinlenenler");txtUUID.setText("");break;
                    case 4 :dialog.setTitle("İki Günde bir kez dinlenenler");txtUUID.setText(""); break;
                    case 5 :dialog.setTitle("Hergün dinlenenler"); txtUUID.setText(""); break;
                    default :txtUUID.setText("Oy başarısız"); dialog.setTitle("Seçiminiz...");break;
                }
            }
        });

        dialog.show();
       final Button btvote = (Button)dialog.findViewById(R.id.btnvote);
        btvote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "Tıklandı", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }

    private  class ViewHolder {
        TextView trackvoteid;
        TextView trackpriority;
        TextView trackid;
        TextView trackartistname;
        TextView trackname;
        TextView trackalbumname;
        ImageButton tracklike;
        ImageButton trackunlike;
        NetworkImageView trackartistimage;
    }




    private class NameFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            constraint = constraint.toString().toLowerCase();
            FilterResults result = new FilterResults();
            if(constraint != null && constraint.toString().length()>0){
                ArrayList<TrackModel> filteredItems = new ArrayList<TrackModel>();

                for(int i = 0,l=TrackModel.size();i<l;i++)
                {
                    com.example.uur.newtest.TrackModel nameList = TrackModel.get(i);
                    if(nameList.toString().toLowerCase().contains(constraint))
                        filteredItems.add(nameList);
                }
                result.count = filteredItems.size();
                result.values = filteredItems;
            }else {
                synchronized (this) {
                    result.values = TrackModel;
                    result.count = TrackModel.size();
                }
            }
            return result;
        }
        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            temporaryList = (ArrayList<TrackModel>)results.values;

            notifyDataSetChanged();
            for(int i=0,l = temporaryList.size();i<l;i++)
                TrackModel.add(temporaryList.get(i));
            notifyDataSetChanged();
        }
    }
}
