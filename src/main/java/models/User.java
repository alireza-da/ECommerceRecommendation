package main.java.models;

import java.util.List;

public class User {
    private String name;
    private PurchaseList purchaseHistory;
    private List<Review> reviews;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PurchaseList getPurchaseHistory() {
        return purchaseHistory;
    }

    public void setPurchaseHistory(PurchaseList purchaseHistory) {
        this.purchaseHistory = purchaseHistory;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public User(String name) {
        this.name = name;
    }
}
