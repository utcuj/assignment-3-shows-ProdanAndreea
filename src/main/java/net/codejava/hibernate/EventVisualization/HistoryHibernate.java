package net.codejava.hibernate.EventVisualization;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import net.codejava.hibernate.EventVisualization.bridge_show.Show;

public class HistoryHibernate {
	
	
	public List<Show> getShowsForUser(int id) {
        Session session = ConnectionFactory.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
        	transaction = session.beginTransaction();
            Criteria c = session.createCriteria(Show.class)
                .addOrder(Order.asc("id_show"))
                .createCriteria("users", Criteria.LEFT_JOIN)
                .addOrder(Order.asc("id_user"))
                .add(Restrictions.eq("id_user", id))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            return c.list();
        } catch( RuntimeException e ) {
            transaction.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            session.close();
        }
    }
	
	
	public void addShowToUser(int id_user, int id_show) {
        Session session = ConnectionFactory.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            User tClass = (User)session.get(User.class, id_user);
            Show tStudent = (Show)session.get(Show.class, id_show);
            tClass.getShows().add( tStudent );
            session.save( tClass );
            transaction.commit();
        } catch( Exception e ) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

	

//http://outbottle.com/java-hibernate-manytomany-tutorial-with-add-and-delete-examples/
	public static void main(String[] args) {
		HistoryHibernate st = new HistoryHibernate();
		
		//st.addShowToUser(1,1);	
		
		st.addShowToUser(2, 3);
		
		List<Show> u = st.getShowsForUser(2);
		for(Show us: u) {
			System.out.println(us.getName());
		}
	}

}
