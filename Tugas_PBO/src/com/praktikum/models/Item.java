package com.praktikum.models;

import com.praktikum.users.Mahasiswa;

public class Item {
    private String itemName;
    private String description;
    private String location;
    private String status;
    private Mahasiswa pelapor;

    public Item(String itemName, String description, String location, String status) {
        this.itemName = itemName;
        this.description = description;
        this.location = location;
        this.status = status;
    }

    public String getItemName() {
        return itemName;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Mahasiswa getPelapor() {
        return pelapor;
    }

    public void setPelapor(Mahasiswa pelapor) {
        this.pelapor = pelapor;
    }
}
