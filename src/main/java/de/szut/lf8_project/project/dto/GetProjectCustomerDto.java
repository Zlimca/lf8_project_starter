package de.szut.lf8_project.project.dto;

import de.szut.lf8_project.customer.dto.GetCustomerShortDto;
import de.szut.lf8_project.employee.dto.GetEmployeeShortDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class GetProjectCustomerDto {
    private Long id;
    private String description;
    private String comment;
    private LocalDateTime startDate;
    private LocalDateTime plannedEndDate;
    private LocalDateTime actualEndDate;
    private Set<GetEmployeeShortDto> employees;

}
