package main.java.models;

import java.util.Date;
import java.util.List;

public class Recommendation {
    private List<Item> items;
    private Date date;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Recommendation(List<Item> items, Date date) {
        this.items = items;
        this.date = date;
    }
}
