package com.assignment.web;

import com.assignment.web.entity.Customer;
import com.assignment.web.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testFindCustomer() {
        Customer customer = Customer.builder().
                name("Bob").accountId(UUID.randomUUID().toString()).build();
        entityManager.persistAndFlush(customer);
        Optional<Customer> findCustomer = customerRepository.findByNameIgnoreCase(customer.name);
        if (findCustomer.isPresent()) {
            assertEquals("Bob", findCustomer.get().name);
            System.out.println(findCustomer);
        }
    }

    @Test
    public void testCreateCustomer() {
        Customer customer = customerRepository.save(Customer
                .builder().name("Alice").accountId(UUID.randomUUID().toString()).build());
        assertEquals("Alice", customer.name);
    }
}
