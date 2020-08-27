package com.example.album.service;

import com.example.album.model.Album;
import com.example.album.model.AlbumResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AlbumService {
    String HTTPS_API_URL = "https://itunes.apple.com/";

    @GET("search?term=all")
    Call<AlbumResponse> getAlbumList();
}
