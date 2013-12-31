package com.ntalfer.getthesetlist;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class TourGigsFragment extends Fragment {
    private ListView listView;
    private TextView textView;

    public interface OnGigSelectedListener {
        public void onGigSelected(Gig selected);
    }

    private OnGigSelectedListener onGigSelectedListener;

    public void setOnGigSelectedListener(OnGigSelectedListener onGigSelectedListener) {
        this.onGigSelectedListener = onGigSelectedListener;
    }

    public ArrayList<Gig> getGigs() {
        return gigs;
    }

    private ArrayList<Gig> gigs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.tour_fragment, container, false);

        this.listView = (ListView) view.findViewById(R.id.listView);
        this.textView = (TextView) view.findViewById(R.id.tourTitle);
        this.textView.setText("Blur - 2013/2014 Festival Tour");

        if (this.gigs == null)
            new TourFetcher(this.getActivity()).execute();
        else
            reloadData();

        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (onGigSelectedListener != null) {
                    onGigSelectedListener.onGigSelected(gigs.get(i));
                }
            }
        });

        return view;
    }

    public void reloadData() {
        listView.setAdapter(new TourGigsAdapter(gigs, getActivity()));
    }

    private class TourGigsAdapter extends BaseAdapter {
        private ArrayList<Gig> list;
        private Context context;

        private TourGigsAdapter(ArrayList<Gig> list, Context context) {
            this.list = list;
            this.context = context;
        }

        @Override
        public int getCount() {
            return this.list.size();
        }

        @Override
        public Object getItem(int i) {
            return this.list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View cell = layoutInflater.inflate(R.layout.tour_cell, null);

            Gig gig = this.list.get(i);

            TextView textView = (TextView) cell.findViewById(R.id.tourgiginfo);
            String info = gig.getShortInfo();
            textView.setText(info);

            return cell;
        }
    }

    private class TourFetcher extends AsyncTask<Void, Void, ArrayList<Gig>> {
        private ProgressDialog progressDialog;

        public TourFetcher(Context c) {
            this.progressDialog = new ProgressDialog(c);
            this.progressDialog.setMessage("Please Wait");
            this.progressDialog.setCancelable(false);
            this.progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        }

        @Override
        protected void onPreExecute() {
            this.progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected ArrayList<Gig> doInBackground(Void... voids) {
            try {
                Tour tour = new Tour();
                return tour.getGigs();
            } catch (Exception e) {
                return new ArrayList<Gig>();
            }
        }

        @Override
        protected void onPostExecute(ArrayList<Gig> retrievedGigs) {
            this.progressDialog.dismiss();
            gigs = retrievedGigs;
            listView.setAdapter(new TourGigsAdapter(gigs, getActivity()));
            super.onPostExecute(retrievedGigs);
        }
    }
}
