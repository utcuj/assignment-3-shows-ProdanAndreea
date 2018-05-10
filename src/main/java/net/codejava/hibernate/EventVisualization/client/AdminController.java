package net.codejava.hibernate.EventVisualization.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import net.codejava.hibernate.EventVisualization.server.Data;

public class AdminController {
	
	private AdminView adminView;
	private final String hostName = "192.168.0.220"; // "192.168.0.227"; //"10.128.55.213";
	private final int portNumber =  1343;
	private Socket kkSocket = null;
	private ObjectOutputStream out = null;
	private ObjectInputStream in = null;
	
	private int id_user = 2;
	private String username = "tom";
	
	
	public AdminController(AdminView adminView) {
		this.adminView = adminView;
	}

	public void connect() {	
		System.out.println("Admin: try to connect");

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
	
	
	public void RowDataSelected() {
		adminView.displayData();
	}
	
	public void btnUpdateClicked() {
		Object[] data = adminView.getData();
		
		if (out == null) {
			connect();
		}
		
        try {
            Data fromServer = null;
            Data fromUser = new Data();
 
            // send the command with data to server
            fromUser.methodName = "updateShow";  
            fromUser.data.add(data);
            
            System.out.println("Admin: write to server");
            out.writeObject(fromUser);
            
            // get data from server
            try {
				fromServer = (Data) in.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
            
        } catch (IOException e) {
            System.err.println("Admin (btnAddClicked): Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        } finally {
        }
	}
	
	public void btnAddClicked() {
		Object[] data = adminView.getData();
		
		if (out == null) {
			connect();
		}
		
        try {
            Data fromServer = null;
            Data fromUser = new Data();
 
            // send the command with data to server
            fromUser.methodName = "addShow";  
            fromUser.data.add(data);
            
            System.out.println("Admin: write to server");
            out.writeObject(fromUser);
            
            // get data from server
            try {
				fromServer = (Data) in.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
            
        } catch (IOException e) {
            System.err.println("Admin (btnAddClicked): Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        } finally {
        }
	}
	
	public void btnAddUserClicked() {
		Object[] data = adminView.getUserData();
		
		if (out == null) {
			connect();
		}
		
        try {
            Data fromServer = null;
            Data fromUser = new Data();
 
            // send the command with data to server
            fromUser.methodName = "addUser";  
            fromUser.data.add(data);
            
            System.out.println("Admin: write to server");
            out.writeObject(fromUser);
            
            // get data from server
            try {
				fromServer = (Data) in.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
            
        } catch (IOException e) {
            System.err.println("Admin (btnAddUserClicked): Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        } finally {
        }
	}
	
	public void btnDisplayAllClicked() {
		displayAll();
		displayAllUsers();
	}
	
	public void displayAll() {
		if (out == null) {
			connect();
		}
		
        try {
            Data fromServer = null;
            Data fromUser = new Data();
 
            // send the command with data to server
            fromUser.methodName = "retriveAll"; System.out.println("Admin: write to server");
            out.writeObject(fromUser);
            
            // get data from server
            try {
				fromServer = (Data) in.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
            
            // {s.getId_show(), s.getName(), s.getType(), s.getDescription(), s.getRelease_date(), s.getImdb_no(), s.getImdb_s()}
            // tell and send to gui what to display from the data received from the server
            System.out.println("Admin: send data to gui");        
	        adminView.displayShows(fromServer.data);

			//closeServer();

        } catch (IOException e) {
            System.err.println("Client (retrieveAll): Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        } finally {
        	// tell the server to close
        	//closeServer();
        }
	}
	
	public void displayAllUsers() {
		if (out == null) {
			connect();
		}
		
        try {
            Data fromServer = null;
            Data fromUser = new Data();
 
            // send the command with data to server
            fromUser.methodName = "retrieveAllUsers"; System.out.println("Admin: write to server");
            out.writeObject(fromUser); 
            
            // get data from server
            try {
				fromServer = (Data) in.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
            
            // {s.getId_show(), s.getName(), s.getType(), s.getDescription(), s.getRelease_date(), s.getImdb_no(), s.getImdb_s()}
            // tell and send to gui what to display from the data received from the server
            System.out.println("Admin: send data to gui");        
	        adminView.displayUsers(fromServer.data);

			//closeServer();

        } catch (IOException e) {
            System.err.println("Client (retrieveAll): Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        } finally {
        	// tell the server to close
        	//closeServer();
        }
	}

	public void btnDeleteClicked() {
		Object[] data = adminView.getData(); 

		if (out == null) {
			connect();
		}
		
        try {
            Data fromServer = null;
            Data fromUser = new Data();
 
            // send the command with data to server
            fromUser.methodName = "deleteShow";  
            fromUser.data.add(data);
            
            System.out.println("Admin: write to server");
            out.writeObject(fromUser);
            
            // get data from server
            try {
				fromServer = (Data) in.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
            
        } catch (IOException e) {
            System.err.println("Admin (btnDeleteClicked): Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        } finally {
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
            
            out.writeObject(fromUser);
            
            // get data from server
            try {
				fromServer = (Data) in.readObject();
				
				data = fromServer.data;
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

        } catch (IOException e) {
            System.err.println("Admin (search): Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        } finally {
        }
        
        return data;
	}
	
	public void btnDeleteUserClicked() {
		Object[] data = adminView.getUserData();
		
		if (out == null) {
			connect();
		}
		
        try {
            Data fromServer = null;
            Data fromUser = new Data();
 
            // send the command with data to server
            fromUser.methodName = "deleteUser";  
            fromUser.data.add(data);
            
            System.out.println("Admin: write to server");
            out.writeObject(fromUser);
            
            // get data from server
            try {
				fromServer = (Data) in.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
            
        } catch (IOException e) {
            System.err.println("Admin (btnDeleteUserClicked): Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        } finally {
        }
	}
		
	public void btnUpdateUserClicked() {
		Object[] data = adminView.getUserData();
		
		if (out == null) {
			connect();
		}
		
        try {
            Data fromServer = null;
            Data fromUser = new Data();
 
            // send the command with data to server
            fromUser.methodName = "updateUser";  
            fromUser.data.add(data);
            
            System.out.println("Admin: write to server");
            out.writeObject(fromUser);
            
            // get data from server
            try {
				fromServer = (Data) in.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
            
        } catch (IOException e) {
            System.err.println("Admin (updateUser): Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        } finally {
        }
	}
	
	public void UserRowDataSelected() {
		adminView.displayUserData();
	}
	

	public void closeServer() {
		System.out.println("Admin: tell server to close");
		 try {
				out.writeObject(null);
				in.close();
		        out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
         System.out.println("Admin: closed");    
	}
	
	
	

	public static void main(String[] args) {

		AdminView av = new AdminView();
		av.start();
		
		/*
		Client c = new Client();
		c.retrieveAll();
		System.out.println("SECOND!!!!!!!!");
		c.retrieveAll();
		
		c.closeServer();*/
		
	}
	
}
