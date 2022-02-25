package com.assignment.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestDto {
    private int id;
    private String name;
    private String accountId;
    private double amount;
    private String toAccount;
}
