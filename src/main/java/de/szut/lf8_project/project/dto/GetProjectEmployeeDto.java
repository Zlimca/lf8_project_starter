package de.szut.lf8_project.project.dto;

import de.szut.lf8_project.customer.dto.GetCustomerShortDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetProjectEmployeeDto {
    private Long id;
    private String description;
    private String comment;
    private LocalDateTime startDate;
    private LocalDateTime plannedEndDate;
    private LocalDateTime actualEndDate;
    private GetCustomerShortDto customer;
}
