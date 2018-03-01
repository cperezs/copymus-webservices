package es.ua.dlsi.grfia.hispamus.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;

@Entity
@ApiModel
public class User {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String username;
    
    protected User() {}
    
    public User(String username) {
    	this.username = username;
    }
    
    public Long getId() {
    	return id;
    }
    
    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
    public String toString() {
        return String.format(
                "User[id=%d, username='%s']",
                id, username);
    }
}
