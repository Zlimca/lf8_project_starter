package de.szut.lf8_project.employee;

import de.szut.lf8_project.employee.dto.AddEmployeeDto;

import de.szut.lf8_project.employee.dto.GetEmployeeShortDto;
import de.szut.lf8_project.employee.dto.GetEmployeeDto;
import de.szut.lf8_project.mapping.MappingService;
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

    @PostMapping
    public ResponseEntity<GetEmployeeShortDto> createEmployee(@Valid @RequestBody final AddEmployeeDto dto) {
        EmployeeEntity newEmployee = this.mappingService.mapAddEmployeeDtoToEmployee(dto);
        newEmployee = this.employeeService.add(newEmployee);
        final GetEmployeeShortDto request = this.mappingService.mapEmployeeToGetEmployeeShortDto(newEmployee);
        return new ResponseEntity<>(request, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<GetEmployeeDto>> findAllEmployees() {
        List<EmployeeEntity> employeeEntities = this.employeeService.readAll();
        List<GetEmployeeDto> dtoList = new LinkedList<>();
        for (EmployeeEntity employee : employeeEntities) {
            dtoList.add(this.mappingService.mapEmployeeToGetEmployeeDto(employee));
        }
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetEmployeeDto> getEmployeeById(@PathVariable final Long id) {
        final var entity = this.employeeService.readById(id);
        final GetEmployeeDto dto = this.mappingService.mapEmployeeToGetEmployeeDto(entity);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetEmployeeShortDto> updateEmployee(@PathVariable final Long id, @Valid @RequestBody final AddEmployeeDto dto) {
        EmployeeEntity updatedEmployee = this.mappingService.mapAddEmployeeDtoToEmployee(dto);
        updatedEmployee.setId(id);
        updatedEmployee = this.employeeService.update(updatedEmployee);
        GetEmployeeShortDto request = this.mappingService.mapEmployeeToGetEmployeeShortDto(updatedEmployee);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        this.employeeService.delete(id);
    }

}
