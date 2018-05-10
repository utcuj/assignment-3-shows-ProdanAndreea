package net.codejava.hibernate.EventVisualization;

import java.util.List;

import javax.persistence.*;

import net.codejava.hibernate.EventVisualization.bridge_show.Show;


@Entity
@Table(name = "user")
public class User {
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name = "id_user")
	private int id_user;
	private String username;
	private String password;
	private boolean premium;
	@ManyToMany( fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }  )
    @JoinTable(
            name = "history",
            catalog = "event_visualization",
            joinColumns = {@JoinColumn(name = "id_user")},
            inverseJoinColumns = {@JoinColumn(name = "id_show")}
    )
	private List<Show> shows;
	
	
	public User() {
	}

	public User(String username, String password, boolean premium) {
		this.username = username;
		this.password = password;
		this.premium = premium;
	}
	
	public User(int id, String username, String password, boolean premium) {
		this.username = username;
		this.password = password;
		this.id_user = id;
		this.premium = premium;
	}
	
	
    public List<Show> getShows() {
        return shows;
    }
	public void setShows(List<Show> shows) {
	        this.shows = shows;
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
	
	public boolean getPremium() {
		return premium;
	}
	
	public void setPremium(boolean premium) {
		this.premium = premium;
	}

	
	
	
}
