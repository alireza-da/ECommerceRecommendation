package main.java.models;

import java.util.List;

public class RecommendationsCat {
    private static RecommendationsCat instance;
    private int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    public static RecommendationsCat getInstance() {
        if(instance == null){
            instance = new RecommendationsCat();
        }
        return instance;
    }

    private List<Recommendation> recommendations;

    public List<Recommendation> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<Recommendation> recommendations) {
        this.recommendations = recommendations;
    }
}
