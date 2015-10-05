package erin.assignment1_ward.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Erin on 2015-10-04.
 */
public class DBOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "MusicDB.db";

    private static final int DB_VERSION = 1;

    public DBOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ArtistDataSource.CREATE_TABLE);
        db.execSQL(SongDataSource.CREATE_TABLE);

        populateDB(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ArtistDataSource.TABLE_NAME);
        onCreate(db);
    }

    public void populateDB(SQLiteDatabase db) {
        String query;
        query = "INSERT INTO Artist VALUES (1, 'Queen');";
        db.execSQL(query);
        query = "INSERT INTO Artist VALUES (2, 'The Beatles');";
        db.execSQL(query);

        query = "INSERT INTO Song VALUES (1, 'Bohemian Rhapsody', 1, '5:03');";
        db.execSQL(query);
        query = "INSERT INTO Song VALUES (2, 'Somebody to Love', 1, '3:48');";
        db.execSQL(query);

        query = "INSERT INTO Song VALUES (3, 'Yellow Submarine', 2, '2:57');";
        db.execSQL(query);
        query = "INSERT INTO Song VALUES (4, 'Across the Universe', 2, '4:02');";
        db.execSQL(query);
    }
}
