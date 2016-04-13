package com.example.wontheone.lab13;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Won Seob Seo(Metropolia UAS) on 2016-03-29.
 */
public class PlayerListAdapter extends BaseAdapter implements ListAdapter {

    Activity activity;
    List<XmlParser.Player> players;

    public PlayerListAdapter(Activity activity, List players) {
        this.activity = activity;
        this.players = (ArrayList<XmlParser.Player>) players;
        for (XmlParser.Player p : this.players) {
            Log.d("constructor, player:", p.toString());
        }
    }

    public void addPlayers(List<XmlParser.Player> players){
        this.players = players;
    }

    public void setPlayers(List<XmlParser.Player> players){
        this.players = players;
    }

    @Override
    public boolean hasStableIds() {
        return super.hasStableIds();
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        super.registerDataSetObserver(observer);
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        super.unregisterDataSetObserver(observer);
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public void notifyDataSetInvalidated() {
        super.notifyDataSetInvalidated();
    }

    @Override
    public boolean areAllItemsEnabled() {
        return super.areAllItemsEnabled();
    }

    @Override
    public boolean isEnabled(int position) {
        return super.isEnabled(position);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return super.getDropDownView(position, convertView, parent);
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public int getCount() {
        return players.size();
    }

    @Override
    public Object getItem(int position) {
        return players.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View targetView = convertView;

        if(targetView == null) {
            targetView = activity.getLayoutInflater().inflate(R.layout.list_item, null);
        }

        XmlParser.Player p = players.get(position);
        if(p != null) {
            TextView tv = (TextView)targetView.findViewById(R.id.playerId);
            tv.setText("" + p.getId());
            tv = (TextView)targetView.findViewById(R.id.playerName);
            tv.setText(p.getName());
        }
        return targetView;
    }
}
