package net.codejava.hibernate.EventVisualization;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;

import com.fasterxml.classmate.AnnotationConfiguration;

public class AdminHibernate {
	
	 public List<Admin> retriveAll() {
	        Session session = ConnectionFactory.getSessionFactory().openSession();
	        Transaction transaction = null;
	        List<Admin> users = null;
	        try {
	            transaction = session.beginTransaction();
	            users = session.createQuery("from Admin").list();
	            
	            for (Iterator iterator = users.iterator(); iterator.hasNext();) {
	            	Admin user = (Admin) iterator.next();
	                System.out.println(user.getId() + "  " + user.getUsername() + "  " + user.getPassword());
	            }
	            transaction.commit();
	        } catch (HibernateException e) {
	            transaction.rollback();
	            e.printStackTrace(); 
	        } finally { 
	            session.close(); 
	        }
	        return users;
	    }
	 
	 
	 public static Admin retrieve(String username, String password) {
		 Session session = ConnectionFactory.getSessionFactory().openSession();
	        Transaction transaction = null;
	        try {
	        	  transaction = session.beginTransaction();
				 // AnnotationConfiguration config = new AnnotationConfiguration();
				 // config.addAnnotatedClass(User.class);
				  //SessionFactory factory= config.configure().buildSessionFactory();
				 // Session session = factory.getCurrentSession();
				 // session.beginTransaction();/*lets hope an id of 1 exists!*/
				  String queryString = "from Admin where username = :x and password = :y";
				  Query query = session.createQuery(queryString);
				  query.setString("x", username);
				  query.setString("y", password);
				  Object queryResult = query.uniqueResult();
				  Admin user = (Admin)queryResult;
				  session.getTransaction().commit();
				  return user;
	        } catch (HibernateException e) {
	            transaction.rollback();
	            e.printStackTrace(); 
	        } finally { 
	            session.close(); 
	        }
	        return null;
		 }
	 
	 public static void main(String[] args) {
	    	
		 AdminHibernate cn = new AdminHibernate();
		 //cn.retriveAll();
		Admin a =  cn.retrieve("tom", "tom");
		if (a != null) {
			System.out.println(a.getUsername());
		}else System.out.println("Doesnsd");
		
		a =  cn.retrieve("tom", "tosm");
		if (a != null) {
			System.out.println(a.getUsername());
		}else System.out.println("Doesnsd");
	 }
}
