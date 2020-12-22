package sg.edu.iss.test.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import javax.validation.Valid;

import lombok.Data;
import lombok.NoArgsConstructor;
import sg.edu.iss.test.model.RoleType;

@Data
@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Size(min=5, max=50)
    private String userName;
    @Size(min=8, max=100)
    private String password;
    @Size(min=2, max=50)
    private String role;

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
	public User() {
		// TODO Auto-generated constructor stub
		super();
	}
	public void setUser(@Valid User user) {
		// TODO Auto-generated method stub
	}
	}