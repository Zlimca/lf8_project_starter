package de.szut.lf8_project.employee;

import de.szut.lf8_project.customer.dto.AddCustomerDto;
import de.szut.lf8_project.customer.dto.GetCustomerDto;
import de.szut.lf8_project.customer.dto.GetCustomerShortDto;
import de.szut.lf8_project.employee.dto.AddEmployeeDto;

import de.szut.lf8_project.employee.dto.GetEmployeeShortDto;
import de.szut.lf8_project.employee.dto.GetEmployeeDto;
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
@RequestMapping("/swagger/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final MappingService mappingService;

    public EmployeeController(EmployeeService employeeService, MappingService mappingService) {
        this.employeeService = employeeService;
        this.mappingService = mappingService;
    }

    @Operation(summary = "creates a new customer with its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "created employee",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AddEmployeeDto.class))}),
            @ApiResponse(responseCode = "400", description = "invalid JSON posted",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content)})
    @PostMapping
    public ResponseEntity<GetEmployeeShortDto> createEmployee(@Valid @RequestBody final AddEmployeeDto dto) {
        EmployeeEntity newEmployee = this.mappingService.mapAddEmployeeDtoToEmployee(dto);
        newEmployee = this.employeeService.add(newEmployee);
        final GetEmployeeShortDto request = this.mappingService.mapEmployeeToGetEmployeeShortDto(newEmployee);
        return new ResponseEntity<>(request, HttpStatus.CREATED);
    }

    @Operation(summary = "delivers a list of employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "list of employees",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetEmployeeShortDto.class))}),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content)})
    @GetMapping
    public ResponseEntity<List<GetEmployeeDto>> findAllEmployees() {
        List<EmployeeEntity> employeeEntities = this.employeeService.readAll();
        List<GetEmployeeDto> dtoList = new LinkedList<>();
        for (EmployeeEntity employee : employeeEntities) {
            dtoList.add(this.mappingService.mapEmployeeToGetEmployeeDto(employee));
        }
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @Operation(summary = "delivers a employee by its Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "employee by id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetEmployeeDto.class))}),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<GetEmployeeDto> getEmployeeById(@PathVariable final Long id) {
        final var entity = this.employeeService.readById(id);
        final GetEmployeeDto dto = this.mappingService.mapEmployeeToGetEmployeeDto(entity);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "updates a employee by its Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "employee by id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AddEmployeeDto.class))}),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content)})
    @PutMapping("/{id}")
    public ResponseEntity<GetEmployeeShortDto> updateEmployee(@PathVariable final Long id, @Valid @RequestBody final AddEmployeeDto dto) {
        EmployeeEntity updatedEmployee = this.mappingService.mapAddEmployeeDtoToEmployee(dto);
        updatedEmployee.setId(id);
        updatedEmployee = this.employeeService.update(updatedEmployee);
        GetEmployeeShortDto request = this.mappingService.mapEmployeeToGetEmployeeShortDto(updatedEmployee);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }

    @Operation(summary = "deletes an employee by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "delete successful"),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "resource not found",
                    content = @Content)})
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        this.employeeService.delete(id);
    }

}
