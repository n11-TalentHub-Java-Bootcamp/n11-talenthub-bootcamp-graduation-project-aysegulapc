package com.aysegulapc.graduation.project.credit.controller;

import com.aysegulapc.graduation.project.credit.dto.UsersCreditDetailsDto;
import com.aysegulapc.graduation.project.credit.service.UserCreditScoreService;
import com.aysegulapc.graduation.project.credit.service.entityService.CreditEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/credits")
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
public class CreditController {

    private final UserCreditScoreService userCreditScoreService;
    private final CreditEntityService creditEntityService;

    @GetMapping("/users")
    public ResponseEntity getAllUsersCreditDetails() {
        List<UsersCreditDetailsDto> usersCreditDetailList =
                creditEntityService.findAllUsersCreditDetailList();
        return ResponseEntity.ok(usersCreditDetailList);
    }

    @GetMapping("/{tcno}/{birthdate}")
    public ResponseEntity getCreditResultByTCNoAndBirthdate(@PathVariable Long tcno,
                                                            @PathVariable("birthdate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthdate) {
        log.info("");
        UsersCreditDetailsDto userByTcNoAndBirthdate = creditEntityService.findUserByTcNoAndBirthdate(tcno, birthdate);
        return ResponseEntity.ok(userByTcNoAndBirthdate);
    }

    @GetMapping("/creditScore/{id}")
    public ResponseEntity getCreditScoreByUserId(@PathVariable Long id) {
        Long creditScore = userCreditScoreService.findCreditScore(id);
        return ResponseEntity.ok(creditScore);
    }
}
