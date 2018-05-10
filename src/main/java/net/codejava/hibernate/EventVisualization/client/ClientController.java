package net.codejava.hibernate.EventVisualization.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import net.codejava.hibernate.EventVisualization.bridge_show.Show;
import net.codejava.hibernate.EventVisualization.server.ClientServer;
import net.codejava.hibernate.EventVisualization.server.Data;


/*
 * The controller which communicates with the user's GUI and its server
 */
// https://www.iplocation.net/find-private-ip-address
// http://www.coderpanda.com/java-socket-programming-transferring-of-java-objects-through-sockets/
public class ClientController {
	private ClientView clientView;
	
	private final String hostName = "192.168.0.220"; // "192.168.0.227"; //"10.128.55.213";
	private final int portNumber =  1342;
	private Socket kkSocket = null;
	private ObjectOutputStream out = null;
	private ObjectInputStream in = null;
	
	private int id_user = 2;
	private String username = "marvel";
	
	private ClientServer cs;
	
	/*
	public Client() {
		System.out.println("Client");
		connect();	
	}*/
	
	public ClientController(ClientView clientView) {
		System.out.println("Client");
		this.clientView = clientView;
		//cs = new ClientServer(portNumber); NO bc the server is the first one which must be started
	}

	public void connect() {	
		System.out.println("Client: try to connect");

		// connect to the server
		try {
			kkSocket = new Socket(hostName, portNumber);
			out = new ObjectOutputStream(kkSocket.getOutputStream());
			in = new ObjectInputStream(kkSocket.getInputStream());
			System.out.println("Client: connected");
		} catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
		 System.err.println("Client (connect): Couldn't get I/O for the connection to " + hostName);
         System.exit(1);
        } 	
		
	}
	
	public void btnDisplayAllClicked() {
		retrieveAll();
		displayHistory();
	}
	
	public void retrieveAll() {

		if (out == null) {
			connect();
		}
		
        try {
            Data fromServer = null;
            Data fromUser = new Data();
 
            // send the command with data to server
            fromUser.methodName = "retriveAll"; System.out.println("Client: write to server");
            out.writeObject(fromUser);
            
            // get data from server
            try {
				fromServer = (Data) in.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
            
            // {s.getId_show(), s.getName(), s.getType(), s.getDescription(), s.getRelease_date(), s.getImdb_no(), s.getImdb_s()}
            // tell and send to gui what to display from the data received from the server
            System.out.println("Client: send data to gui");
            List<Object[]> lo = new ArrayList<Object[]>();
	        for(Object[] ob : fromServer.data) {
	        	lo.add(new Object[]{ob[1], ob[2]});
		    	 System.out.println(ob[1] + " " + ob[2]);
	        }
	        
	        clientView.displayShows(lo);

			//closeServer();

        } catch (IOException e) {
            System.err.println("Client (retrieveAll): Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        } finally {
        	// tell the server to close
        	//closeServer();
        }
	}

	public List<Object[]> search(String name) {
		
		List<Object[]> data = new  ArrayList<Object[]>();
		
		if (out == null) {
			connect();
		}
		
        try {
            Data fromServer = null;
            Data fromUser = new Data();
 
            // send the command with data to server
            fromUser.methodName = "search"; 
            Object[] o = {name};
            fromUser.data.add(o); 
            
            System.out.println("Client: write to server");
            out.writeObject(fromUser);
            
            // get data from server
            try {
				fromServer = (Data) in.readObject();
				
				data = fromServer.data;
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

        } catch (IOException e) {
            System.err.println("Client (search): Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        } finally {
        }
        
        return data;
	}
	
	public void btnSearchClicked() {
		String name = clientView.getSearchData();	
		List<Object[]> data = search(name);
		
		// {s.getId_show(), s.getName(), s.getType(), s.getDescription(), s.getRelease_date(), s.getImdb_no(), s.getImdb_s()}
        // tell and send to gui what to display from the data received from the server
        System.out.println("Client: send data to gui");
        List<Object[]> data1 = new ArrayList<Object[]>();
        List<Object[]> data2 = new ArrayList<Object[]>();
        float imdb = 0;
        for(Object[] ob : data) {
        	 data1.add(new Object[]{ob[1], ob[2]});
        	 if ((int) ob[5] != 0) {
        		 imdb = (float) ob[6] / (int) ob[5];//Float.parseFloat(ob[6]);
        	 }
        	 data2.add(new Object[]{ob[3], ob[4], imdb});
	    	 System.out.println(ob[1] + " " + ob[2]);
        }
        
        clientView.displayShows(data1);
        clientView.displayDetails(data2);
	}
	
	public void RowDataSelected() {
		String name = clientView.getRowData();
		List<Object[]> data = search(name);
		
		// {s.getId_show(), s.getName(), s.getType(), s.getDescription(), s.getRelease_date(), s.getImdb_no(), s.getImdb_s()}
        // tell and send to gui what to display from the data received from the server
        System.out.println("Client: send data to gui");
        List<Object[]> data1 = new ArrayList<Object[]>();
        List<Object[]> data2 = new ArrayList<Object[]>();
        float imdb = 0;
        for(Object[] ob : data) {
        	 data1.add(new Object[]{ob[1], ob[2]});
        	 if ((int) ob[5] != 0) {
        		 imdb = (float) ob[6] / (int) ob[5];//Float.parseFloat(ob[6]);
        	 }
        	 data2.add(new Object[]{ob[3], ob[4], imdb});
	    	 System.out.println(ob[1] + " " + ob[2]);
        }
        
        clientView.displayShows(data1);
        clientView.displayDetails(data2);
	}
	
	public void btnAddToViewedClicked() {
		String name = clientView.getAddTextField();
		List<Object[]> data = search(name); // to get the id of the show

		int id_show = (int) data.get(0)[0];
		addHistory(id_show);
		
		displayHistory();
	}
	
	
	public void addHistory(int id_show) {
		
		if (out == null) {
			connect();
		}
		
        try {
            Data fromServer = null;
            Data fromUser = new Data();
 
            // send the command with data to server
            fromUser.methodName = "addShowToUser"; 
            Object[] o = {id_user};
            fromUser.data.add(o); 
            fromUser.data.add(new Object[]{id_show});
            
            System.out.println("Client: write to server");
            out.writeObject(fromUser);
            
            // get data from server
            try {
				fromServer = (Data) in.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
            
        } catch (IOException e) {
            System.err.println("Client (retrieveAll): Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        } finally {
        }
		
	}
	
	public void displayHistory() {

		if (out == null) {
			connect();
		}
		
        try {
            Data fromServer = null;
            Data fromUser = new Data();
 
            // send the command with data to server
            fromUser.methodName = "getShowsForUser"; 
            Object[] o = {id_user};
            fromUser.data.add(o); 
            
            System.out.println("Client: write to server");
            out.writeObject(fromUser);
            
            // get data from server
            try {
				fromServer = (Data) in.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
                  
         // {s.getId_show(), s.getName(), s.getType(), s.getDescription(), s.getRelease_date(), s.getImdb_no(), s.getImdb_s()}
         // tell and send to gui what to display from the data received from the server
            System.out.println("Client: send data to gui");
            List<Object[]> data = new ArrayList<Object[]>();
            for(Object[] ob : fromServer.data) {
            	 data.add(new Object[]{ob[1]}); // the name of the show
            }
            
            clientView.displayHistory(data);

        } catch (IOException e) {
            System.err.println("Client (retrieveAll): Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        } finally {
        }
	}
	
	public void closeServer() {
		System.out.println("Client: tell server to close");
		 try {
				out.writeObject(null);
				in.close();
		        out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
         System.out.println("Client: closed");    
	}
	
	
	public void btnRateClicked() {
		String name = clientView.getAddTextField();
		String rate = clientView.getRateTextField();
		
		List<Object[]> data = search(name); // to get the id of the show

		String imdb_no = Integer.toString((int) data.get(0)[5] + 1);
		String imdb_s = Float.toString((float) data.get(0)[6] + Float.parseFloat(rate));
		
		//float average = imdb_s/imdb_no;
		data.get(0)[5] = imdb_no;
		data.get(0)[6] = imdb_s;
		
		update(data);
		btnSearchClicked();
	}
	
	public void update(List<Object[]> data) {
		if (out == null) {
			connect();
		}
		
        try {
            Data fromServer = null;
            Data fromUser = new Data();
 
            // send the command with data to server
            fromUser.methodName = "updateShow"; 
            fromUser.data = data;
            
            System.out.println("Client: write to server");
            out.writeObject(fromUser);
            
            // get data from server
            try {
				fromServer = (Data) in.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
            
        } catch (IOException e) {
            System.err.println("Client (update): Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        } finally {
        }
		
	}
	
	public static void main(String[] args) {

		ClientView cv = new ClientView();
		cv.start();
		
		/*
		Client c = new Client();
		c.retrieveAll();
		System.out.println("SECOND!!!!!!!!");
		c.retrieveAll();
		
		c.closeServer();*/
		
	}
	
	
}
