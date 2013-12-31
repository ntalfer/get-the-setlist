package com.ntalfer.getthesetlist;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class MainActivity extends Activity implements TourGigsFragment.OnGigSelectedListener {

    private TourGigsFragment tourGigsFragment;
    private SetlistFragment setlistFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Verifie que l'on est pas restauré depuis un état précédent (rotation)
        if (savedInstanceState != null) {
            return;
        }

        this.tourGigsFragment = new TourGigsFragment();
        this.tourGigsFragment.setOnGigSelectedListener(this);

        getFragmentManager().beginTransaction()
                .add(R.id.frame_layout, this.tourGigsFragment).commit();
    }

    @Override
    public void onGigSelected(Gig gig) {
        if (this.setlistFragment == null) {
            this.setlistFragment = new SetlistFragment();
        }

        this.setlistFragment.setGig(gig);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.frame_layout, this.setlistFragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.addToBackStack(null);
        ft.commit();
    }
}
