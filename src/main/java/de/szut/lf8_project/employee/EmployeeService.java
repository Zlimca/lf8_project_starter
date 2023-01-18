package de.szut.lf8_project.employee;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<EmployeeEntity> readAll() {
        return this.repository.findAll();
    }

    public EmployeeEntity readById(long id) {
        Optional<EmployeeEntity> optionalEmployee = this.repository.findById(id);
        return optionalEmployee.orElse(null);
    }

    public EmployeeEntity add(EmployeeEntity employee) {
        return this.repository.save(employee);
    }

    public EmployeeEntity update(EmployeeEntity employee) {
        EmployeeEntity updatedEmployee = readById(employee.getId());
        updatedEmployee.setFirstname(employee.getFirstname());
        updatedEmployee.setLastname(employee.getLastname());
        updatedEmployee.setStreet(employee.getStreet());
        updatedEmployee.setPostcode(employee.getPostcode());
        updatedEmployee.setCity(employee.getCity());
        updatedEmployee.setPhone(employee.getPhone());
        updatedEmployee = this.repository.save(updatedEmployee);
        return updatedEmployee;
    }
}
