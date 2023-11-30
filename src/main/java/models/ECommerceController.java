package main.java.models;


public class ECommerceController {
    private RecommendationsCat recommendationsCat;
    private ItemCat itemCat;

    public ItemCat getItemCat() {
        return itemCat;
    }

    public void setItemCat(ItemCat itemCat) {
        this.itemCat = itemCat;
    }

    public RecommendationsCat getRecommendationsCat() {
        return recommendationsCat;
    }

    public void setRecommendationsCat(RecommendationsCat recommendationsCat) {
        this.recommendationsCat = recommendationsCat;
    }
}
