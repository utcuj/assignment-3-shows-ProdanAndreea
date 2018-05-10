package net.codejava.hibernate.EventVisualization.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class ClientServer {

	private int portNumber;
	
	public ClientServer(int portNumber) {
		this.portNumber = portNumber;	
		connect();
	}
	
	public void connect() {
		System.out.println("Server port " + portNumber + " try to connect");
		try ( 
			    ServerSocket serverSocket = new ServerSocket(portNumber); 
			    Socket clientSocket = serverSocket.accept();
				ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
		
				) {
			Data inputLine = null, outputLine = null;
		            
		    System.out.println("Server port " + portNumber + " connected");

		    ClientProtocol kkp = new ClientProtocol();  
		  	
		    // get the command and data from the client
		    inputLine = read(in);
		    
		    while (inputLine != null) {
		    
			    outputLine = kkp.processInput(inputLine);
			    
			     System.out.println("Server: write data to client");
			    out.writeObject(outputLine); 
			    
			 // get the next command and data from the client
			 inputLine = read(in);
			    
		    }
		    
		    // wait until the client has done the reading from the socket
		  //  System.out.println("Server: wait for client");
		  //  while((inputLine = read(in)) != null) { }
		    
		    in.close();
            out.close();
            serverSocket.close();
		        
	        System.out.println("Server closed");
	            
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		 System.out.println("Server port " + portNumber + " closed");
	}
	
	
	 private Data read(ObjectInputStream in) {
		Data inputLine = null;
		 try {
				inputLine = (Data) in.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		 return inputLine;
	}
	 
	 
	 
	 public static void main(String[] args) {
			
			ClientServer cs = new ClientServer(1342);
	}

}
