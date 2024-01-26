package main.java.models.states.eccontroller;

import main.java.models.ECommerceController;
import main.java.models.ItemCat;
import main.java.models.RecommendationsCat;

public class Safe implements ECControllerState{

    public Safe() {
        super();
    }


    public ItemCat getItemCat() {
        return null;
    }


    public void setItemCat(ItemCat itemCat) {

    }


    public RecommendationsCat getRecommendationsCat() {
        return null;
    }

    @Override
    public void onError(ECommerceController eCommerceController) {

    }

    @Override
    public void onRecovery(ECommerceController eCommerceController) {
        eCommerceController.setState(new Normal());
    }
}
