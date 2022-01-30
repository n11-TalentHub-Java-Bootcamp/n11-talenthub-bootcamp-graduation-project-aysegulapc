package com.aysegulapc.graduation.project.credit.entity;

import com.aysegulapc.graduation.project.common.entity.BaseEntity;
import com.aysegulapc.graduation.project.credit.enums.CreditEnum;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "CREDIT")
@Data
public class CreditResponse implements BaseEntity {

    @Id
    @SequenceGenerator(
            name = "CreditResponse",
            sequenceName = "CREDIT_ID_SEQ",
            initialValue = 100,
            allocationSize = 1)
    @GeneratedValue(generator = "CreditResponse")
    private Long id;
    private BigDecimal creditLimit;
    private Long userId;
    private Date savedDate;

    @Enumerated(EnumType.STRING)
    private CreditEnum creditResult;
}
