package main.java.models.policies;

import main.java.models.PurchaseList;
import main.java.models.Recommendation;

import java.util.ArrayList;

public class MixRecomPolicy extends CompositeRecomPolicy{
    private final ArrayList<IRecommendationPolicy> policies = new ArrayList<>();
    @Override
    public Recommendation getRecommendation(ArrayList<PurchaseList> purchaseLists) {
        ArrayList<Recommendation> recommendations = new ArrayList<>();
        for(IRecommendationPolicy policy: policies){
            recommendations.add(policy.getRecommendation(purchaseLists));
        }
        return recommendations.get((int) (Math.random() * recommendations.size()));
    }
}
