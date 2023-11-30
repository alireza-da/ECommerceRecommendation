package main.java.models;

import java.util.List;
import java.util.Objects;

public class ItemCat {
    private List<Item> itemList;

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
