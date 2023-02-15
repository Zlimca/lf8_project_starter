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

    public void deleteById(Long id) {repository.deleteById(id);}

    public List<ProjectEntity> readAll(){
       return repository.findAll();
    }

    public ProjectEntity readById(long id){
        Optional<ProjectEntity> oProject = repository.findById(id);
        return oProject.orElse(null);
    }

    public List<ProjectEntity> findByEmployeeId(Long employeeId) {
        return repository.findByEmployeesId(employeeId);
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
        boolean removed = project.removeEmployee(employeeId);
        if (removed) {
            repository.save(project);
        }
        return removed;
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Project not found for id: " + id);
        }
        repository.deleteById(id);
    }
}
