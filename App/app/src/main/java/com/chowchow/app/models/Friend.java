package com.chowchow.app.models;

/**
 * Created by jackychan on 16/8/15.
 */
public class Friend {

    private static final String TAG = "Friend";

    public static class Builder {

        String id;
        String name;
        int distance; // in meters

        public Builder(String id) {
            this.id = id;
            this.name = "";
            this.distance = 0;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDistance(int distance) {
            this.distance = distance;
            return this;
        }

        public Friend build() {
            return new Friend(this); 
        }


    }

    private String id;
    private String name;
    private int distance; // in meters

    public Friend(Builder builder) {
        this.id = builder.id;
        this.name= builder.name;
        this.distance = builder.distance;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDistance() {
        return distance;
    }


    public static String getTAG() {
        return TAG;
    }
}
