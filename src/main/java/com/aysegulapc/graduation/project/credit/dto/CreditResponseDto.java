package com.aysegulapc.graduation.project.credit.dto;

import com.aysegulapc.graduation.project.credit.enums.CreditEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CreditResponseDto {
    private Long id;
    private BigDecimal creditLimit;
    private Long userId;
    private CreditEnum creditResult;
    private Date savedDate;
}
