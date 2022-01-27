package pl.javastart.foodieapp.entity;

import javax.persistence.*;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long itemId;
    private double price;
    private String name;
    private String shortDescription;
    @Column(length = 1024)
    private String description;
    private String imageUrl;

    public Item() {
    }

    public Item(double price, String name, String shortDescription, String longDescription, String imageUrl) {
        this.price = price;
        this.name = name;
        this.shortDescription = shortDescription;
        this.description = longDescription;
        this.imageUrl = imageUrl;
    }

    public long getItemId() {
        return itemId;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
