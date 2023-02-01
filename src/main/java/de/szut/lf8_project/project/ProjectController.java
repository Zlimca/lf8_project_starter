package de.szut.lf8_project.project;


import de.szut.lf8_project.employee.dto.AddEmployeeDto;
import de.szut.lf8_project.project.dto.AddProjectDto;
import de.szut.lf8_project.project.dto.GetProjectDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import de.szut.lf8_project.mapping.MappingService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/swagger/project")
public class ProjectController {

    private final ProjectService service;
    private final MappingService mappingService;

    public ProjectController(ProjectService service, MappingService mappingService) {
        this.service = service;
        this.mappingService = mappingService;
    }

    @PostMapping
    public ResponseEntity<GetProjectDto> createProject(@Valid @RequestBody final AddProjectDto dto) {
        ProjectEntity newProject = this.mappingService.mapAddProjectDtoToProject(dto);
        newProject = this.service.create(newProject);
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

