package test;

import main.java.models.PurchaseList;
import main.java.models.Review;
import main.java.models.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class TestUser {
    @Test
    public void UserCreate(){
        User user = new User("John Doe");
        user.setReviews(new ArrayList<Review>());
        user.setPurchaseHistory(new ArrayList<PurchaseList>());
        Assert.assertEquals(user.getName(), "John Doe");
    }
}
