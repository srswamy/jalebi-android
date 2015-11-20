package com.productions.jalebi;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sathvik on 11/17/2015.
 */
public class Item implements Parcelable {

    private int id;
    private String name;
    private String description;
    private int price;
    private String priceUnit;

    public Item(int id, String name, String description, int price, String priceUnit) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.priceUnit = priceUnit;
    }

    public Item (Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.description = in.readString();
        this.price = in.readInt();
        this.priceUnit = in.readString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeInt(this.price);
        dest.writeString(this.priceUnit);
    }

    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Item> CREATOR
            = new Parcelable.Creator<Item>() {

        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPriceUnit() {
        return this.priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }


}
