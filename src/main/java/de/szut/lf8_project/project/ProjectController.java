package de.szut.lf8_project.project;


import de.szut.lf8_project.employee.dto.AddEmployeeDto;
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

    public ProjectController(ProjectService projectService, CustomerService customerService, EmployeeService employeeService, MappingService mappingService) {
        this.projectService = projectService;
        this.customerService = customerService;
        this.employeeService = employeeService;
        this.mappingService = mappingService;
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
        final var entity = this.service.readById(id);
        final GetProjectDto dto = this.mappingService.mapProjectToGetProjectDto(entity);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}

