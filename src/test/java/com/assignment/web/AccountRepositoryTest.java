package com.assignment.web;

import com.assignment.web.entity.Account;
import com.assignment.web.entity.Customer;
import com.assignment.web.repository.AccountRepository;
import com.assignment.web.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class AccountRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void testCreateCustomerAndAccountWithInitialBalance() {
        entityManager.persistAndFlush(Customer.builder()
                .name("Bob").accountId(UUID.randomUUID().toString()).build());
        Optional<Customer> customer = customerRepository.findByNameIgnoreCase("Bob");
        customer.ifPresent(
                bob -> {
                    entityManager.persistAndFlush(
                            Account.builder().accountId(bob.getAccountId()).balance(80).build());
                    Account account = accountRepository.findByAccountId(bob.getAccountId());
                    assertEquals(80, account.getBalance());
                }
        );
    }
}
