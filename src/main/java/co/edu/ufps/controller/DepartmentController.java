package co.edu.ufps.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ufps.entities.Department;
import co.edu.ufps.entities.Employee;
import co.edu.ufps.services.DepartmentService;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    // Buscar un departamento por ID
    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable int id) {
        Department department = departmentService.getDepartmentById(id);
        if (department != null) {
            return new ResponseEntity<>(department, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Agregar empleados a un departamento
    @PostMapping("/{departmentId}/add-employees")
    public ResponseEntity<Department> addEmployeesToDepartment(@PathVariable int departmentId, @RequestBody List<Integer> employeeIds) {
        Department updatedDepartment = departmentService.addEmployeesToDepartment(departmentId, employeeIds);
        return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
    }

    // Desasociar un empleado de un departamento
    @DeleteMapping("/{departmentId}/remove-employee/{employeeId}")
    public ResponseEntity<Employee> removeEmployeeFromDepartment(@PathVariable int departmentId, @PathVariable int employeeId) {
        Employee removedEmployee = departmentService.removeEmployeeFromDepartment(employeeId, departmentId);
        return new ResponseEntity<>(removedEmployee, HttpStatus.OK);
    }
}
