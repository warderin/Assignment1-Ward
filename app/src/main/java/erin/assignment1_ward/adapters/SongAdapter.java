package erin.assignment1_ward.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import erin.assignment1_ward.R;
import erin.assignment1_ward.model.Song;

/**
 * Created by Erin on 2015-10-04.
 */
public class SongAdapter extends ArrayAdapter<Song> {

    private List<Song> songs;

    public SongAdapter(Context context, int resource, List<Song> songs) {
        super(context, resource, songs);
        this.songs = songs;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        // Create new Song list item and populate with song data
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.song_list_item, null);
        }

        Song song = songs.get(position);

        TextView txtName = (TextView) view.findViewById(R.id.songName);
        TextView txtDuration = (TextView) view.findViewById(R.id.songDuration);

        txtName.setText(song.getName());
        txtDuration.setText("Duration: " + song.getDuration());

        return view;

    }

}
