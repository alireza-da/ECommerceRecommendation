package main.java.models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private ArrayList<PurchaseList> purchaseHistory;
    private List<Review> reviews;
    private int ID;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<PurchaseList> getPurchaseHistory() {
        return purchaseHistory;
    }

    public void setPurchaseHistory(ArrayList<PurchaseList> purchaseHistory) {
        this.purchaseHistory = purchaseHistory;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public User(String name) {
        this.ID = System.identityHashCode(this);
        this.name = name;
    }

    public int getID() {
        return ID;
    }
}
