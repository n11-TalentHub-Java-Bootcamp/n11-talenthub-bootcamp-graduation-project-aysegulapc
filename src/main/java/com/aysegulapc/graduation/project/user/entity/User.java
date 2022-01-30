package com.aysegulapc.graduation.project.user.entity;

import com.aysegulapc.graduation.project.common.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "USERS")
@Data
public class User implements BaseEntity {

    @Id
    @SequenceGenerator(
            name = "User",
            sequenceName = "USERS_ID_SEQ",
            initialValue = 100,
            allocationSize = 1)
    @GeneratedValue(generator = "User")
    private Long id;

    private Long TCNo;
    private String name;
    private String surname;
    private BigDecimal salary;
    private String phoneNumber;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;

    private BigDecimal guaranteeAmount;
}
