package co.edu.ufps.repositories;

import co.edu.ufps.entities.ProjectAssignment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectAssignmentRepository extends JpaRepository<ProjectAssignment, Integer> {
	List<ProjectAssignment> findByProjectId(int projectId);
}
