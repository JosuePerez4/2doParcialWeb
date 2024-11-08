package co.edu.ufps.services;

import co.edu.ufps.entities.Employee;
import co.edu.ufps.entities.ProjectAssignment;
import co.edu.ufps.repositories.EmployeeRepository;
import co.edu.ufps.repositories.ProjectAssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;


	// Crear un empleado
	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public Optional<Employee> findEmployeeWithSalary(int id) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.getPosition().getSalary(); // Ensure salary is loaded
                    return employee;
                });
    }
}
