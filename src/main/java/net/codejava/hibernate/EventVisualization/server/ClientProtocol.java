package net.codejava.hibernate.EventVisualization.server;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import net.codejava.hibernate.EventVisualization.HistoryHibernate;
import net.codejava.hibernate.EventVisualization.bridge_show.Show;
import net.codejava.hibernate.EventVisualization.bridge_show.ShowHibernate;

public class ClientProtocol {
    
    private ShowHibernate showHibernate = new ShowHibernate();
    private HistoryHibernate historyHibernate = new HistoryHibernate();
   /*
    * theInput = Object[ Object[Object] ]
    */ 
    public  Data processInput(Data theInput) {
    	Data theOutput = new Data();
    	int id_user;
    	int id_show;
        		
        String methodName = (String) theInput.methodName;
        
        switch (methodName) {
        	case "retriveAll" : theOutput.data = retrieveAll(); break;
        	case "search" : 
        		String name = (String) theInput.data.get(0)[0];
        		theOutput.data = search(name); 
        		break;
        	case "addShowToUser" :
        		id_user = (int) theInput.data.get(0)[0];
        		id_show = (int) theInput.data.get(1)[0];
        		addShowToUser(id_user, id_show);
        		break;
        	case "getShowsForUser" :
        		id_user = (int) theInput.data.get(0)[0];
        		theOutput.data = getShowsForUser(id_user);
        		break;
        	case "updateShow" : 
        		updateShow(theInput.data.get(0));
        	default: theOutput = null; break;
        }

        return theOutput;
    }
    
    private List<Object[]> retrieveAll() {
    	List<Show> shows = showHibernate.retriveAll();
    	List<Object[]> data = new ArrayList<Object[]>();
    	System.out.println("Protocol: retrieveAll ");
    	for(Show s: shows) {
    		Object[] o = {s.getId_show(), s.getName(), s.getType(), s.getDescription(), s.getRelease_date(), s.getImdb_no(), s.getImdb_s()};
    		data.add(o);
    		System.out.println(s.getName());
    	}
    	return data;
    }
    
    private List<Object[]> search(String name) {
    	List<Show> shows = showHibernate.search(name);
    	List<Object[]> data = new ArrayList<Object[]>();
    	System.out.println("Protocol: search ");
    	for(Show s: shows) {
    		Object[] o = {s.getId_show(), s.getName(), s.getType(), s.getDescription(), s.getRelease_date(), s.getImdb_no(), s.getImdb_s()};
    		data.add(o);
    		System.out.println(s.getName());
    	}
    	return data;
    }
    
    private void addShowToUser(int id_user, int id_show) {
    	historyHibernate.addShowToUser(id_user, id_show);
    }
    
    private List<Object[]> getShowsForUser(int id_user) {
    	List<Show> shows = historyHibernate.getShowsForUser(id_user);
    	List<Object[]> data = new ArrayList<Object[]>();
    	System.out.println("Protocol: getShowsForUser ");
    	for(Show s: shows) {
    		Object[] o = {s.getId_show(), s.getName(), s.getType(), s.getDescription(), s.getRelease_date(), s.getImdb_no(), s.getImdb_s()};
    		data.add(o);
    		System.out.println(s.getName());
    	}
    	return data;
    }
 
    private void updateShow(Object[] data) {
    	System.out.print("Protocol: updateShow");
    	int id_show = (int) data[0];
    	String name = (String) data[1];
		String description = (String) data[2];
		String type = (String) data[3];
		Date release_date = (Date) data[4];
		int imdb_no = Integer.parseInt((String) data[5]);
		float imdb_s = Float.parseFloat((String) data[6]);
		
    	Show show = new Show(id_show, name, type, description, release_date, imdb_no, imdb_s);
		
    	showHibernate.update(show);
    }
    
    
}
