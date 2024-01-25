package main.java.models;

import java.util.*;

public class ItemCat {
    private static ItemCat instance;
    private final List<Item> itemList;
    private int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    public static ItemCat getInstance() {
        if(instance == null){
            instance = new ItemCat();
        }
        return instance;
    }

    public ItemCat() {
        this.itemList = new ArrayList<Item>();
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void addItem(Item item){
        itemList.add(item);
    }

    public Item getItem(String name){
        for (Item item : itemList) {
            if (Objects.equals(item.getName(), name)) {
                return item;
            }
        }
        return null;
    }


}
