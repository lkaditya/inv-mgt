package sg.edu.iss.test.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class RepairOrder {

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private long repairId;
@ManyToOne
private Customer customer;
@OneToMany(mappedBy="rep")
private List<ProductUsage> productUsageList;
private LocalDate repairDate;









}
