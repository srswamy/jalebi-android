package com.productions.jalebi;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sathvik on 10/14/2015.
 */
public class Store implements Parcelable {

    private String storeName;
    private String location;

    // TODO: Will add more fields as we move forward

    public Store(String storeName, String location) {
        this.storeName = storeName;
        this.location = location;
    }

    public Store(Parcel input) {
        super();
        readFromParcel(input);
    }

    public static final Parcelable.Creator<Store> CREATOR = new Parcelable.Creator<Store>() {
        public Store createFromParcel(Parcel input) {
            return new Store(input);
        }

        public Store[] newArray(int size) {
            return new Store[size];
        }
    };

    public void readFromParcel(Parcel input) {
        storeName = input.readString();
        location = input.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(storeName);
        dest.writeString(location);
    }

    public String getStoreName() {
        return storeName;
    }

    public String getLocation() {
        return location;
    }

    public void setStoreName(String name) {
        this.storeName = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
