package com.example.album.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.album.R;
import com.example.album.databinding.AlbumListItemBinding;
import com.example.album.model.Album;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ProjectViewHolder> {

    List<? extends Album> projectList;

    public void setAlbumList(final List<? extends Album> projectList) {
        if (this.projectList == null) {
            this.projectList = projectList;
            notifyItemRangeInserted(0, projectList.size());
        } else {
            this.projectList.clear();
            this.projectList=projectList;
            notifyDataSetChanged();
        }
    }

    @Override
    public ProjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AlbumListItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.album_list_item,
                        parent, false);
        return new ProjectViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ProjectViewHolder holder, int position) {
        holder.binding.setAlbum(projectList.get(position));
        holder.binding.executePendingBindings();
        Picasso.get().load(projectList.get(position).artWorkUrl)
                .placeholder(R.color.white)
                .error(R.color.white).into(holder.binding.ivIcon);
    }

    @Override
    public int getItemCount() {
        return projectList == null ? 0 : projectList.size();
    }

    static class ProjectViewHolder extends RecyclerView.ViewHolder {

        final AlbumListItemBinding binding;

        public ProjectViewHolder(AlbumListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
