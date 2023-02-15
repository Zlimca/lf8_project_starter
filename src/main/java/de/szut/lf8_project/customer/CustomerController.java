package de.szut.lf8_project.customer;

import de.szut.lf8_project.customer.dto.AddCustomerDto;
import de.szut.lf8_project.customer.dto.GetCustomerDto;
import de.szut.lf8_project.mapping.MappingService;
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

    @PostMapping
    public ResponseEntity<GetCustomerDto> createCustomer(@Valid @RequestBody final AddCustomerDto dto) {
        CustomerEntity newCustomer = this.mappingService.mapAddCustomerDtoToCustomer(dto);
        newCustomer = this.customerService.add(newCustomer);
        final GetCustomerDto request = this.mappingService.mapCustomerToGetCustomerShortDto(newCustomer);
        return new ResponseEntity<>(request, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<GetCustomerDto>> findAllCustomers() {
        List<CustomerEntity> customerEntities = this.customerService.readAll();
        List<GetCustomerDto> dtoList = new LinkedList<>();
        for (CustomerEntity Customer : customerEntities) {
            dtoList.add(this.mappingService.mapCustomerToGetCustomerDto(Customer));
        }
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetCustomerDto> getCustomerById(@PathVariable final Long id) {
        final var entity = this.customerService.readById(id);
        final GetCustomerDto dto = this.mappingService.mapCustomerToGetCustomerDto(entity);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetCustomerDto> updateCustomer(@PathVariable final Long id, @Valid @RequestBody final AddCustomerDto dto) {
        CustomerEntity updatedCustomer = this.mappingService.mapAddCustomerDtoToCustomer(dto);
        updatedCustomer.setId(id);
        updatedCustomer = this.customerService.update(updatedCustomer);
        GetCustomerDto request = this.mappingService.mapCustomerToGetCustomerDto(updatedCustomer);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        this.customerService.delete(id);
    }

}
