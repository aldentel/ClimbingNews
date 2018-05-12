package com.example.salome.climbingnews;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ClimbingAdapter extends ArrayAdapter<ClimbingNews> {

    private Context context;

    public ClimbingAdapter(Activity context, ArrayList<ClimbingNews> article) {

        super(context, 0, article);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.climbing_list_item, parent, false);
        }

        final ClimbingNews currentArticle = getItem(position);


        TextView titleView = listItemView.findViewById(R.id.title);
        titleView.setText(getContext().getString(R.string.title, currentArticle.getTitle()));

        TextView sectionView = listItemView.findViewById(R.id.section);
        sectionView.setText(getContext().getString(R.string.section, currentArticle.getSection()));

        TextView dateView = listItemView.findViewById(R.id.date);
        dateView.setText(getContext().getString(R.string.datePub, currentArticle.getDate()));

        TextView authorView = listItemView.findViewById(R.id.author);
        authorView.setText(getContext().getString(R.string.author, currentArticle.getAuthor()));

        TextView urlView = listItemView.findViewById(R.id.url);
        urlView.setText(currentArticle.getUrl());

        return listItemView;
    }
}