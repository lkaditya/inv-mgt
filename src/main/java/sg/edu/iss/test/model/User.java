package sg.edu.iss.test.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    private String userName;
    private String password;
    private RoleType role;

    public User(String userName, String password) {
        super();
        this.userName = userName;
        this.password = password;
    }

    public User(String userName, String password, RoleType role) {
        super();
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    //hz test 2

}