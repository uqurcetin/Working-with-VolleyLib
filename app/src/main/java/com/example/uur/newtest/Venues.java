package com.example.uur.newtest;

/**
 * Created by Uğur on 11.2.2015.
 */
public class Venues {
    public String Id;
    public String FkAccountId;
    public String Name = null;
    public String Address = null;
    public String City = null;
    public String District = null;
    public String CreatedAt = null;
    public String UpdatedAt = null;
    public String DeletedAt = null;
    public String Order = null ;
    public String Latitude = null;
    public String CoverImage = null;

    public Venues() {

    }
    public Venues(String id, String fkAccountId, String name, String address,
                  String city, String district, String createdAt, String updatedAt,
                  String deletedAt, String order, String latitude, String coverImage)
    {
        Id = id;
        FkAccountId = fkAccountId;
        Name = name;
        Address = address;
        City = city;
        District = district;
        CreatedAt = createdAt;
        UpdatedAt = updatedAt;
        DeletedAt = deletedAt;
        Order = order;
        Latitude = latitude;
        CoverImage = coverImage;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setFkAccountId(String fkAccountId) {
        FkAccountId = fkAccountId;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setCity(String city) {
        City = city;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public void setCreatedAt(String createdAt) {
        CreatedAt = createdAt;
    }

    public void setUpdatedAt(String updatedAt) {
        UpdatedAt = updatedAt;
    }

    public void setDeletedAt(String deletedAt) {
        DeletedAt = deletedAt;
    }

    public void setOrder(String order) {
        Order = order;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public void setCoverImage(String coverImage) {
        CoverImage = coverImage;
    }

    public String getId() {
        return Id;
    }

    public String getFkAccountId() {
        return FkAccountId;
    }

    public String getName() {
        return Name;
    }

    public String getAddress() {
        return Address;
    }

    public String getCity() {
        return City;
    }

    public String getDistrict() {
        return District;
    }

    public String getCreatedAt() {
        return CreatedAt;
    }

    public String getUpdatedAt() {
        return UpdatedAt;
    }

    public String getDeletedAt() {
        return DeletedAt;
    }

    public String getOrder() {
        return Order;
    }

    public String getLatitude() {
        return Latitude;
    }

    public String getCoverImage() {
        return CoverImage;
    }
}
