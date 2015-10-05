package erin.assignment1_ward;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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

        // get the FragmentManager and put in the ArtistFragment
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft;
        artistFragment = (ArtistFragment) fm.findFragmentByTag(ArtistFragment.TAG);

        if (artistFragment == null) {
            // create a new instance of ArtistFragment & set arguments
            artistFragment = new ArtistFragment();
            // create a Bundle to send the info to fragment
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("artists", artists);
            artistFragment.setArguments(bundle);
            ft = fm.beginTransaction();
            ft.add(R.id.container, artistFragment, ArtistFragment.TAG);
            ft.commit();
        } else {
            fm.beginTransaction()
                    .replace(R.id.container, artistFragment, ArtistFragment.TAG)
                    .commit();
        }
        getActionBar().setDisplayHomeAsUpEnabled(false);

        if (getResources().getConfiguration().orientation != Configuration.ORIENTATION_PORTRAIT) {
            if (songFragment != null) {
                ft = fm.beginTransaction();
                ft.replace(R.id.container, artistFragment, ArtistFragment.TAG);
                ft.commit();
            }
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

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        if (getResources().getConfiguration().orientation != Configuration.ORIENTATION_PORTRAIT) {
            // get the FragmentManager and put in the SongFragment
            ft.replace(R.id.container2, songFragment, SongFragment.TAG);
            ft.addToBackStack(SongFragment.TAG);
            ft.commit();
        } else {
            getActionBar().setDisplayHomeAsUpEnabled(true); //set up 'Up' button in the song view action bar.
            ft.replace(R.id.container, songFragment, SongFragment.TAG);
            ft.addToBackStack(ArtistFragment.TAG);
            ft.commit();
        }
    }
}
