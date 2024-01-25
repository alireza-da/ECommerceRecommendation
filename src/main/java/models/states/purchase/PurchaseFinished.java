package main.java.models.states.purchase;

import main.java.models.PurchaseList;
import main.java.models.Recommendation;
import main.java.models.User;
import main.java.models.policies.IRecommendationPolicy;

public class PurchaseFinished implements IPurchaseState{
    @Override
    public Recommendation getRecommendation(User user, PurchaseList purchaseList) throws Exception {
        throw new Exception("Purchase is finished. you can't get recommendation for a finished purchase");
    }

    @Override
    public void finishPurchase(PurchaseList purchaseList) throws Exception {
        if(purchaseList.getState().getClass() == PurchaseFinished.class){
            throw new Exception("Returning to same state");
        }
    }

    @Override
    public void restartPurchase(PurchaseList purchaseList) {
        purchaseList.setState(new PurchaseStarted());
    }
}
