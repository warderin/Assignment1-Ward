package erin.assignment1_ward;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import erin.assignment1_ward.adapters.ArtistAdapter;
import erin.assignment1_ward.adapters.SongAdapter;
import erin.assignment1_ward.model.Artist;
import erin.assignment1_ward.model.Song;

/**
 * Created by Erin on 2015-10-04.
 */
public class SongFragment extends ListFragment {
    public static final String TAG = "SongFragment";

    public SongFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // get the arguments passed in from Activity
        Bundle bundle = getArguments();

        if (bundle == null) {
            bundle = new Bundle();
        }

        // inflate the view and get the ListView
        View view = inflater.inflate(R.layout.fragment_song, container, false);

        if (bundle.containsKey("songs")) {
            ArrayList<Song> songs = bundle.getParcelableArrayList("songs");

            ListView lv = (ListView) view.findViewById(android.R.id.list);

            // create an ArrayAdapter and assign it to the ListView
            SongAdapter adapter = new SongAdapter(getActivity(),
                    android.R.layout.simple_list_item_1, songs);
            lv.setAdapter(adapter);
        }

        return view;
    }
}
