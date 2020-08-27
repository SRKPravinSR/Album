package com.example.album.ui.main;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.album.R;
import com.example.album.adapter.AlbumAdapter;
import com.example.album.databinding.MainFragmentBinding;
import com.example.album.model.Album;
import com.example.album.service.AlbumRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private MainFragmentBinding binding;
    private AlbumAdapter albumAdapter;
    private Button btnSearch;
    private List<Album> albumList;


    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false);
        btnSearch = binding.btnSearch;
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
showSearch();
            }
        });
        albumAdapter = new AlbumAdapter();
        binding.rvAlbum.setAdapter(albumAdapter);
        return (View) binding.getRoot();
    }

    private void showSearch() {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.search_dialog, null);
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setView(view);
            final Dialog dialog = builder.create();
            dialog.setCancelable(false);
            Button btnClose = view.findViewById(R.id.btn_close);
            Button btnSearch = view.findViewById(R.id.btn_search);
        EditText etArtist = view.findViewById(R.id.et_artist);
        EditText etTrack = view.findViewById(R.id.et_track);
        EditText etCollectionName = view.findViewById(R.id.et_collection_name);
        EditText etCollectionPrice = view.findViewById(R.id.et_collection_price);
        EditText etReleaseDate = view.findViewById(R.id.et_release_date);
        btnClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            btnSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String artist = etArtist.getText().toString();
                    String track = etTrack.getText().toString();
                    String collectionName = etCollectionName.getText().toString();
                    String collectionPrice = etCollectionPrice.getText().toString();
                    String releaseDate = etReleaseDate.getText().toString();
                    if(artist.length()==0 &&track.length()==0){
                        Toast.makeText(getContext(), "Artist Name and Track Name are required to search", Toast.LENGTH_SHORT).show();
                    } else {
                        if (albumList != null) {
                            List<Album> albums = new ArrayList<>();
                            boolean add = false;
                            for(Album album: albumList){
                                if(album.artistName.equalsIgnoreCase(artist) && album.trackName.equalsIgnoreCase(track)){
                                    add = true;
                                    if(collectionName.length()>0 && !(album.collectionName.equalsIgnoreCase(collectionName))){
                                        add = false;
                                    }
                                    if(collectionPrice.length()>0 && !(String.valueOf(album.collectionPrice).equalsIgnoreCase(collectionPrice))){
                                        add = false;
                                    }
                                    if(releaseDate.length()>0 && !(album.releaseDate.equalsIgnoreCase(releaseDate))){
                                        add = false;
                                    }
                                }
                                if (add) {
                                    albums.add(album);
                                    add = false;
                                }
                            }
                            albumAdapter.setAlbumList(albums);
                        }
                        dialog.dismiss();
                    }
                }
            });
            Window window = dialog.getWindow();
            window.setGravity(Gravity.CENTER);

            dialog.show();// I was told to call show first I am not sure if this it to cause layout to happen so that we can override width?

            WindowManager.LayoutParams lWindowParams = new WindowManager.LayoutParams();
            lWindowParams.copyFrom(window.getAttributes());
            lWindowParams.width = WindowManager.LayoutParams.MATCH_PARENT; // this is where the magic happens
            lWindowParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(lWindowParams);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new MainViewModel(AlbumRepository.getInstance());
        // TODO: Use the ViewModel
        setData();
    }

    private void setData() {
        mViewModel.getAlbumListObservable().observe(this, new Observer<List<Album>>() {
            @Override
            public void onChanged(@Nullable List<Album> albums) {
                if (albums != null) {
                    albumList = albums;
                    Collections.sort(albumList, new Comparator<Album>() {
                        @Override
                        public int compare(Album album, Album t1) {
                            return album.releaseDate.compareTo(t1.releaseDate);
                        }
                    });
                    List<Album> albumList1 = new ArrayList<>(albumList);
                    albumAdapter.setAlbumList(albumList1);
                }
            }
        });
    }

}