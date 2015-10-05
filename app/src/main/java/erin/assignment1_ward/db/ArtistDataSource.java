package erin.assignment1_ward.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import erin.assignment1_ward.model.Artist;
import erin.assignment1_ward.model.Song;

/**
 * Created by Erin on 2015-10-04.
 */
public class ArtistDataSource {

    private SQLiteDatabase db;
    private SQLiteOpenHelper dbOpenHelper;

    public static final String TABLE_NAME = "Artist";

    public static final String ID_COLUMN = "_id";
    public static final int ID_COLUMN_POSITION = 0;

    public static final String NAME_COLUMN = "name";
    public static final int NAME_COLUMN_POSITION = 1;

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
            ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAME_COLUMN + " TEXT" +
            ")";

    public ArtistDataSource(Context context) {
        dbOpenHelper = new DBOpenHelper(context);
    }

    // Save new Artist to Artist table
    public Artist saveArtist(Artist artist) {
        db = dbOpenHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME_COLUMN, artist.getName());

        long id = db.insert(TABLE_NAME, null, values);

        artist.setId(id);

        db.close();

        return artist;
    }

    // Get all Artists in Artist table
    public ArrayList<Artist> getArtists() {
        ArrayList<Artist> artists = new ArrayList<>();

        db = dbOpenHelper.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            long id = cursor.getLong(ID_COLUMN_POSITION);
            String name = cursor.getString(NAME_COLUMN_POSITION);
            artists.add(new Artist(id, name));
        }

        cursor.close();

        db.close();

        return artists;
    }

}
