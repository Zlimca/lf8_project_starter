package de.szut.lf8_project.mapping;

import com.sun.xml.bind.v2.TODO;
import de.szut.lf8_project.customer.CustomerEntity;
import de.szut.lf8_project.project.AddProjectDto;
import de.szut.lf8_project.project.ProjectEntity;
import org.springframework.stereotype.Service;

@Service
public class MappingService {

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
