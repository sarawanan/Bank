package com.assignment.web.contoller;

import com.assignment.web.entity.Account;
import com.assignment.web.repository.AccountRepository;
import com.assignment.web.entity.Customer;
import com.assignment.web.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import java.util.UUID;

@Controller
public class LoginController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping("/login/{customerName}")
    public String login(
            @PathVariable("customerName") String customerName,
            Model model) {
        Customer customer = Optional.of(
                customerRepository.findByNameIgnoreCase(customerName)
                        .orElse(customerRepository.save(
                                Customer.builder()
                                        .name(customerName)
                                        .accountId(UUID.randomUUID().toString())
                                        .build()))).get();
        accountRepository.save(
                Account.builder()
                        .accountId(customer.getAccountId())
                        .balance(0).build());
        model.addAttribute("name", customer.name);
        model.addAttribute("id", customer.getId());
        model.addAttribute("accountId", customer.getAccountId());
        return "welcome";
    }
}
