package de.szut.lf8_project.project;


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
    @PutMapping("/{id}")
    public ResponseEntity<GetProjectDto> updateProject(@PathVariable final Long id, @Valid @RequestBody final AddProjectDto dto) {
        ProjectEntity updatedProject = this.mappingService.mapAddProjectDtoToProject(dto);
        updatedProject.setId(id);
        updatedProject = this.service.update(updatedProject);
        GetProjectDto request = this.mappingService.mapProjectToGetProjectDto(updatedProject);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }
}

