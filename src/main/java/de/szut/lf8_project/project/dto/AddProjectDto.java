package de.szut.lf8_project.project.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
public class AddProjectDto {
    @NotBlank(message = "description is mandatory")
    private String description;

    private String comment;

    @NotNull(message = "start date is mandatory")
    private LocalDateTime startDate;

    @NotNull(message = "end date is mandatory")
    private LocalDateTime plannedEndDate;

    private LocalDateTime actualEndDate;
    private Set<Long> employeeIds = new HashSet<>();
    private Long customerId;
}
