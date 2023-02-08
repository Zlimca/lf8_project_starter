package de.szut.lf8_project.project;

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

    /*public ProjectEntity readById(long id){
        Optional<ProjectEntity> oProject = repository.findById(id);
        if (oProject.isEmpty()){
            throw new ResourceNotFoundException("Project ot found on id: " + id);
        }
        return oProject.get();
    }*/


}
