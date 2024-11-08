package co.edu.ufps.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "project_assignment")
public class ProjectAssignment {
	
	@Column(name="id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "project_id")
	private int projectId;

	@ManyToOne
	@JoinColumn(name = "employee_id")
	 private Employee employee;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;
}
