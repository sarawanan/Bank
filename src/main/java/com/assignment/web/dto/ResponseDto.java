package com.assignment.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDto {
    private int id;
    private String name;
    private String accountId;
    private String toAccount;
    private double balance;
    private double liability;
}
