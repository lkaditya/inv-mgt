package sg.edu.iss.test.model;

import java.beans.BeanProperty;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
public class RepairOrder {

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private long repairId;
@ManyToOne
private Customer customer;
@OneToMany(cascade=CascadeType.ALL, mappedBy="rep")
private List<ProductUsage> productUsageList;
@DateTimeFormat (pattern="yyyy-MM-dd")
@NotNull
private LocalDate repairDate;









}
