package net.codejava.hibernate.EventVisualization.bridge_show;

import java.sql.Date;
import java.util.List;

import javax.persistence.*;

import net.codejava.hibernate.EventVisualization.User;

@Entity
@Table(name="showt")
public class Show {
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name = "id_show")
	private int id_show;
	private String name;
	private String description;
	//@Temporal(TemporalType.DATE)
	private Date release_date;
	private String type;
	private int imdb_no;
	private float imdb_s;
	@ManyToMany( fetch = FetchType.LAZY, mappedBy = "shows", cascade = CascadeType.ALL  )
	private List<User> users;
	
	public Show(){}
	
	public Show(String name, String description, Date release_date, int imdb_no, float imdb_s) {
		this.name = name;
		this.description = description;
		this.release_date = release_date;
		this.imdb_no = imdb_no;
		this.imdb_s = imdb_s;	
	}
	
	
	public Show(int id_show, String name, String type, String description, Date release_date, int imdb_no, float imdb_s) {
		this.id_show = id_show;
		this.name = name;
		this.type = type;
		this.description = description;
		this.release_date = release_date;
		this.imdb_no = imdb_no;
		this.imdb_s = imdb_s;	
	}
	
	public Show(String name, String type, String description, Date release_date, int imdb_no, float imdb_s) {
		this.name = name;
		this.type = type;
		this.description = description;
		this.release_date = release_date;
		this.imdb_no = imdb_no;
		this.imdb_s = imdb_s;	
	}
	/*
	int i = 0;
	@PreUpdate
    public void setLastUpdate() {
        i = 100;
    }*/
	
	
    public List<User> getUsers() {
        return users;
    }
    public void setTClasses(List<User> users) {
        this.users = users;
    }

	public int getId_show() {
		return id_show;
	}
	
	public void setId_show(int id_show) {
		this.id_show = id_show;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getRelease_date() {
		return release_date;
	}

	public void setRelease_date(Date release_date) {
		this.release_date = release_date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getImdb_no() {
		return imdb_no;
	}

	public void setImdb_no(int imdb_no) {
		this.imdb_no = imdb_no;
	}

	public float getImdb_s() {
		return imdb_s;
	}

	public void setImdb_s(float imdb_s) {
		this.imdb_s = imdb_s;
	}
	
	
	
	
}
