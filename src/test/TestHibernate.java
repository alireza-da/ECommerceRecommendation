package test;

import main.java.models.User;
import main.java.models.database.utils.HibernateUtils;
import org.hibernate.Session;

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
}
