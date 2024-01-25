package main.java.models;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public class Item {
    private String name;
    private List<ItemDesc> itemDescs;
    private int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    public List<ItemDesc> getItemDescs() {
        return itemDescs;
    }

    public void setItemDescs(List<ItemDesc> itemDescs) {
        this.itemDescs = itemDescs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
