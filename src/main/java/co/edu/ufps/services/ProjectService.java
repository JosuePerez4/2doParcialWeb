package co.edu.ufps.services;

import co.edu.ufps.entities.Project;
import co.edu.ufps.entities.ProjectAssignment;
import co.edu.ufps.repositories.ProjectAssignmentRepository;
import co.edu.ufps.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private ProjectAssignmentRepository projectAssignmentRepository;

	public List<Project> getAllProjects() {
		return projectRepository.findAll();
	}

	public Project updateProject(int id, Project projectDetails) {
		return projectRepository.findById(id).map(project -> {
			project.setName(projectDetails.getName());
			project.setDescripcion(projectDetails.getDescripcion());
			project.setStartDate(projectDetails.getStartDate());
			project.setEndDate(projectDetails.getEndDate());
			return projectRepository.save(project);
		}).orElseThrow(() -> new RuntimeException("Project not found"));
	}

	public List<ProjectAssignment> listEmployeesWithRolesInProject(int projectId) {
		Project project = projectRepository.findById(projectId)
				.orElseThrow(() -> new RuntimeException("Project not found with id " + projectId));
		return project.getProjectAssignments();
	}
}
