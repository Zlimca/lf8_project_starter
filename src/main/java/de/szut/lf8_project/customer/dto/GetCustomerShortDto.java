package de.szut.lf8_project.customer.dto;

import de.szut.lf8_project.project.ProjectEntity;
import lombok.Data;

import java.util.Set;

@Data
public class GetCustomerShortDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String street;
    private String postcode;
    private String city;
    private String phone;
}