package de.szut.lf8_project.employee.dto;

import lombok.Data;

@Data public class GetEmployeeShortDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String street;
    private String postcode;
    private String city;
    private String phone;
}
