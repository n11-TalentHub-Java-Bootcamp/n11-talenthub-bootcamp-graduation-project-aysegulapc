package com.aysegulapc.graduation.project.credit.dto;

import com.aysegulapc.graduation.project.credit.enums.CreditEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersCreditDetailsDto {
    private Long id;
    private Long TCNo;
    private String name;
    private String surname;
    private BigDecimal salary;
    private String phoneNumber;
    private LocalDate birthdate;
    private BigDecimal guaranteeAmount;
    private BigDecimal creditLimit;
    private CreditEnum creditResult;
    private Date savedDate;
}
