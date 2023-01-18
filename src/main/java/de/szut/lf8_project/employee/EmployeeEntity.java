package de.szut.lf8_project.employee;

import de.szut.lf8_project.project.ProjectEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "employees")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private String street;
    private String postcode;
    private String city;
    private String phone;
    //private Set<String> skillSet; //TODO: Add Skill Set Entity and Many to Many relation
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "employees")
    private Set<ProjectEntity> projects = new HashSet<ProjectEntity>();

    public Set<ProjectEntity> getProjects() {
        return projects;
    }

    public void setProjects(Set<ProjectEntity> projects) {
        this.projects = projects;
    }
}
