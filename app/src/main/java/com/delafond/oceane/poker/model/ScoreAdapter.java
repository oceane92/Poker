package com.delafond.oceane.poker.model;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.delafond.oceane.poker.R;

import java.util.ArrayList;

public class ScoreAdapter extends BaseAdapter {
    public Activity context;
    public ArrayList<Scores> score;

    public ScoreAdapter(Activity context, ArrayList<Scores> score) {
        this.context = context;
        this.score = score;
    }

    @Override
    public String toString() {
        return "ExperienceAdapter{" +
                "context=" + context +
                ", experiences=" + score +
                '}';
    }

    @Override
    public int getCount() {
        return this.score.size();
    }

    @Override
    public Object getItem(int position) {
        return this.score.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.scores_item, null);
        TextView textView = (TextView) rowView.findViewById(R.id.textViewScore);
        textView.setText(this.score.get(position).toString());

        return rowView;
    }

}
