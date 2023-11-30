package main.java.models;

import java.math.BigInteger;
import java.util.Date;

public class Item {
    private String name;
    private BigInteger price;
    private Integer discount;
    private Integer quantity;
    private Date dateAdded;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getPrice() {
        return price;
    }

    public void setPrice(BigInteger price) {
        this.price = price;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Item(String name, BigInteger price, Integer discount, Integer quantity, Date dateAdded) {
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.quantity = quantity;
        this.dateAdded = dateAdded;
    }
}
