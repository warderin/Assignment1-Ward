package erin.assignment1_ward;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import erin.assignment1_ward.db.ArtistDataSource;
import erin.assignment1_ward.db.SongDataSource;
import erin.assignment1_ward.model.Artist;
import erin.assignment1_ward.model.Song;

public class MainActivity extends Activity implements ArtistFragment.FragmentListListener {

    private SongFragment songFragment;
    private ArtistFragment artistFragment;

    @Override
    public void onItemSelected(Artist clickedArtist) {
        showSongs(clickedArtist.getId());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArtistDataSource ds = new ArtistDataSource(this);
        ArrayList<Artist> artists = ds.getArtists();

        // create a Bundle to send the info to fragment
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("artists", artists);

        // get the FragmentManager and put in the ArtistFragment
        FragmentManager fm = getFragmentManager();
        artistFragment = (ArtistFragment) fm.findFragmentByTag(ArtistFragment.TAG);

        if (artistFragment == null) {
            // create a new instance of ArtistFragment & set arguments
            ArtistFragment artistFragment = new ArtistFragment();
            artistFragment.setArguments(bundle);
            fm.beginTransaction()
                    .add(R.id.container, artistFragment, ArtistFragment.TAG)
                    .commit();
        }

        if (getResources().getConfiguration().orientation != Configuration.ORIENTATION_PORTRAIT) {
            songFragment = (SongFragment) fm.findFragmentByTag(SongFragment.TAG);
            if (songFragment == null) {
                SongFragment songFragment = new SongFragment();
                showSongs(1);
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showSongs(long clickedArtistId) {

        Bundle bundle = new Bundle();

        SongDataSource ds = new SongDataSource(this);
        ArrayList<Song> songs = ds.getSongs(clickedArtistId);

        // create a Bundle to send the info to fragment
        bundle.putParcelableArrayList("songs", songs);

        // create a new instance of SongFragment & set arguments
        SongFragment songFragment = new SongFragment();
        songFragment.setArguments(bundle);

        // get the FragmentManager and put in the SongFragment
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction()
                .replace(R.id.container2, songFragment, SongFragment.TAG)
                .commit();
    }
}
