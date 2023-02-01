package de.szut.lf8_project.employee;

import de.szut.lf8_project.project.ProjectEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "employee")
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
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "employee")
    private Set<ProjectEntity> projects = new HashSet<>();

    public Set<ProjectEntity> getProjects() {
        return this.projects;
    }

    public void setProjects(Set<ProjectEntity> projects) {
        this.projects = projects;
    }
}
