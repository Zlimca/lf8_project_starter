package de.szut.lf8_project.mapping;

import de.szut.lf8_project.employee.dto.AddEmployeeDto;
import de.szut.lf8_project.employee.EmployeeEntity;
import de.szut.lf8_project.employee.dto.GetEmployeeDto;
import de.szut.lf8_project.project.AddProjectDto;
import de.szut.lf8_project.project.ProjectEntity;
import org.springframework.stereotype.Service;

@Service
public class MappingService {
    public EmployeeEntity mapAddEmployeeDtoToEmployee(AddEmployeeDto dto) {
        EmployeeEntity newEmployee = new EmployeeEntity();
        newEmployee.setFirstname(dto.getFirstname());
        newEmployee.setLastname(dto.getLastname());
        newEmployee.setStreet(dto.getStreet());
        newEmployee.setPostcode(dto.getPostcode());
        newEmployee.setCity(dto.getCity());
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
        dto.setProjects(employee.getProjects());
        return dto;
    }

    public ProjectEntity mapAddProjectDtoToProject(AddProjectDto dto){
        ProjectEntity newProject = new ProjectEntity();
        newProject.setDescription(dto.getDescription());
        newProject.setComment(dto.getComment());
        newProject.setStartDate(dto.getStartDate());
        newProject.setPlannedEndDate(dto.getPlannedEndDate());
        //tOdO Customer Information hinzufügen
        return newProject;
    }
}
