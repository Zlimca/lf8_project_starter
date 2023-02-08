package de.szut.lf8_project.project.dto;

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
    private Set<Long> employeeIds;
    private Long customerId;
}
