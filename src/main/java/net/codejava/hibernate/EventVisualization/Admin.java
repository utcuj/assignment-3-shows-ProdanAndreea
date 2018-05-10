package net.codejava.hibernate.EventVisualization;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin {
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name = "id_user")
	private int id_user;
	private String username;
	private String password;
	
	public Admin(){}
	
	
	public Admin(int id_user, String username, String password) {
		this.username = username;
		this.password = password;
		this.id_user = id_user;
	}

	public int getId() {
		return id_user;
	}
	
	public void setId(int id_user) {
		this.id_user = id_user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}
