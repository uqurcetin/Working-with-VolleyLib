package com.example.uur.newtest;

/**
 * Created by Uğur on 16.2.2015.
 */
public class PlayListModel {
    public String PlayList_id;
    public String title;
    public String total_songs;
    public String is_available;
    public String created_at;
    public String updated_at;
    public String deleted_at;

    public PlayListModel() {

    }

    public PlayListModel(String PlayList_id, String title, String total_songs, String is_available, String created_at, String updated_at, String deleted_at) {
        this.PlayList_id = PlayList_id;
        this.title = title;
        this.total_songs = total_songs;
        this.is_available = is_available;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.deleted_at = deleted_at;
    }

    public String getPlayList_Id() {
        return PlayList_id;
    }

    public void setPlayList_id(String PlayList_id) {
        this.PlayList_id = PlayList_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTotal_songs() {
        return total_songs;
    }

    public void setTotal_songs(String total_songs) {
        this.total_songs = total_songs;
    }

    public String getIs_available() {
        return is_available;
    }

    public void setIs_available(String is_available) {
        this.is_available = is_available;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(String deleted_at) {
        this.deleted_at = deleted_at;
    }
}
