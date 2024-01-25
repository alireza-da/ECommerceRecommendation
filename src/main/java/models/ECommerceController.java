package main.java.models;


public class ECommerceController {
    private RecommendationsCat recommendationsCat;
    private ItemCat itemCat;
    private int ID;

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

}
