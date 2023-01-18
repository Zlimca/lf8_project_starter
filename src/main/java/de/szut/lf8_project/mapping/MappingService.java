package de.szut.lf8_project.mapping;

import de.szut.lf8_project.employee.AddEmployeeDto;
import de.szut.lf8_project.employee.EmployeeEntity;
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
