package erin.assignment1_ward.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Erin on 2015-10-04.
 */
public class Artist implements Parcelable {

    String name;
    long id;

    public Artist(long artistId, String artistName) {
        id = artistId;
        name = artistName;
    }

    public String getName() {
        return name;
    }

    public long getId() { return id; }

    public void setName(String name) {
        this.name = name;
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
        dest.writeLong(id);
    }

    public static final Parcelable.Creator<Artist> CREATOR = new Parcelable.Creator<Artist>()
    {
        public Artist createFromParcel(Parcel in)
        {
            return new Artist(in.readLong(), in.readString());
        }
        public Artist[] newArray(int size)
        {
            return new Artist[size];
        }
    };

}
