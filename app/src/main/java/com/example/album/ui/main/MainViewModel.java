package com.example.album.ui.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.album.model.Album;
import com.example.album.service.AlbumRepository;

import java.util.List;

public class MainViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private final LiveData<List<Album>> albumListObservable;

    public MainViewModel(@NonNull AlbumRepository projectRepository) {
        super();
        // If any transformation is needed, this can be simply done by Transformations class ...
        albumListObservable = projectRepository.getAlbumList();
    }

    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */
    public LiveData<List<Album>> getAlbumListObservable() {
        return albumListObservable;
    }
}