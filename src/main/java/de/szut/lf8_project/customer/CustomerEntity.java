package de.szut.lf8_project.customer;

import de.szut.lf8_project.project.ProjectEntity;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name="Customer")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lastname;
    private String firstname;
    private String street;
    private String postcode;
    private String city;
    private String phone;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private Set<ProjectEntity> projects;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CustomerEntity customer = (CustomerEntity) o;
        return id != null && Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
