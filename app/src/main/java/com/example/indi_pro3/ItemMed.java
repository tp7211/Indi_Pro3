package com.example.indi_pro3;

import java.io.Serializable;

public class ItemMed implements Serializable {

    private String id, name, price, qty = "100", size, power, ageGroup, description1, description2;
    private int imageId;


    public ItemMed(String id, String name, String price, String qty, String size, String power, String ageGroup, String description1, String description2, int imageId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.size = size;
        this.power = power;
        this.ageGroup = ageGroup;
        this.description1 = description1;
        this.description2 = description2;
        this.imageId = imageId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getQty() {
        return qty;
    }

    public String getSize() {
        return size;
    }

    public String getPower() {
        return power;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public String getDescription1() {
        return description1;
    }

    public String getDescription2() {
        return description2;
    }

    public int getImageId() {
        return imageId;
    }
}
