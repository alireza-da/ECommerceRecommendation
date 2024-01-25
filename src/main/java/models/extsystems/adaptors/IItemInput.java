package main.java.models.extsystems.adaptors;

import main.java.models.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public interface IItemInput {
    String scanItemName() throws IOException;
    HashMap<String, String> scanMultiArgument();

    HashMap<String, String> scanMultiArgument(ArrayList<String> keys) throws IOException;
}
