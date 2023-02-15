package de.szut.lf8_project.customer;

import de.szut.lf8_project.customer.dto.AddCustomerDto;
import de.szut.lf8_project.customer.dto.GetCustomerDto;
import de.szut.lf8_project.customer.dto.GetCustomerShortDto;
import de.szut.lf8_project.employee.EmployeeEntity;
import de.szut.lf8_project.employee.dto.AddEmployeeDto;
import de.szut.lf8_project.employee.dto.GetEmployeeShortDto;
import de.szut.lf8_project.hello.dto.HelloGetDto;
import de.szut.lf8_project.mapping.MappingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/swagger/customer")
public class CustomerController {
    private  final CustomerService customerService;
    private final MappingService mappingService;

    public CustomerController(CustomerService customerService, MappingService mappingService) {
        this.customerService = customerService;
        this.mappingService = mappingService;
    }

    @Operation(summary = "creates a new customer with its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "created customer",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AddCustomerDto.class))}),
            @ApiResponse(responseCode = "400", description = "invalid JSON posted",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content)})
    @PostMapping
    public ResponseEntity<GetCustomerDto> createCustomer(@Valid @RequestBody final AddCustomerDto dto) {
        CustomerEntity newCustomer = this.mappingService.mapAddCustomerDtoToCustomer(dto);
        newCustomer = this.customerService.add(newCustomer);
        final GetCustomerDto request = this.mappingService.mapCustomerToGetCustomerShortDto(newCustomer);
        return new ResponseEntity<>(request, HttpStatus.CREATED);
    }

    @Operation(summary = "delivers a list of customers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "list of customers",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetCustomerShortDto.class))}),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content)})
    @GetMapping
    public ResponseEntity<List<GetCustomerDto>> findAllCustomers() {
        List<CustomerEntity> customerEntities = this.customerService.readAll();
        List<GetCustomerDto> dtoList = new LinkedList<>();
        for (CustomerEntity Customer : customerEntities) {
            dtoList.add(this.mappingService.mapCustomerToGetCustomerDto(Customer));
        }
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @Operation(summary = "delivers a customer by its Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "customer by id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetCustomerDto.class))}),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<GetCustomerDto> getCustomerById(@PathVariable final Long id) {
        final var entity = this.customerService.readById(id);
        final GetCustomerDto dto = this.mappingService.mapCustomerToGetCustomerDto(entity);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "updates a customer by its Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "customer by id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AddCustomerDto.class))}),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content)})
    @PutMapping("/{id}")
    public ResponseEntity<GetCustomerDto> updateCustomer(@PathVariable final Long id, @Valid @RequestBody final AddCustomerDto dto) {
        CustomerEntity updatedCustomer = this.mappingService.mapAddCustomerDtoToCustomer(dto);
        updatedCustomer.setId(id);
        updatedCustomer = this.customerService.update(updatedCustomer);
        GetCustomerDto request = this.mappingService.mapCustomerToGetCustomerShortDto(updatedCustomer);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }

    @Operation(summary = "deletes a Customer by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "delete successful"),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "resource not found",
                    content = @Content)})
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        this.customerService.delete(id);
    }

}
