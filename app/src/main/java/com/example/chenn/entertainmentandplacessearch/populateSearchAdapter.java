package com.example.chenn.entertainmentandplacessearch;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class populateSearchAdapter extends RecyclerView.Adapter<populateSearchAdapter.MyViewHolder> {
    DetailsObject[] dobj;
    public Context myContext;
    SharedPreferences sharedPref;

    // private final View.OnClickListener mOnClickListener = new MyOnClickListener();
   private AdapterView.OnItemClickListener listener;

   public class myOnclickListener implements AdapterView.OnItemClickListener {

       public void onItemClick() {
       }

       @Override
       public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

       }
   }

    public populateSearchAdapter(DetailsObject[] obj,Context context,SharedPreferences sharedPref) {
        this.dobj = obj;
        this.listener = new myOnclickListener();
        this.myContext = context;
        this.sharedPref = sharedPref;
        //this.moviesList = moviesList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.populatesearchrow, parent, false);
        //itemView.setOnClickListener();

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //Movie movie = moviesList.get(position);
       // holder.imgview.setI
        Picasso.get().load(dobj[position].iconurl.substring(1,dobj[position].iconurl.length()-1)).into(holder.imgview);
        holder.name.setText(dobj[position].name.substring(1,dobj[position].name.length()-1));
       holder.location.setText(dobj[position].add.substring(1,dobj[position].add.length()-1));
       holder.favview.setImageResource(R.drawable.favouritesoutlineblack);
       holder.clicked = false;
        holder.bind(position, listener);

        SharedPreferences.Editor editor = sharedPref.edit();
        System.out.println("Here for "+position + " and value is "+holder.clicked);
        //Set<String> set = sharedPref.getStringSet("favIDs",null);
        String fstr = sharedPref.getString("favIDarr",null);

        Gson gson = new Gson();

        if(fstr!=null) {
            List<String> list;
            list =  gson.fromJson(fstr, List.class);

            System.out.println("value is " + Arrays.toString(list.toArray()));
            String id = dobj[position].obj.getAsJsonObject().get("id").getAsString();
            System.out.println(" id " + id);

            if (list.contains(id)) {
                System.out.println("Inside this beacuse it contained Id!! "+id);
                holder.clicked = true;
                holder.favview.setImageResource(R.drawable.favredheart);
            }
        }
        final MyViewHolder h1 = holder;
              // holder.year.setText("Hurrah");
    }

    @Override
    public int getItemCount() {
        return dobj.length;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, location;
        public ImageView imgview,favview;
        public boolean clicked = false;
        public MyViewHolder(View view) {
            super(view);
            imgview = (ImageView) view.findViewById(R.id.imgsrcicon);
           name = (TextView) view.findViewById(R.id.srchName);
            location = (TextView) view.findViewById(R.id.srchlocation);
            favview = (ImageView) view.findViewById(R.id.imgfavicon);
            System.out.println("Inside the contructor of myview holder");
            favview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("Clicked Fav");
                    String text = "";
                    String loc = "";
                    if(clicked)
                    {
                        favview.setImageResource(R.drawable.favouritesoutlineblack);
                        text = name.getText().toString() + " was removed from favourites";
                        loc = location.getText().toString();

                        if(myContext instanceof displayresultsAcitivity){
                            ((displayresultsAcitivity)myContext).removeFromFavourties( name.getText().toString(),loc);
                        }
                    }
                    else {
                        favview.setImageResource(R.drawable.favredheart);
                        text = name.getText().toString() + " was added to the favourites";
                        loc = location.getText().toString();

                        if(myContext instanceof displayresultsAcitivity){
                            ((displayresultsAcitivity)myContext).addtoFavourites( name.getText().toString(),loc);
                        }
                    }

                    clicked = !clicked;
                    Toast.makeText(myContext, text, Toast.LENGTH_SHORT).show();
                }


            });

            // year = (TextView) view.findViewById(R.id.sr2);
        }
        public void bind(final int item, final AdapterView.OnItemClickListener listener) {
           // name.setText(item.name);
           // Picasso.with(itemView.getContext()).load(item.imageUrl).into(image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                   // Toast.makeText(getContext(), "Item Clicked", Toast.LENGTH_LONG).show();
                    System.out.println("Clicked V");
                    System.out.println("Obj "+dobj[item].name);
                    System.out.println("Context is "+myContext);
                    if(myContext instanceof displayresultsAcitivity){
                        ((displayresultsAcitivity)myContext).myFunc(item);
                    }


                }
            });
        }

    }

}







