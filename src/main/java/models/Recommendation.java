package main.java.models;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Recommendation {
    private List<Item> items;
    private LocalDateTime date;
    private int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Recommendation(List<Item> items, LocalDateTime date) {
        this.items = items;
        this.date = date;
    }
}
