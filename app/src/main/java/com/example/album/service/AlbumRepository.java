package com.example.album.service;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.album.model.Album;
import com.example.album.model.AlbumResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AlbumRepository {
        private AlbumService albumService;
        private static AlbumRepository albumRepository;

        private AlbumRepository() {
            //TODO this gitHubService instance will be injected using Dagger in part #2 ...
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(AlbumService.HTTPS_API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            albumService = retrofit.create(AlbumService.class);
        }

        public synchronized static AlbumRepository getInstance() {
            //TODO No need to implement this singleton in Part #2 since Dagger will handle it ...
            if (albumRepository == null) {
                if (albumRepository == null) {
                    albumRepository = new AlbumRepository();
                }
            }
            return albumRepository;
        }

        public LiveData<List<Album>> getAlbumList() {
            final MutableLiveData<List<Album>> data = new MutableLiveData<>();

            albumService.getAlbumList().enqueue(new Callback<AlbumResponse>() {
                @Override
                public void onResponse(Call<AlbumResponse> call, Response<AlbumResponse> response) {
                    data.setValue(response.body().getResults());
                }

                @Override
                public void onFailure(Call<AlbumResponse> call, Throwable t) {
                    // TODO better error handling in part #2 ...
                    data.setValue(null);
                }
            });

            return data;
        }
}
