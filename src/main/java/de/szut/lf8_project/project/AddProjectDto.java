package de.szut.lf8_project.project;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

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


}
