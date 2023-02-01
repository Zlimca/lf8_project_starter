package de.szut.lf8_project.mapping;

import de.szut.lf8_project.customer.CustomerEntity;
import de.szut.lf8_project.customer.dto.AddCustomerDto;
import de.szut.lf8_project.customer.dto.GetCustomerDto;
import de.szut.lf8_project.employee.dto.AddEmployeeDto;
import de.szut.lf8_project.employee.EmployeeEntity;
import de.szut.lf8_project.employee.dto.GetEmployeeDto;
import de.szut.lf8_project.project.dto.AddProjectDto;
import de.szut.lf8_project.project.dto.GetProjectDto;
import de.szut.lf8_project.project.ProjectEntity;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MappingService {
    public EmployeeEntity mapAddEmployeeDtoToEmployee(AddEmployeeDto dto) {
        EmployeeEntity newEmployee = new EmployeeEntity();
        newEmployee.setFirstname(dto.getFirstname());
        newEmployee.setLastname(dto.getLastname());
        newEmployee.setStreet(dto.getStreet());
        newEmployee.setPostcode(dto.getPostcode());
        newEmployee.setCity(dto.getCity());
        newEmployee.setPhone(dto.getPhone());
        return newEmployee;
    }

    public GetEmployeeDto mapEmployeeToGetEmployeeDto(EmployeeEntity employee) {
        GetEmployeeDto dto = new GetEmployeeDto();
        dto.setId(employee.getId());
        dto.setFirstname(employee.getFirstname());
        dto.setLastname(employee.getLastname());
        dto.setStreet(employee.getStreet());
        dto.setPostcode(employee.getPostcode());
        dto.setCity(employee.getCity());
        dto.setPhone(employee.getPhone());
        dto.setProjects(employee.getProjects());
        return dto;
    }

    public CustomerEntity mapAddCustomerDtoToCustomer(AddCustomerDto dto) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setFirstname(dto.getFirstname());
        customerEntity.setLastname(dto.getLastname());
        customerEntity.setStreet(dto.getStreet());
        customerEntity.setPostcode(dto.getPostcode());
        customerEntity.setCity(dto.getCity());
        customerEntity.setPhone(dto.getPhone());
        return customerEntity;
    }

    public GetCustomerDto mapCustomerToGetCustomerDto(CustomerEntity customer) {
        GetCustomerDto dto = new GetCustomerDto();
        dto.setId(customer.getId());
        dto.setFirstname(customer.getFirstname());
        dto.setLastname(customer.getLastname());
        dto.setStreet(customer.getStreet());
        dto.setPostcode(customer.getPostcode());
        dto.setCity(customer.getCity());
        dto.setPhone(customer.getPhone());
        dto.setProjects(customer.getProjects());
        return dto;
    }

    public ProjectEntity mapAddProjectDtoToProject(AddProjectDto dto, CustomerEntity customer, Set<EmployeeEntity> employees) {
        ProjectEntity newProject = new ProjectEntity();
        newProject.setDescription(dto.getDescription());
        newProject.setComment(dto.getComment());
        newProject.setStartDate(dto.getStartDate());
        newProject.setPlannedEndDate(dto.getPlannedEndDate());
        newProject.setCustomer(customer);
        newProject.setEmployees(employees);
        return newProject;
    }

    public GetProjectDto mapProjectToGetProjectDto(ProjectEntity project) {
        GetProjectDto dto = new GetProjectDto();
        dto.setId(project.getId());
        dto.setComment(project.getComment());
        dto.setDescription(project.getDescription());
        dto.setStartDate(project.getStartDate());
        dto.setPlannedEndDate(project.getPlannedEndDate());
        dto.setActualEndDate(project.getActualEndDate());
        dto.setCustomerId(project.getCustomer().getId());
        project.getEmployees().forEach(employee -> dto.getEmployeeIds().add(employee.getId()));
        return dto;
    }
}
