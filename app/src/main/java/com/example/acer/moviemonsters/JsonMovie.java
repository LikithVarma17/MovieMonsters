package com.example.acer.moviemonsters;

import android.os.Parcel;
import android.os.Parcelable;

public class JsonMovie implements Parcelable {

    int id, vote_count;
    boolean video, adult;
    double vote_average;
    long popularity;
    String title;
    String poster_path;
    String original_language;
    String original_title;

    public void setId(int id) {
        this.id = id;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public void setPopularity(long popularity) {
        this.popularity = popularity;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    String backdrop_path;
    String overview;
    String release_date;

    JsonMovie(int vote_count, int id, boolean video, double vote_average, String title, long popularity, String poster_path,
              String original_language, String original_title, String backdrop_path, boolean adult, String overview, String release_date) {
        this.vote_average = vote_average;
        this.id = id;
        this.vote_count = vote_count;
        this.video = video;
        this.adult = adult;
        this.popularity = popularity;
        this.title = title;
        this.poster_path = poster_path;
        this.original_language = original_language;
        this.original_title = original_title;
        this.backdrop_path = backdrop_path;
        this.overview = overview;
        this.release_date = release_date;
    }

    protected JsonMovie(Parcel in) {
        id = in.readInt();
        vote_count = in.readInt();
        video = in.readByte() != 0;
        adult = in.readByte() != 0;
        vote_average = in.readDouble();
        popularity = in.readLong();
        title = in.readString();
        poster_path = in.readString();
        original_language = in.readString();
        original_title = in.readString();
        backdrop_path = in.readString();
        overview = in.readString();
        release_date = in.readString();
    }

    public static final Creator<JsonMovie> CREATOR = new Creator<JsonMovie>() {
        @Override
        public JsonMovie createFromParcel(Parcel in) {
            return new JsonMovie(in);
        }

        @Override
        public JsonMovie[] newArray(int size) {
            return new JsonMovie[size];
        }
    };

    public double getVote_average() {
        return vote_average;
    }

    public int getId() {
        return id;
    }

    public int getVote_count() {
        return vote_count;
    }

    public boolean isVideo() {
        return video;
    }

    public boolean isAdult() {
        return adult;
    }

    public long getPopularity() {
        return popularity;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(vote_count);
        dest.writeByte((byte) (video ? 1 : 0));
        dest.writeByte((byte) (adult ? 1 : 0));
        dest.writeDouble(vote_average);
        dest.writeLong(popularity);
        dest.writeString(title);
        dest.writeString(poster_path);
        dest.writeString(original_language);
        dest.writeString(original_title);
        dest.writeString(backdrop_path);
        dest.writeString(overview);
        dest.writeString(release_date);
    }
}
