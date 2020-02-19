package com.bhsoft.musiconline.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bhsoft.musiconline.R;
import com.bhsoft.musiconline.model.Audio;
import com.bhsoft.musiconline.view.AudioPlayerActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AudioAdapter extends RecyclerView.Adapter<AudioAdapter.ViewHolder> {

    private ArrayList<Audio> arrayAudio;

    public AudioAdapter(ArrayList<Audio> arrayAudio) {
        this.arrayAudio = arrayAudio;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(holder.ImageCircle.getContext())
                .load(arrayAudio.get(position).getImage())
                .into(holder.ImageCircle);
        holder.txtName.setText(arrayAudio.get(position).getName());
        holder.txtSinger.setText(arrayAudio.get(position).getSinger());
    }

    @Override
    public int getItemCount() {
        return arrayAudio.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView ImageCircle;
        TextView txtName,txtSinger;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ImageCircle = itemView.findViewById(R.id.ImageCircle);
            txtName     = itemView.findViewById(R.id.txtName);
            txtSinger   = itemView.findViewById(R.id.txtSinger);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), AudioPlayerActivity.class);
                    intent.putExtra("audio",arrayAudio.get(getAdapterPosition()));
                    v.getContext().startActivity(intent);
                }
            });
        }

    }
}
