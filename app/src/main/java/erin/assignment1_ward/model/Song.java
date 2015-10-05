package erin.assignment1_ward.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Erin on 2015-10-04.
 */
public class Song implements Parcelable {

    String name;
    long artistId;
    String duration;
    long id;

    public Song(String songName, long songArtistId, String songDuration) {
        name = songName;
        artistId = songArtistId;
        duration = songDuration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public long getArtistId() {
        return artistId;
    }

    public void setArtistId(long artistId) {
        this.artistId = artistId;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeLong(artistId);
        dest.writeString(duration);
        dest.writeLong(id);
    }

    public static final Parcelable.Creator<Song> CREATOR = new Parcelable.Creator<Song>()
    {
        public Song createFromParcel(Parcel in)
        {
            return new Song(in.readString(), in.readLong(), in.readString());
        }
        public Song[] newArray(int size)
        {
            return new Song[size];
        }
    };
}
