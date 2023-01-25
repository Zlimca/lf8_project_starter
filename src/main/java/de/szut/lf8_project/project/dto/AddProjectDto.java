package de.szut.lf8_project.project.dto;

import de.szut.lf8_project.customer.CustomerEntity;
import de.szut.lf8_project.employee.EmployeeEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
public class AddProjectDto {
    @NotBlank(message = "description is mandatory")
    private String description;

    private String comment;

    @NotBlank(message = "start date is mandatory")
    private LocalDateTime startDate;

    @NotBlank(message = "end date is mandatory")
    private LocalDateTime plannedEndDate;

    private LocalDateTime actualEndDate;
    private Set<EmployeeEntity> employees = new HashSet<>();
    private CustomerEntity customer;
}
