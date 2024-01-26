package main.java.models.extsystems;

import java.io.*;

public class TUI {
    public String getInput(String prompt) throws IOException {
        System.out.printf("%s: ", prompt);
        // TEST
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        // TEST
        return reader.readLine();
    }
}
