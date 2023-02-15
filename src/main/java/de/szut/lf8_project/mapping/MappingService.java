package de.szut.lf8_project.mapping;

import de.szut.lf8_project.customer.CustomerEntity;
import de.szut.lf8_project.customer.dto.AddCustomerDto;
import de.szut.lf8_project.customer.dto.GetCustomerDto;
import de.szut.lf8_project.customer.dto.GetCustomerShortDto;
import de.szut.lf8_project.employee.EmployeeEntity;
import de.szut.lf8_project.employee.dto.AddEmployeeDto;
import de.szut.lf8_project.employee.dto.GetEmployeeDto;
import de.szut.lf8_project.employee.dto.GetEmployeeShortDto;
import de.szut.lf8_project.project.ProjectEntity;
import de.szut.lf8_project.project.dto.AddProjectDto;
import de.szut.lf8_project.project.dto.GetProjectCustomerDto;
import de.szut.lf8_project.project.dto.GetProjectDto;
import de.szut.lf8_project.project.dto.GetProjectEmployeeDto;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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

    public GetEmployeeShortDto mapEmployeeToGetEmployeeShortDto(EmployeeEntity employee) {
        GetEmployeeShortDto dto = new GetEmployeeShortDto();
        dto.setId(employee.getId());
        dto.setFirstname(employee.getFirstname());
        dto.setLastname(employee.getLastname());
        dto.setStreet(employee.getStreet());
        dto.setPostcode(employee.getPostcode());
        dto.setCity(employee.getCity());
        dto.setPhone(employee.getPhone());

        return dto;
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

        Set<GetProjectEmployeeDto> allProjects = new HashSet<>();
        for(ProjectEntity project : employee.getProjects()){
            GetProjectEmployeeDto pdto = new GetProjectEmployeeDto();
            pdto.setId(project.getId());
            pdto.setComment(project.getComment());
            pdto.setDescription(project.getDescription());

            CustomerEntity customer = project.getCustomer();
            GetCustomerShortDto cdto = new GetCustomerShortDto();
            cdto.setId(customer.getId());
            cdto.setFirstname(customer.getFirstname());
            cdto.setLastname(customer.getLastname());
            cdto.setPostcode(customer.getPostcode());
            cdto.setCity(customer.getCity());
            cdto.setStreet(customer.getStreet());
            cdto.setPhone(customer.getPhone());
            pdto.setCustomer(cdto);

            pdto.setStartDate(project.getStartDate());
            pdto.setPlannedEndDate(project.getPlannedEndDate());
            pdto.setActualEndDate(project.getActualEndDate());

            allProjects.add(pdto);
        }
        dto.setProjects(allProjects);
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

    public GetCustomerDto mapCustomerToGetCustomerShortDto(CustomerEntity customer) {
        GetCustomerDto dto = new GetCustomerDto();
        dto.setId(customer.getId());
        dto.setFirstname(customer.getFirstname());
        dto.setLastname(customer.getLastname());
        dto.setStreet(customer.getStreet());
        dto.setPostcode(customer.getPostcode());
        dto.setCity(customer.getCity());
        dto.setPhone(customer.getPhone());

        return dto;
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
        Set<GetProjectCustomerDto> allProjects = new HashSet<>();
        for (ProjectEntity project : customer.getProjects()){
            GetProjectCustomerDto pdto = new GetProjectCustomerDto();
            pdto.setId(project.getId());
            pdto.setComment(project.getComment());
            pdto.setDescription(project.getDescription());
            pdto.setPlannedEndDate(project.getPlannedEndDate());
            pdto.setStartDate(project.getStartDate());
            pdto.setActualEndDate(project.getActualEndDate());

            Set<GetEmployeeShortDto> allEmployees = new HashSet<>();
            for(EmployeeEntity employee : project.getEmployees()) {
                GetEmployeeShortDto edto = new GetEmployeeShortDto();
                edto.setId(employee.getId());
                edto.setFirstname(employee.getFirstname());
                edto.setLastname(employee.getLastname());
                edto.setCity(employee.getCity());
                edto.setPostcode(employee.getPostcode());
                edto.setStreet(employee.getStreet());
                edto.setPhone(employee.getPhone());
                allEmployees.add(edto);
            }
            pdto.setEmployees(allEmployees);
            allProjects.add(pdto);
        }
        dto.setProjects(allProjects);
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
        Set<GetEmployeeShortDto> allEmployees = new HashSet<>();
        for(EmployeeEntity employee : project.getEmployees()){
            GetEmployeeShortDto edto = new GetEmployeeShortDto();
            edto.setId(employee.getId());
            edto.setFirstname(employee.getFirstname());
            edto.setLastname(employee.getLastname());
            edto.setCity(employee.getCity());
            edto.setPostcode(employee.getPostcode());
            edto.setStreet(employee.getStreet());
            edto.setPhone(employee.getPhone());
            allEmployees.add(edto);
        }
        dto.setEmployees(allEmployees);
        CustomerEntity customer = project.getCustomer();
        GetCustomerShortDto cdto = new GetCustomerShortDto();
        cdto.setId(customer.getId());
        cdto.setFirstname(customer.getFirstname());
        cdto.setLastname(customer.getLastname());
        cdto.setPostcode(customer.getPostcode());
        cdto.setCity(customer.getCity());
        cdto.setStreet(customer.getStreet());
        cdto.setPhone(customer.getPhone());

        dto.setCustomer(cdto);

        return dto;
    }
}
