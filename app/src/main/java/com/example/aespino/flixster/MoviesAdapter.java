package com.example.aespino.flixster;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MoviesAdapter extends ArrayAdapter<Movie> {

    private static class ViewHolder {
        TextView title;
        TextView overview;
        ImageView imageView;
    }

    public MoviesAdapter(Context context, ArrayList<Movie> movies) {
        super(context, android.R.layout.simple_list_item_1, movies);
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        Movie movie = getItem(position);
        int orientation = getContext().getResources().getConfiguration().orientation;

        if(movie.getRating() < 5 && orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            return 0;
        }
        else
        {
            return 1;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Get the data item for this position
        Movie movie = getItem(position);

        ViewHolder viewHolder = new ViewHolder();

        String ApiUrl = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

        if(getItemViewType(position) == 0) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_movie, parent, false);
                viewHolder.title = (TextView) convertView.findViewById(R.id.tvTitle);
                viewHolder.imageView = (ImageView) convertView.findViewById(R.id.ivPoster);
                viewHolder.overview = (TextView) convertView.findViewById(R.id.tvOverview);
                convertView.setTag(viewHolder);

            }
            else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            //iv poster set
            Picasso.with(getContext()).load(movie.getPosterUrl()).into(viewHolder.imageView);
            //Populate the data into the template  using the object
            viewHolder.overview.setText(movie.getOverview());
            viewHolder.title.setText(movie.getTitle());
            viewHolder.imageView.setVisibility(View.VISIBLE);
    }
        else
        {
            if( convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.backdrop_item, parent, false);
                viewHolder.imageView = (ImageView) convertView.findViewById(R.id.backdropView);
                convertView.setTag(viewHolder);
            }
            else{
                viewHolder = (ViewHolder) convertView.getTag();
            }

            //iv poster set
            Picasso.with(getContext()).load(movie.getBackdropUrl()).into(viewHolder.imageView);
            viewHolder.imageView.setVisibility(View.VISIBLE);
        }

        //return the completed view to return on screen
        return convertView;
    }
}



