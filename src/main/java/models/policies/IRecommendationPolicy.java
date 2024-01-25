package main.java.models.policies;

import main.java.models.PurchaseList;
import main.java.models.Recommendation;

import java.util.ArrayList;

public interface IRecommendationPolicy {
    public Recommendation getRecommendation(ArrayList<PurchaseList> purchaseLists);
}
