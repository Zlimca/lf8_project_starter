package de.szut.lf8_project.customer;

import de.szut.lf8_project.project.ProjectEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;
@Data
@Entity
@Table(name="Customer")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String lastname;
    private String firstname;
    private String street;
    private String postcode;
    private String city;
    private String phone;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ProjectEntity> projects;

}
