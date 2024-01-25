package main.java.models.extsystems.adaptors;

import main.java.models.Item;
import main.java.models.extsystems.QRScanner;

import java.util.ArrayList;
import java.util.HashMap;

public class ItemInputQRAdaptor implements IItemInput{
    private QRScanner qrScanner;

     public String scanItemName(){

         return null;
     }

    @Override
    public HashMap<String, String> scanMultiArgument() {
        return null;
    }

    @Override
    public HashMap<String, String> scanMultiArgument(ArrayList<String> keys) {
        return null;
    }
}
