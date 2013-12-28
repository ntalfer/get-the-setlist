package com.ntalfer.getthesetlist;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    private TourGigsFragment tourGigsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Verifie que l'on est pas restauré depuis un état précédent (rotation)
        if (savedInstanceState != null) {
            return;
        }

        this.tourGigsFragment = new TourGigsFragment();

        getFragmentManager().beginTransaction()
                .add(R.id.frame_layout, this.tourGigsFragment).commit();
    }
}
