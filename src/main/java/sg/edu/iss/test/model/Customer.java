package sg.edu.iss.test.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Customer {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private long customerId;
private String name;
private int mobile;
@OneToMany(mappedBy="customer")
private List<RepairOrder>orders;

}