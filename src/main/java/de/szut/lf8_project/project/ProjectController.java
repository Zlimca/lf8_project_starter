package de.szut.lf8_project.project;


import de.szut.lf8_project.customer.CustomerEntity;
import de.szut.lf8_project.customer.CustomerService;
import de.szut.lf8_project.employee.EmployeeEntity;
import de.szut.lf8_project.employee.EmployeeRepository;
import de.szut.lf8_project.employee.EmployeeService;
import de.szut.lf8_project.exceptionHandling.ResourceNotFoundException;
import de.szut.lf8_project.project.dto.AddProjectDto;
import de.szut.lf8_project.project.dto.GetProjectDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;

    public ProjectController(ProjectService projectService, CustomerService customerService, EmployeeService employeeService, MappingService mappingService,
                             ProjectRepository projectRepository,
                             EmployeeRepository employeeRepository) {
        this.projectService = projectService;
        this.customerService = customerService;
        this.employeeService = employeeService;
        this.mappingService = mappingService;
        this.projectRepository = projectRepository;
        this.employeeRepository = employeeRepository;
    }

    @Operation(summary = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "created project", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AddProjectDto.class))}),
            @ApiResponse(responseCode = "400", description = "invalid JSON posted", content = @Content),
            @ApiResponse(responseCode = "401", description = "not authorized", content = @Content)
    })
    @PostMapping
    public ResponseEntity<GetProjectDto> createProject(@Valid @RequestBody final AddProjectDto dto) {
        System.getLogger("project_dto").log(System.Logger.Level.DEBUG, dto.toString());
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
    
    @GetMapping("/{id}")
    public ResponseEntity<GetProjectDto> getProjectById(@PathVariable final Long id) {
        final var entity = this.projectService.readById(id);
        final GetProjectDto dto = this.mappingService.mapProjectToGetProjectDto(entity);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
   
   @PutMapping("/{id}")
    public ResponseEntity<GetProjectDto> updateProject(@PathVariable final Long id, @Valid @RequestBody final AddProjectDto dto) {
        final CustomerEntity customer = this.customerService.readById(dto.getCustomerId());
        Set<EmployeeEntity> employees = new HashSet<>();
        dto.getEmployeeIds().forEach(eid -> employees.add(
                this.employeeService.readById(eid)
        ));
        ProjectEntity updatedProject = this.mappingService.mapAddProjectDtoToProject(dto, customer, employees);
        updatedProject.setId(id);
        updatedProject = this.projectService.update(updatedProject);
        GetProjectDto request = this.mappingService.mapProjectToGetProjectDto(updatedProject);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }

    @Operation(summary = "removes a employee by id from the project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "delete successful"),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "resource not found",
                    content = @Content)})
    @DeleteMapping("/{projectId}/{employeeId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteEmployeeFromProject(@PathVariable final Long projectId, @PathVariable final Long employeeId) {
        final ProjectEntity project = this.projectService.readById(projectId);
        final EmployeeEntity employee = this.employeeService.readById(employeeId);
        if (project == null) {
            throw new ResourceNotFoundException("ProjectEntity not found with id = " + projectId);
        }

        if (employee == null) {
            throw new ResourceNotFoundException("EmployeeEntity not found with id = " + employeeId);
        }

        if (!projectService.removeEmployee(project, employeeId)) {
            throw new ResourceNotFoundException("EmployeeEntity with id = " + employeeId + " not removed");
        }

        projectRepository.save(project);
        employeeRepository.save(employee);
    }
}

