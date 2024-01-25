package main.java.models.states.purchase;

import main.java.models.PurchaseList;
import main.java.models.Recommendation;
import main.java.models.User;
import main.java.models.factory.Factory;
import main.java.models.policies.IRecommendationPolicy;

public class PurchaseStarted implements IPurchaseState{
    @Override
    public Recommendation getRecommendation(User user, PurchaseList purchaseList) throws Exception {
        IRecommendationPolicy policy = Factory.getInstance().getRecommendationPolicy();
        return policy.getRecommendation(user.getPurchaseHistory());
    }

    @Override
    public void finishPurchase(PurchaseList purchaseList) {
        purchaseList.setState(new PurchaseFinished());
    }

    @Override
    public void restartPurchase(PurchaseList purchaseList) throws Exception {
        if (purchaseList.getState().getClass() == PurchaseStarted.class){
            throw new Exception("Returning to same state");
        }
    }
}
