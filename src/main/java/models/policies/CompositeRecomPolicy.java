package main.java.models.policies;

import main.java.models.Item;
import main.java.models.PurchaseList;
import main.java.models.Recommendation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public abstract class CompositeRecomPolicy implements IRecommendationPolicy{
    private ArrayList<IRecommendationPolicy> policies = new ArrayList<>();


    @Override
    public Recommendation getRecommendation(ArrayList<PurchaseList> purchaseLists) {
        return null;
    }

    public void addPolicy(IRecommendationPolicy policy){
        policies.add(policy);
    }

}
