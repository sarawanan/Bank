package com.assignment.web.service;

import com.assignment.web.dto.RequestDto;
import com.assignment.web.dto.ResponseDto;
import com.assignment.web.entity.Account;
import com.assignment.web.entity.Liability;
import com.assignment.web.repository.AccountRepository;
import com.assignment.web.repository.LiabilityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;

@Service
@Slf4j
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private LiabilityRepository liabilityRepository;

    @Transactional
    public ResponseDto topUpBalance(RequestDto requestDto) {
        Account account = accountRepository.findByAccountId(requestDto.getAccountId());
        Liability liability = liabilityRepository.findByAccountId(account.getAccountId());
        if (liability.getAmount() > 0) {
            double difference = requestDto.getAmount() - liability.getAmount();
            if (difference > 0) {
                account.setBalance(difference);
            } else {
                liability.setAmount(liability.getAmount() - requestDto.getAmount());
            }
        }
        account.setBalance(account.getBalance() + requestDto.getAmount());
        return ResponseDto.builder()
                .balance(account.getBalance()).build();
    }

    @Transactional
    public ResponseDto transfer(@RequestBody RequestDto requestDto) {
        Account fromAccount = accountRepository.findByAccountId(requestDto.getAccountId());
        Account toAccount = accountRepository.findByAccountId(requestDto.getToAccount());

        double fromAccountBalance = fromAccount.getBalance();
        double balance = 0;
        ResponseDto responseDto = ResponseDto.builder().build();

        if (requestDto.getAmount() > fromAccountBalance) {
            toAccount.setBalance(toAccount.getBalance() + fromAccountBalance);
            liabilityRepository.save(Liability.builder()
                    .accountId(requestDto.getAccountId())
                    .toAccount(requestDto.getToAccount())
                    .amount(requestDto.getAmount() - fromAccountBalance)
                    .build());
            responseDto.setLiability(requestDto.getAmount() - fromAccountBalance);
        } else {
            balance = fromAccountBalance - requestDto.getAmount();
        }
        fromAccount.setBalance(balance);
        responseDto.setBalance(balance);
        return responseDto;
    }
}
