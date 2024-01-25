package main.java.models;

import main.java.models.states.purchase.IPurchaseState;
import main.java.models.states.purchase.PurchaseStarted;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class PurchaseList {
    private List<Item> items;
    private BigInteger total;
    private Integer totalDiscount;
    private LocalDateTime submitDate;
    private int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    private IPurchaseState state;

    public PurchaseList() {
        this.state = new PurchaseStarted();
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public BigInteger getTotal() {
        return total;
    }

    public void setTotal(BigInteger total) {
        this.total = total;
    }

    public Integer getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(Integer totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public LocalDateTime getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(LocalDateTime submitDate) {
        this.submitDate = submitDate;
    }

    public Recommendation getRecommendation(User user) throws Exception {
        return state.getRecommendation(user, this);
    }


    public IPurchaseState getState() {
        return state;
    }

    public void setState(IPurchaseState state) {
        this.state = state;
    }

    public void finishPurchase() throws Exception {
        this.state.finishPurchase(this);
    }
}
