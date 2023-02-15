package de.szut.lf8_project.project;

import de.szut.lf8_project.employee.EmployeeEntity;
import de.szut.lf8_project.exceptionHandling.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository repository;

    public ProjectService(ProjectRepository repository){this.repository = repository;}

    public ProjectEntity create(ProjectEntity newProject){return repository.save(newProject);}

    public List<ProjectEntity> readAll(){
       List<ProjectEntity> listProject = repository.findAll();
       return listProject;
    }

    public ProjectEntity readById(long id){
        Optional<ProjectEntity> oProject = repository.findById(id);
        return oProject.orElse(null);
    }

    public ProjectEntity update(ProjectEntity project) {
        ProjectEntity updatedProject = readById(project.getId());
        updatedProject.setDescription(project.getDescription());
        updatedProject.setComment(project.getComment());
        updatedProject.setStartDate(project.getStartDate());
        updatedProject.setPlannedEndDate(project.getPlannedEndDate());
        updatedProject.setActualEndDate(project.getActualEndDate());
        updatedProject.setCustomer(project.getCustomer());
        updatedProject.setEmployees(project.getEmployees());
        updatedProject = this.repository.save(updatedProject);
        return updatedProject;
    }

    public boolean removeEmployee(ProjectEntity project, Long employeeId) {
        return project.removeEmployee(employeeId);
    }
}
