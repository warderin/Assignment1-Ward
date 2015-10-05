package erin.assignment1_ward;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import erin.assignment1_ward.model.Artist;
import erin.assignment1_ward.adapters.ArtistAdapter;

/**
 * Created by Erin on 2015-10-04.
 */
public class ArtistFragment extends ListFragment {

    public static final String TAG = "ArtistFragment";

    /* Interface that Activity will implement */
    public interface FragmentListListener {
        void onItemSelected(Artist artist);
    }

    // Variable to hold reference to Activity
    FragmentListListener mListListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Hold a reference to the activity for calling
        mListListener = (FragmentListListener) activity;
    }

    public ArtistFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Get the arguments passed in from MainActivity
        Bundle bundle = getArguments();
        ArrayList<Artist> artists = bundle.getParcelableArrayList("artists");

        // Inflate the view with artist fragment and get the ListView
        View view = inflater.inflate(R.layout.fragment_artist, container, false);
        ListView lv = (ListView) view.findViewById(android.R.id.list);

        // Create an ArtistAdapter and assign it to the ListView
        ArtistAdapter adapter = new ArtistAdapter(getActivity(),
                android.R.layout.simple_list_item_1, artists);
        lv.setAdapter(adapter);

        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Artist clickedArtist = (Artist) l.getItemAtPosition(position);

        Toast.makeText(this.getActivity(), "Clicked Item: " + clickedArtist.getName(), Toast.LENGTH_SHORT).show();

        // Invoke the callback method on MainActivity
        mListListener.onItemSelected(clickedArtist);
    }

}
