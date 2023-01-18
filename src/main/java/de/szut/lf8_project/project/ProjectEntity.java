package de.szut.lf8_project.project;

import de.szut.lf8_project.customer.CustomerEntity;
import de.szut.lf8_project.employee.EmployeeEntity;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "Projects")
@SequenceGenerator(name = "project_generator", sequenceName = "project_seq")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String description;
    private String comment;
    private LocalDateTime startDate;
    private LocalDateTime plannedEndDate;
    private LocalDateTime actualEndDate;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "project_employee", joinColumns = { @JoinColumn(name = "project_id") },
    inverseJoinColumns = { @JoinColumn(name = "employee_id")})
    private Set<EmployeeEntity> employees = new HashSet <>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private CustomerEntity customer;

    public void addEmployee(EmployeeEntity employee) {
        this.employees.add(employee);
        employee.getProjects().add(this);
    }

    public void removeEmployee(long employeeId) {
        EmployeeEntity employee = this.employees.stream().filter(e -> e.getId() == employeeId).findFirst().orElse(null);
        if (employee != null) {
            this.employees.remove(employee);
            employee.getProjects().remove(this);
        }
    }
}
