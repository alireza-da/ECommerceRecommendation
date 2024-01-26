package main.java.models;


import main.java.models.states.eccontroller.ECControllerState;
import main.java.models.states.eccontroller.Normal;
import main.java.models.states.eccontroller.Safe;

public class ECommerceController {
    private RecommendationsCat recommendationsCat;
    private ItemCat itemCat;
    private int ID;
    public ECControllerState state;
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    private static ECommerceController instance;

    public static ECommerceController getInstance() {
        if(instance == null){
            instance = new ECommerceController();
        }
        return instance;
    }

    public ECommerceController() {
        this.itemCat = ItemCat.getInstance();
        this.recommendationsCat = RecommendationsCat.getInstance();
        this.state = new Normal();
    }

    public ItemCat getItemCat() {
        return itemCat;
    }

    public void setItemCat(ItemCat itemCat) {
        this.itemCat = itemCat;
    }

    public RecommendationsCat getRecommendationsCat() {
        if(recommendationsCat == null){
            recommendationsCat = RecommendationsCat.getInstance();
        }
        return recommendationsCat;
    }

    public void setState(ECControllerState ecControllerState) {
        state = ecControllerState;
    }
}
