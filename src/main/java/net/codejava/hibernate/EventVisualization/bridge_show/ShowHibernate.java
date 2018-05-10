package net.codejava.hibernate.EventVisualization.bridge_show;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import net.codejava.hibernate.EventVisualization.ConnectionFactory;
import net.codejava.hibernate.EventVisualization.User;

public class ShowHibernate {
	
	public ShowHibernate() {
	}
	
	public void add(Show show, ShowBridge sb) { // without id
		sb.add(show);
    }
    
    public List<Show> retriveAll() {
        Session session = ConnectionFactory.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Show> shows = null;
        try {
            transaction = session.beginTransaction();
            shows = session.createQuery("from Show").list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace(); 
        } finally { 
            session.close(); 
        }
        return shows;
    }
    
    public List<Show> search(String name) {
        Session session = ConnectionFactory.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Show> shows = null;
        try {
            transaction = session.beginTransaction();
            Query q = session.createQuery("from Show where name = :x");
            q.setParameter("x", name);
            shows = q.list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace(); 
        } finally { 
            session.close(); 
        }
        return shows;
    }
    
    
    ////
    public void delete(int id) {
    	 Session session = ConnectionFactory.getSessionFactory().openSession();
         Transaction transaction = null;
         try {
             transaction = session.beginTransaction();

             session.createNativeQuery("delete from Show where id_show = :x")
             .setParameter("x", id)
             .executeUpdate();
             
             System.out.println("Show records deleted!");
         } catch (HibernateException e) {
             transaction.rollback();
             e.printStackTrace();
         } finally {
             session.close(); 
         }
    }
 
    public void update(Show show) {
    	 
        Session session = ConnectionFactory.getSessionFactory().openSession();
        Transaction transaction = null;
        try {       
    		session.beginTransaction();
    		session.update(show);
    		session.getTransaction().commit();
    		session.close();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally { 
            session.close(); 
        }
    }
    
    
    public static void main(String[] args) {
    	
    	/* working
    	
    	String lastCrawlDate = "2018-02-15";
    	try {
			Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(lastCrawlDate);
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime()); 
			
			Show s = new Show("Queen", "lasts 2 hours",sqlDate, 9, 6);
			ShowBridge sb = new ShowBridge(new Theatre());
			sh.add(s, sb);
			
			
			
			
		} catch (ParseException e) {
			
			e.printStackTrace();
		}*/
    	
    	
    	    }


}
