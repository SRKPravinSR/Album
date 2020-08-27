package com.example.album.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AlbumResponse {
@SerializedName("resultCount")
    public int resultCount;
    @SerializedName("results")
    public List<Album> results;

    public List<Album> getResults() {
        return results;
    }
}
