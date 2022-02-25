package com.assignment.web.service;

import com.assignment.web.dto.RequestDto;
import com.assignment.web.dto.ResponseDto;
import com.assignment.web.entity.Account;
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
        account.setBalance(account.getBalance() + requestDto.getAmount());
        return ResponseDto.builder()
                .balance(account.getBalance()).build();
    }

    @Transactional
    public ResponseDto transfer(@RequestBody RequestDto requestDto) {
        Account fromAccount = accountRepository.findByAccountId(requestDto.getAccountId());
        Account toAccount = accountRepository.findByAccountId(requestDto.getToAccount());

        //Credit amount to destination account
        toAccount.setBalance(toAccount.getBalance() + requestDto.getAmount());

        //Debit amount to source account
        fromAccount.setBalance(fromAccount.getBalance() - requestDto.getAmount());

        return ResponseDto.builder()
                .balance(fromAccount.getBalance()).build();
    }
}
