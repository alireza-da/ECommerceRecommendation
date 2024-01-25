package main.java.models;

import java.math.BigInteger;
import java.time.LocalDateTime;

public class ItemDesc {
    private int price; // Set to BigInteger at production stage
    private Double discount;
    private Integer quantity;
    private LocalDateTime dateAdded;
    private int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    public Integer getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime  getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDateTime  dateAdded) {
        this.dateAdded = dateAdded;
    }

}
