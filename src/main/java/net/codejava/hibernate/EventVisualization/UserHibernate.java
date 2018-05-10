package net.codejava.hibernate.EventVisualization;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import net.codejava.hibernate.EventVisualization.bridge_show.Show;

public class UserHibernate {

    public void add(String username, String password, boolean premium) {
        Session session = ConnectionFactory.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            User user = new User(username, password, premium);
            session.save(user);
            transaction.commit();
            System.out.println("Records inserted sucessessfully");
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    public List<User> retriveAll() {
        Session session = ConnectionFactory.getSessionFactory().openSession();
        Transaction transaction = null;
        List<User> users = null;
        try {
            transaction = session.beginTransaction();
            users = session.createQuery("from User").list();
            /*
            for (Iterator iterator = users.iterator(); iterator.hasNext();) {
                User user = (User) iterator.next();
                System.out.println(user.getId() + "  " + user.getUsername() + "  " + user.getPassword());
            }*/
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace(); 
        } finally { 
            session.close(); 
        }
        return users;
    }
    
  
    public void delete(String username) { 
        Session session = ConnectionFactory.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            session.createNativeQuery("delete from User where username = :x")
            .setParameter("x", username)
            .executeUpdate();
            
            System.out.println("User records deleted!");
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close(); 
        }
    }
    
 
    public void update(int id, String username, String password, boolean premium) {
    	 
        Session session = ConnectionFactory.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
        	User user = new User(id, username, password, premium);
    		session.beginTransaction();
    		session.update(user);
    		session.getTransaction().commit();
    		session.close();

        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally { 
            session.close(); 
        }
    }
    

    //// for SignIn
    
    public List<Show> searchUser(String username, String password) {
        Session session = ConnectionFactory.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Show> shows = null;
        try {
            transaction = session.beginTransaction();
            Query q = session.createQuery("from User where username = :x and password = :y");
            q.setParameter("x", username);
            q.setParameter("y", password);
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
    
    public List<Show> searchAdmin(String username, String password) {
        Session session = ConnectionFactory.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Show> shows = null;
        try {
            transaction = session.beginTransaction();
            Query q = session.createQuery("from Admin where username = :x and password = :y");
            q.setParameter("x", username);
            q.setParameter("y", password);
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
    

    
    public static void main(String[] args) {
    	
    	UserHibernate cn = new UserHibernate();
    	//cn.add("er", "rrwerwose", true);
    	
    	//cn.retriveAll();
    	//cn.delete("er");
    	//cn.update(1, "as", "pass", true);
    }
}
