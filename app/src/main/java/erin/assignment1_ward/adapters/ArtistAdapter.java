package erin.assignment1_ward.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import erin.assignment1_ward.R;
import erin.assignment1_ward.model.Artist;

/**
 * Created by Erin on 2015-10-04.
 */
public class ArtistAdapter extends ArrayAdapter<Artist> {

    private List<Artist> artists;

    public ArtistAdapter(Context context, int resource, List<Artist> artists) {
        super(context, resource, artists);
        this.artists = artists;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        // Create new Artist list item and populate with artist name
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.artist_list_item, null);
        }

        Artist artist = artists.get(position);

        TextView txtName = (TextView) view.findViewById(R.id.artistName);

        txtName.setText(artist.getName());

        return view;

    }

}
