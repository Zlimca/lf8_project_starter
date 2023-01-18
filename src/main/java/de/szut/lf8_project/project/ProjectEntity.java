package de.szut.lf8_project.project;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Project")
@SequenceGenerator(name="project_generator", sequenceName = "project_seq")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String description;
    private String comment;
    private LocalDateTime startdate;
    private LocalDateTime planned_enddate;
    private LocalDateTime actual_enddate;





}
