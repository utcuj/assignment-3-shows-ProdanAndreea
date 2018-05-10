package net.codejava.hibernate.EventVisualization.server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Data  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String methodName;
	
	/*
	public String id;
	public String name;
	public String type;
	public String description;
	public String release_date;
	public String imdb_no;
	public String imdb_s;*/
	
	public List<Object[]> data = new ArrayList<Object[]>();
	
	
	

}
