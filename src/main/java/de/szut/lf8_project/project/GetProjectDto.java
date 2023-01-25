package de.szut.lf8_project.project;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetProjectDto {
    private Long id;
    private String description;
    private String comment;
    private LocalDateTime startDate;
    private LocalDateTime plannedEndDate;
    private LocalDateTime actualEndDate;
    private Long cid;
    private Long[] eIds;
}
