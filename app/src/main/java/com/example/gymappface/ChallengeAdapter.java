package com.example.gymappface;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChallengeAdapter extends RecyclerView.Adapter<ChallengeAdapter.ViewHolder>
{
    final private OnClickListener clickListener;
    private ArrayList <Challenges> challenges;

    public ChallengeAdapter(ArrayList<Challenges> challenges, OnClickListener listener) {
        this.clickListener = listener;
        this.challenges = challenges;
    }

    @NonNull
    @Override
    public ChallengeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.challenges_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.name.setText(challenges.get(i).getP_name());
        viewHolder.desc.setText(challenges.get(i).getP_description());
        viewHolder.image.setImageResource(challenges.get(i).getP_image());
    }

    @Override
    public int getItemCount() {
        return challenges.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView desc;
        ImageView image;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameOfChallenge);
            desc = itemView.findViewById(R.id.description);
            image = itemView.findViewById(R.id.photo_ch);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onListItemClick(getAdapterPosition());
        }
    }
    public interface OnClickListener
    {
        void onListItemClick(int clickedItemIndex);
    }
}

