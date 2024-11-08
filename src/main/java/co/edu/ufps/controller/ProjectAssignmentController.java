package co.edu.ufps.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ufps.entities.ProjectAssignment;
import co.edu.ufps.services.ProjectAssignmentService;

@RestController
@RequestMapping("/projects")
public class ProjectAssignmentController {

    @Autowired
    private ProjectAssignmentService projectAssignmentService;

 // Agregar un empleado a un proyecto con un rol específico
    @PostMapping("/{projectId}/assign")
    public ResponseEntity<ProjectAssignment> assignEmployeeToProject(@PathVariable int projectId, @RequestBody Map<String, Integer> assignmentData) {
        // Obtener los IDs directamente del Map
        int employeeId = assignmentData.get("employeeId");
        int roleId = assignmentData.get("roleId");

        // Llamar al servicio para realizar la asignación
        ProjectAssignment assignment = projectAssignmentService.assignEmployeeToProject(employeeId, projectId, roleId);

        // Retornar la respuesta con el resultado
        return new ResponseEntity<>(assignment, HttpStatus.CREATED);
    }

    // Listar los empleados de un proyecto con su respectivo rol
    @GetMapping("/{projectId}/employees-with-roles")
    public ResponseEntity<List<Map<String, String>>> getEmployeesByProjectWithRoles(@PathVariable int projectId) {
        List<Map<String, String>> employeesWithRoles = projectAssignmentService.getEmployeesByProjectWithRoles(projectId);
        return new ResponseEntity<>(employeesWithRoles, HttpStatus.OK);
    }
}
