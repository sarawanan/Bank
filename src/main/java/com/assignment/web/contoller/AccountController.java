package com.assignment.web.contoller;

import com.assignment.web.dto.RequestDto;
import com.assignment.web.dto.ResponseDto;
import com.assignment.web.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@Slf4j
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/top-up")
    public ResponseDto topUpBalance(@RequestBody RequestDto requestDto) {
        log.info("Request::" + requestDto.toString());
        return accountService.topUpBalance(requestDto);
    }

    @PostMapping("/transfer")
    public ResponseDto transfer(@RequestBody RequestDto requestDto) {
        log.info("Request::" + requestDto.toString());
        return accountService.transfer(requestDto);
    }
}
