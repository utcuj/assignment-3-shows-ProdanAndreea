package net.codejava.hibernate.EventVisualization;

import java.sql.Date;
import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name="history")
public class History {
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name = "id_user")
	private int id;
	private int id_user;
	private int id_show;
	private Date date;
	
	
	public History(){}
	
	public History(int id_user, int id_show) {
		this.id_user = id_user;
		this.id_show = id_show;
	}

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public int getId_show() {
		return id_show;
	}

	public void setId_show(int id_show) {
		this.id_show = id_show;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
