package de.szut.lf8_project.customer;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public List<CustomerEntity> readAll() {
        return this.repository.findAll();
    }

    public CustomerEntity readById(long id) {
        Optional<CustomerEntity> optionalCustomer = this.repository.findById(id);
        return optionalCustomer.orElse(null);
    }

    public CustomerEntity add(CustomerEntity customer) {
        return this.repository.save(customer);
    }

    public CustomerEntity update(CustomerEntity customer) {
        CustomerEntity updatedCustomer = readById(customer.getId());
        updatedCustomer.setFirstname(customer.getFirstname());
        updatedCustomer.setLastname(customer.getLastname());
        updatedCustomer.setStreet(customer.getStreet());
        updatedCustomer.setPostcode(customer.getPostcode());
        updatedCustomer.setCity(customer.getCity());
        updatedCustomer.setPhone(customer.getPhone());
        updatedCustomer.setProjects(customer.getProjects());
        updatedCustomer = this.repository.save(updatedCustomer);
        return updatedCustomer;
    }

    public void delete(long id) {
        repository.deleteById(id);
    }
}
