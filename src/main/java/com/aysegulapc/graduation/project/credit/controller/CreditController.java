package com.aysegulapc.graduation.project.credit.controller;

import com.aysegulapc.graduation.project.credit.dto.UsersCreditDetailsDto;
import com.aysegulapc.graduation.project.credit.service.UserCreditScoreService;
import com.aysegulapc.graduation.project.credit.service.entityService.CreditEntityService;
import com.aysegulapc.graduation.project.user.dto.UserDto;
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

    private final CreditEntityService creditEntityService;

    /**
     *  Gets all users with join user and users' credit information.
     * @return ResponseEntity
     */
    @GetMapping("/users")
    public ResponseEntity getAllUsersCreditDetails() {
        List<UsersCreditDetailsDto> usersCreditDetailList =
                creditEntityService.findAllUsersCreditDetailList();
        return ResponseEntity.ok(usersCreditDetailList);
    }
    
    /**
     * Gets user by tcno and birthdate.
     * @param tcno
     * @param birthdate
     * @return ResponseEntity
     */
    @GetMapping("/{tcno}/{birthdate}")
    public ResponseEntity getCreditResultByTCNoAndBirthdate(@PathVariable Long tcno,
                                                            @PathVariable("birthdate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthdate) {
        log.info("");
        UsersCreditDetailsDto userByTcNoAndBirthdate = creditEntityService.findUserByTcNoAndBirthdate(tcno, birthdate);
        return ResponseEntity.ok(userByTcNoAndBirthdate);
    }

    /**
     * Gets user's credit score.
     * @param userDto
     * @return ResponseEntity
     */
    @GetMapping("/creditScore")
    public ResponseEntity getCreditScoreByUserId(@RequestParam UserDto userDto) {
        Long creditScore = creditEntityService.getUserCreditScore(userDto);
        return ResponseEntity.ok(creditScore);
    }
}
