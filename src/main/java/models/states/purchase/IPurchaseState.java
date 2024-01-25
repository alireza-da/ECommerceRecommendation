package main.java.models.states.purchase;

import main.java.models.PurchaseList;
import main.java.models.Recommendation;
import main.java.models.User;
import main.java.models.policies.IRecommendationPolicy;

public interface IPurchaseState {
    public Recommendation getRecommendation(User user, PurchaseList purchaseList) throws Exception;
    public void finishPurchase(PurchaseList purchaseList) throws Exception;
    public void restartPurchase(PurchaseList purchaseList) throws Exception;

}
