package com.example.chenn.entertainmentandplacessearch;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PopulateReviewsAdapter extends RecyclerView.Adapter<PopulateReviewsAdapter.MyViewHolder> {

    ReviewsDetails [] obj;
    Context myContext;
    private AdapterView.OnItemClickListener listener;
    public class myOnclickListener implements AdapterView.OnItemClickListener {

        public void onItemClick() {
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    }
    PopulateReviewsAdapter(ReviewsDetails [] obj, Context myContext)
    {
        this.obj=obj;
        this.listener = new myOnclickListener();
        this.myContext = myContext;

    }


    @Override
    public PopulateReviewsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.review_row, parent, false);

        return new PopulateReviewsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PopulateReviewsAdapter.MyViewHolder holder, int position) {

        holder.name.setText(obj[position].name);
        holder.time.setText(obj[position].time);
        holder.text.setText(obj[position].text);
        holder.rb.setRating((float)obj[position].rating);
        holder.bind(position, listener);

        if(obj[position].img_url.equals(""))
        {
            //Picasso.get().load(obj[position].img_url).into(holder.img);
            holder.img.setAlpha(0);
        }
        else
        {
            Picasso.get().load(obj[position].img_url).into(holder.img);

        }

    }

    @Override
    public int getItemCount() {
        return obj.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, time,text;
        public RatingBar rb;
        public ImageView img;

        public MyViewHolder(View view) {
            super(view);

            name = (TextView) view.findViewById(R.id.rev_name);
            time = (TextView) view.findViewById(R.id.rev_time);
            text = (TextView) view.findViewById(R.id.rev_text);
            rb = (RatingBar) view.findViewById(R.id.ratingBar2);
            img = (ImageView) view.findViewById(R.id.rev_img_icon);

        }
        public void bind(final int item, final AdapterView.OnItemClickListener listener) {
            // name.setText(item.name);
            // Picasso.with(itemView.getContext()).load(item.imageUrl).into(image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {

                    String url = obj[item].author_url;
                    if(!url.equals("")) {
                        Uri uri = Uri.parse(url); // missing 'http://' will cause crashed

                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        myContext.startActivity(intent);
                    }



                }
            });
        }

    }
}
