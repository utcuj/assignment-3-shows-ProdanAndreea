package net.codejava.hibernate.EventVisualization.bridge_show;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import net.codejava.hibernate.EventVisualization.ConnectionFactory;

public class Theatre implements Implementor {
	
	public void add(Show show) { // without id
        Session session = ConnectionFactory.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            
            show.setType("theatre");
            
            session.save(show);
            transaction.commit();
            System.out.println("Records inserted sucessessfully");
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
   
}
