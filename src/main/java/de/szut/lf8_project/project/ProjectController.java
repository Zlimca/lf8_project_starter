package de.szut.lf8_project.project;


import de.szut.lf8_project.customer.CustomerEntity;
import de.szut.lf8_project.customer.CustomerService;
import de.szut.lf8_project.employee.EmployeeEntity;
import de.szut.lf8_project.employee.EmployeeService;
import de.szut.lf8_project.project.dto.AddProjectDto;
import de.szut.lf8_project.project.dto.GetProjectDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import de.szut.lf8_project.mapping.MappingService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/swagger/project")
public class ProjectController {

    private final ProjectService projectService;
    private final CustomerService customerService;
    private final EmployeeService employeeService;
    private final MappingService mappingService;

    public ProjectController(ProjectService projectService, CustomerService customerService, EmployeeService employeeService, MappingService mappingService) {
        this.projectService = projectService;
        this.customerService = customerService;
        this.employeeService = employeeService;
        this.mappingService = mappingService;
    }

    @PostMapping
    public ResponseEntity<GetProjectDto> createProject(@Valid @RequestBody final AddProjectDto dto) {
        final CustomerEntity customer = this.customerService.readById(dto.getCustomerId());
        Set<EmployeeEntity> employees = new HashSet<>();
        dto.getEmployeeIds().forEach(id -> employees.add(
                this.employeeService.readById(id)
        ));
        ProjectEntity newProject = this.mappingService.mapAddProjectDtoToProject(dto, customer, employees);
        newProject = this.projectService.create(newProject);
        final GetProjectDto request = this.mappingService.mapProjectToGetProjectDto(newProject);
        return new ResponseEntity<>(request, HttpStatus.CREATED);
    }
}

