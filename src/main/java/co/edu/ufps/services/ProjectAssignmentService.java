package co.edu.ufps.services;

import co.edu.ufps.entities.ProjectAssignment;
import co.edu.ufps.entities.Employee;
import co.edu.ufps.entities.Role;
import co.edu.ufps.repositories.ProjectAssignmentRepository;
import co.edu.ufps.repositories.EmployeeRepository;
import co.edu.ufps.repositories.RoleRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectAssignmentService {

	@Autowired
	private ProjectAssignmentRepository projectAssignmentRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private RoleRepository roleRepository;

	// Agregar un empleado a un proyecto con un rol específico
	public ProjectAssignment assignEmployeeToProject(int employeeId, int projectId, int roleId) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new RuntimeException("Employee not found"));
		Role role = roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));

		ProjectAssignment projectAssignment = new ProjectAssignment();
		projectAssignment.setEmployee(employee);
		projectAssignment.setProjectId(projectId);
		projectAssignment.setRole(role);

		return projectAssignmentRepository.save(projectAssignment);
	}

	// Listar empleados de un proyecto con su respectivo rol
    public List<Map<String, String>> getEmployeesByProjectWithRoles(int projectId) {
        // Obtener las asignaciones de proyecto para el proyecto con el ID especificado
        List<ProjectAssignment> assignments = projectAssignmentRepository.findByProjectId(projectId);
        
        // Crear una lista de mapas que contienen la información del empleado y su rol
        return assignments.stream()
                .map(assignment -> {
                    Map<String, String> employeeRoleMap = new HashMap<>();
                    employeeRoleMap.put("employeeName", assignment.getEmployee().getFirstName() + " " + assignment.getEmployee().getLastName());
                    employeeRoleMap.put("roleName", assignment.getRole().getName());
                    return employeeRoleMap;
                })
                .collect(Collectors.toList());
    }
}
