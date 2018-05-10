package net.codejava.hibernate.EventVisualization.server;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import net.codejava.hibernate.EventVisualization.HistoryHibernate;
import net.codejava.hibernate.EventVisualization.User;
import net.codejava.hibernate.EventVisualization.UserHibernate;
import net.codejava.hibernate.EventVisualization.bridge_show.Movie;
import net.codejava.hibernate.EventVisualization.bridge_show.Show;
import net.codejava.hibernate.EventVisualization.bridge_show.ShowBridge;
import net.codejava.hibernate.EventVisualization.bridge_show.ShowHibernate;
import net.codejava.hibernate.EventVisualization.bridge_show.SportEvent;
import net.codejava.hibernate.EventVisualization.bridge_show.Theatre;

public class AdminProtocol {

	private ShowHibernate showHibernate = new ShowHibernate();
    private HistoryHibernate historyHibernate = new HistoryHibernate();
    private UserHibernate userHibernate = new UserHibernate();
   /*
    * theInput = Object[ Object[Object] ]
    */ 
    public  Data processInput(Data theInput) {
    	Data theOutput = new Data();
    	int id_user;
    	int id_show;
        		
        String methodName = (String) theInput.methodName;
        
        switch (methodName) {
        	case "updateShow" : 
        		updateShow(theInput.data.get(0)); 
        		break;
        	case "addShow" :
        		addShow(theInput.data.get(0));
        		break;
        	case "deleteShow" :
        		deleteShow(theInput.data.get(0));
        		break;
        	case "retriveAll" : theOutput.data = retrieveAll(); break;
        	case "addUser": addUser(theInput.data.get(0)); break;
        	case "retrieveAllUsers" : theOutput.data = retrieveAllUsers(); break;
        	case "updateUser": updateUser(theInput.data.get(0)); break;
        	case "deleteUser": deleteUser(theInput.data.get(0)); break;
        	default: theOutput = null; break;
        }

        return theOutput;
    }
    
    private void updateShow(Object[] data) {
    	System.out.print("Admin Protocol: updateShow");
    	int id = Integer.parseInt((String) data[0]);
    	String name = (String) data[1];
		String description = (String) data[2];
		String type = (String) data[4];
		java.sql.Date sqlDate = null;
    	try {
			java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse((String)data[3]);
			sqlDate = new java.sql.Date(utilDate.getTime()); 
    	} catch (ParseException e) {
			e.printStackTrace();
		}

		int imdb_no = Integer.parseInt((String) data[5]);
		float imdb_s = Float.parseFloat((String) data[6]);
		
    	Show show = new Show(id, name, type, description, sqlDate, imdb_no, imdb_s);

    	showHibernate.update(show);
    }
    

    private void addShow(Object[] data) {
    	System.out.print("Admin Protocol: addShow");
    	int id = Integer.parseInt((String) data[0]);
    	String name = (String) data[1];
		String description = (String) data[2];
		String type = (String) data[4];
		java.sql.Date sqlDate = null;
    	try {
			java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse((String)data[3]);
			sqlDate = new java.sql.Date(utilDate.getTime()); 
    	} catch (ParseException e) {
			e.printStackTrace();
		}

		int imdb_no = Integer.parseInt((String) data[5]);
		float imdb_s = Float.parseFloat((String) data[6]);
		
    	Show show = new Show(id, name, type, description, sqlDate, imdb_no, imdb_s);
    	
    	ShowBridge sb;
    	if (type.equals("movie")) {
    		 sb = new ShowBridge(new Movie());
    	} else if (type.equals("sport event")) {
   		 sb = new ShowBridge(new SportEvent());
    	} else sb = new ShowBridge(new Theatre());
		
    	showHibernate.add(show, sb);
    }
    
    private List<Object[]> retrieveAll() {
    	List<Show> shows = showHibernate.retriveAll();
    	List<Object[]> data = new ArrayList<Object[]>();
    	System.out.println("Protocol: retrieveAll ");
    	for(Show s: shows) {
    		Object[] o = {s.getId_show(), s.getName(), s.getDescription(), s.getRelease_date(),s.getType(), s.getImdb_no(), s.getImdb_s()};
    		data.add(o);
    		System.out.println(s.getName());
    	}
    	return data;
    }

    private void addUser(Object[] data) {
    	String username = (String) data[1];
    	String password = (String) data[2];
    	boolean premium;
    	if(((String) data[3]).equals("0")) {
    		premium = false;
    	} else premium = true;   	
    	
    	userHibernate.add(username, password, premium);
    }

    private List<Object[]> retrieveAllUsers() {
    	List<User> users = userHibernate.retriveAll();
    	List<Object[]> data = new ArrayList<Object[]>();
    	System.out.println("Protocol: retrieveAll ");
    	for(User u: users) {
    		Object[] o = {u.getId(), u.getUsername(), u.getPassword(), u.getPremium()};
    		data.add(o);
    	}
    	return data;
    }
    
    
    private void updateUser(Object[] data) {
    	int id = Integer.parseInt((String)data[0]);
    	String username = (String) data[1];
    	String password = (String) data[2];
    	boolean premium;
    	if(((String) data[3]).equals("0")) {
    		premium = false;
    	} else premium = true;   	   	
    	userHibernate.update(id, username, password, premium);
    }
    
    
    public void deleteUser(Object[] data) {  
    	String username = (String) data[1];
    	userHibernate.delete(username);
    }
    
    public void deleteShow(Object[] data) {
    	int id = Integer.parseInt((String)data[0]);  		   	
    	showHibernate.delete(id);
    }
    
    	
    
}
