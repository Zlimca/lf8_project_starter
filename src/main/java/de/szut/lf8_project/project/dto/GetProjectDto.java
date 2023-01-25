package de.szut.lf8_project.project.dto;

import de.szut.lf8_project.customer.CustomerEntity;
import de.szut.lf8_project.employee.EmployeeEntity;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class GetProjectDto {
    private Long id;
    private String description;
    private String comment;
    private LocalDateTime startDate;
    private LocalDateTime plannedEndDate;
    private LocalDateTime actualEndDate;
    private CustomerEntity customer;
    private Set<EmployeeEntity> employees;
}
