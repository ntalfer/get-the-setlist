package com.ntalfer.getthesetlist;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SetlistFragment extends Fragment {

    public void setGig(Gig gig) {
        this.gig = gig;
    }

    private Gig gig;
    private ListView listView;
    private TextView textView;

    public SetlistFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.setlist_fragment, container, false);

        this.listView = (ListView) view.findViewById(R.id.setlist);
        this.listView.setAdapter(new GigAdapter(this.gig, getActivity()));

        this.textView = (TextView) view.findViewById(R.id.gig);
        this.textView.setText(this.gig.getShortInfo());

        return view;
    }

    private class GigAdapter extends BaseAdapter {
        private ArrayList<String> songs;
        private Context context;

        private GigAdapter(Gig gig, Context context) {
            this.songs = gig.getSongs();
            this.context = context;
        }

        @Override
        public int getCount() {
            return this.songs.size();
        }

        @Override
        public Object getItem(int i) {
            return this.songs.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View cell = layoutInflater.inflate(R.layout.setlist_cell, null);

            TextView textView = (TextView) cell.findViewById(R.id.songinfo);
            String song = this.songs.get(i);
            textView.setText(String.valueOf(i + 1) + ". " + song);

            return cell;
        }
    }
}
