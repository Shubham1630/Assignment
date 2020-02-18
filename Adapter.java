package com.android.assignment;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;

import java.util.List;



public class Adapter extends RecyclerView.Adapter<Adapter.WordViewHolder>  {

    private final List<String> mWordList,mover_titlt,minformation;
    Context mcontext;
    private LayoutInflater mInflater;

    public Adapter(Context context,
                   List<String> wordList, List<String> over_title, List<String> information) {
        Log.i("WordlistAdapter","constructor called");
        mInflater = LayoutInflater.from(context);
        this.mWordList = wordList;
        this.mover_titlt=over_title;
        this.minformation=information;
        this.mcontext=context;
    }


    class WordViewHolder extends RecyclerView.ViewHolder {
        public final TextView infoView ;
        ImageView imageView;
        final Adapter mAdapter;

        public WordViewHolder(View itemView, Adapter adapter) {
            super(itemView);
            Log.i("viewfolderinnerclass","wordview holder called");
            infoView = itemView.findViewById(R.id.word);
            imageView=itemView.findViewById(R.id.image1);
            this.mAdapter = adapter;
        }
    }
    @NonNull
    @Override

    public Adapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i("oncreateviewholder","inflatewordlistitem");
        View mItemView = mInflater.inflate(R.layout.imagelist_item,
                parent, false);
        return new Adapter.WordViewHolder(mItemView, this);

    }

    @Override

    public void onBindViewHolder(@NonNull Adapter.WordViewHolder holder, int position) {

        final String images = mWordList.get(position);
        final String info=mover_titlt.get(position);
        final String details=minformation.get(position);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mcontext,DetailsActivity.class);
                intent.putExtra("images",images);
                intent.putExtra("details",details);
                mcontext.startActivity(intent);

            }
        });
        if (info.equals("")){
            holder.infoView.setVisibility(View.GONE);
        }
        else {
            holder.infoView.setVisibility(View.VISIBLE);
            holder.infoView.setText(info);

        }
        Picasso.get().load(images).into(holder.imageView);
    }

    @Override
    public int getItemCount() {

        return mWordList.size();
    }
}

