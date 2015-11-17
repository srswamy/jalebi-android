package com.productions.jalebi;

/**
 * Created by sathvik on 11/17/2015.
 */
public class Item {

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
