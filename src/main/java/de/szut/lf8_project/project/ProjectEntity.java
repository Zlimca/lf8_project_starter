package de.szut.lf8_project.project;

import de.szut.lf8_project.customer.CustomerEntity;
import de.szut.lf8_project.employee.EmployeeEntity;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "projects")
@SequenceGenerator(name = "project_generator", sequenceName = "project_seq")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @JoinTable(name = "project_employee",
            joinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"))
    @ToString.Exclude
    private Set<EmployeeEntity> employees;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private CustomerEntity customer;

    public void addEmployee(EmployeeEntity employee) {
        this.employees.add(employee);
        employee.getProjects().add(this);
    }

    public boolean removeEmployee(long employeeId) {
        EmployeeEntity employee = this.employees.stream().filter(e -> e.getId() == employeeId).findFirst().orElse(null);
        if (employee != null) {
            return this.employees.remove(employee) && employee.getProjects().remove(this);
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProjectEntity that = (ProjectEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
