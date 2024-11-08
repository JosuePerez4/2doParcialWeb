package co.edu.ufps.services;

import co.edu.ufps.entities.Department;
import co.edu.ufps.entities.Employee;
import co.edu.ufps.repositories.DepartmentRepository;
import co.edu.ufps.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    // Buscar un departamento por ID
    public Department getDepartmentById(int id) {
        Optional<Department> department = departmentRepository.findById(id);
        return department.orElse(null);  // Devuelve null si no se encuentra el departamento
    }

    // Agregar empleados a un departamento
    public Department addEmployeesToDepartment(int departmentId, List<Integer> employeeIds) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        List<Employee> employees = employeeRepository.findAllById(employeeIds);
        department.getEmployees().addAll(employees);

        return departmentRepository.save(department);
    }

    // Desasociar un empleado de un departamento
    public Employee removeEmployeeFromDepartment(int employeeId, int departmentId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        department.getEmployees().remove(employee);
        departmentRepository.save(department);

        return employee;
    }
}
