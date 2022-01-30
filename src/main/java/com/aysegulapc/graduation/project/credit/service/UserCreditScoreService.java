package com.aysegulapc.graduation.project.credit.service;

import com.aysegulapc.graduation.project.user.entity.User;
import com.aysegulapc.graduation.project.user.exception.UserNotFoundException;
import com.aysegulapc.graduation.project.user.service.entityService.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCreditScoreService {
    private final UserEntityService userEntityService;

    private static final Logger logger = LoggerFactory.getLogger(UserCreditScoreService.class);

    /* This method calculates credit score by user last three digits of
     * tc number. If the last three digits of tc number is lass than
     * 100, then it multiplies with 10. Else, return the last three digits
     * of tc number directly. For example tc number is 34657482045 then
     * credit score is 450. If tc number is 34657482678 then credit score
     * is 678.
     */

    public Long findCreditScore(Long id) {
        User user = userEntityService.getById(id);
        if(user == null) {
            throw new UserNotFoundException("User not found!");
        }
        long creditScore = 0L;
        long lastThreeDigitOfTCNo = user.getTCNo() % 1000;
        if(lastThreeDigitOfTCNo < 100) {
            creditScore = lastThreeDigitOfTCNo * 100;
        } else {
            creditScore = lastThreeDigitOfTCNo;
        }
        logger.info("CreditScore is {}", creditScore);
        return creditScore;
    }
}
