package com.demo.testcontainer.demotestcontainers.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @Column(name = "item_id", nullable = false, length = 255)
    private String itemId;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    // Constructors, Getters, and Setters

    public Item() {
    }

    public Item(String itemId, String name) {
        this.itemId = itemId;
        this.name = name;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
