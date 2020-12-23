package sg.edu.iss.test.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String userName;
    private String password;
    private String role;
    private String email;

    public User(String userName, String password) {
        super();
        this.userName = userName;
        this.password = password;
    }

    public User(String userName, String password, String role) {
        super();
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

	public void setUser(@Valid User user) {
		
	}

	public User(String userName, String password, String role, String email) {
		super();
		this.userName = userName;
		this.password = password;
		this.role = role;
		this.email = email;
	}


}