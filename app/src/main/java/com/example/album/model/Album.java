package com.example.album.model;

import com.google.gson.annotations.SerializedName;

public class Album {
    @SerializedName("trackId")
    public String trackId;
@SerializedName("artistName")
    public String artistName;
    @SerializedName("trackName")
    public String trackName;
    @SerializedName("collectionName")
    public String collectionName;
    @SerializedName("collectionPrice")
    public float collectionPrice;
    @SerializedName("releaseDate")
    public String releaseDate;
    @SerializedName("artworkUrl100")
    public String artWorkUrl;

    public String getArtistName() {
        return artistName;
    }

    public String getTrackName() {
        return trackName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public String getCollectionPrice() {
        return String.valueOf(collectionPrice);
    }

    public String getReleaseDate() {
        return releaseDate.substring(0,10);
    }

    public String getArtWorkUrl() {
        return artWorkUrl;
    }
}
