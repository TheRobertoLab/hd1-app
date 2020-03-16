package com.example.hd1app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hd1app.Models.Deal;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    private Context context;
    private ArrayList<Deal> items;
    private View.OnClickListener mOnItemClickListener;

    public CustomAdapter(Context context, ArrayList<Deal> items) {
        this.context = context;
        this.items = items;
    }
    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.item, parent, false));
    }
    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.itemTitle.setText(items.get(position).getTitle());
        holder.itemImage.setImageResource(items.get(position).getImage());
        holder.likeButton.setTag(R.drawable.heart);
        holder.likeButton.setImageResource(R.drawable.heart);
    }
    @Override
    public int getItemCount() {
        return items.size();
    }
    public Deal getItem(int position){
        return items.get(position);
    }
    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        private TextView itemTitle;
        private ImageView likeButton;
        private ImageView itemImage;


        public CustomViewHolder(final View view) {
            super(view);
            itemImage = view.findViewById(R.id.item_image);
            itemTitle = view.findViewById(R.id.item_title);
            likeButton = view.findViewById(R.id.like_button);

            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);

            likeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = (int)likeButton.getTag();
                    if( id == R.drawable.heart){

                        likeButton.setTag(R.drawable.heart_liked);
                        likeButton.setImageResource(R.drawable.heart_liked);
                        Toast.makeText(view.getContext(),"Deal has been added to favourites",Toast.LENGTH_SHORT).show();

                    }else{
                        likeButton.setTag(R.drawable.heart);
                        likeButton.setImageResource(R.drawable.heart);
                        Toast.makeText(view.getContext(),"Deal has been removed from favourites",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}