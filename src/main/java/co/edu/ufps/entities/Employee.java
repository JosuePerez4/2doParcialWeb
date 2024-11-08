package co.edu.ufps.entities;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "first_name", length = 50)
	private String firstName;

	@Column(name = "last_name", length = 50)
	private String lastName;

	@Column(name = "birthdate")
	private Date birthdate;

	@Column(name = "entry_date")
	private Date entryDate;

	@ManyToOne
	@JoinColumn(name = "dep_id")
	private Department department;

	@ManyToOne
	@JoinColumn(name = "pos_id")
	private Position position;

	@OneToMany(mappedBy = "employee")
	@JsonIgnore
	private List<ProjectAssignment> projectAssignments;

	@OneToMany(mappedBy = "chief")
	@JsonIgnore
	private List<Department> managedDepartments;

	@ManyToMany
	@JsonIgnore
	private List<Department> departments;

	@OneToMany(mappedBy = "employee")
	@JsonIgnore
	private List<Visit> visits;
}
