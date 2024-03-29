package main.java.models.policies;

import main.java.models.Item;
import main.java.models.PurchaseList;
import main.java.models.Recommendation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AIRecommendationPolicy implements IRecommendationPolicy{
    @Override
    public Recommendation getRecommendation(ArrayList<PurchaseList> purchaseLists) {
        // fake implementation
        ArrayList<Item> items = new ArrayList<>();
        for(PurchaseList purchaseList: purchaseLists){
            items.add(purchaseList.getItems().get(0));
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return new Recommendation(items, now);
    }
}
