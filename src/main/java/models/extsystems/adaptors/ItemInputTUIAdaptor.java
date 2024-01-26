package main.java.models.extsystems.adaptors;

import main.java.models.extsystems.TUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ItemInputTUIAdaptor implements IItemInput{
    private final TUI tui;

    public ItemInputTUIAdaptor() {
        this.tui = new TUI();
    }

    @Override
    public String scanItemName() throws IOException {
        return tui.getInput("ITEM NAME");
    }

    @Override
    public HashMap<String, String> scanMultiArgument() {
        return null;
    }

    @Override
    public HashMap<String, String> scanMultiArgument(ArrayList<String> keys) throws IOException {
        HashMap<String, String> output = new HashMap<>();
        for(String key: keys){
            output.put(key, tui.getInput(key));
        }
        return output;
    }

}
