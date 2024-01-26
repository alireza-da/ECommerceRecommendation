package test;

import main.java.logger.Logger;
import main.java.models.User;
import main.java.models.database.annotations.UserMapper;
import main.java.models.database.utils.HibernateUtils;
import org.hibernate.Session;

import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;

public class TestHibernate {
    @Test
    public void save(){
        //Get Session

        Session session = HibernateUtils.getSessionFactory().getCurrentSession();

        //start transaction
        session.beginTransaction();


        User e1 = new User("John Doe");
        session.save(e1);
        //Commit transaction
        session.getTransaction().commit();
        System.out.println("User ID="+e1.getID());
        //terminate session factory, otherwise program won't end
        HibernateUtils.getSessionFactory().close();

    }
    @Test
    public void read(){
        // Open a session
        Session session = HibernateUtils.getSessionFactory().openSession();
        // Begin a transaction
        Transaction transaction = session.beginTransaction();
        // Retrieve the object using the primary key
        User u = (User) session.get(User.class, 1);
        Assert.assertEquals(u.getID(), 1);
        // Commit the transaction
        transaction.commit();
        // Close the session
        session.close();

    }
}
