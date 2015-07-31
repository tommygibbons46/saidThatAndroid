package com.example.thomasgibbons.saidthat;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ThomasGibbons on 7/31/15.
 */
class CustomAdapter extends ArrayAdapter <String> {


    public CustomAdapter(Context context, String[] quotes) {
        super(context, R.layout.quote_cell ,quotes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater quoteCellInflater = LayoutInflater.from(getContext());
        View customView = quoteCellInflater.inflate(R.layout.quote_cell, parent, false);

        String singleQuote = getItem(position);
        TextView quoteTextView = (TextView) customView.findViewById(R.id.quoteText);
        ImageView saidByImageView = (ImageView) customView.findViewById(R.id.saidByImage);

        quoteTextView.setText(singleQuote);
        saidByImageView.setImageResource(R.drawable.tommy);
        return customView;


    }
}
