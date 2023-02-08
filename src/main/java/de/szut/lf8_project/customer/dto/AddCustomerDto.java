package de.szut.lf8_project.customer.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class AddCustomerDto {
    @NotBlank(message = "Firstname is mandatory")
    @Size(max = 50, message = "Firstname must not exceed 50 characters")
    private String firstname;
    @NotBlank(message = "Lastname is mandatory")
    @Size(max = 50, message = "Lastname must not exceed 50 characters")
    private String lastname;
    @NotBlank(message = "Street is mandatory")
    @Size(max = 50, message = "Street must not exceed 50 characters")
    private String street;
    @NotBlank(message = "Postcode is mandatory")
    @Size(min = 5, max = 5, message = "Postcode must have 5 characters")
    private String postcode;
    @NotBlank(message = "City is mandatory")
    @Size(max = 50, message = "City must not exceed 50 characters")
    private String city;
    @NotBlank(message = "Phone is mandatory")
    private String phone;
}
