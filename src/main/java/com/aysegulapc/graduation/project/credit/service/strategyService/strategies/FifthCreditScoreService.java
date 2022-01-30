package com.aysegulapc.graduation.project.credit.service.strategyService.strategies;

import com.aysegulapc.graduation.project.credit.enums.CreditEnum;
import com.aysegulapc.graduation.project.credit.enums.StrategyNames;
import com.aysegulapc.graduation.project.credit.service.CreditService;
import com.aysegulapc.graduation.project.credit.service.UserCreditScoreService;
import com.aysegulapc.graduation.project.credit.service.strategyService.CreditStrategy;
import com.aysegulapc.graduation.project.user.entity.User;
import com.aysegulapc.graduation.project.user.service.entityService.UserEntityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class FifthCreditScoreService implements CreditStrategy {

    @Autowired
    private UserCreditScoreService userCreditScoreService;

    @Autowired
    private CreditService creditService;

    @Autowired
    private UserEntityService userEntityService;

    @Override
    public void calculateCreditResult(User user) {
        Long creditScore = userCreditScoreService.findCreditScore(user.getId());

        if(creditScore >= 1000L) {
            log.info("User's information is in the fifth strategy service.");
            creditService.createCreditResponseDtoByStrategy(
                    user, user.getId(), creditScore, CreditEnum.ONAY, user.getSalary().multiply(new BigDecimal(4)), 50L);
        }
    }

    @Override
    public StrategyNames getStrategy() {
        return StrategyNames.FifthCreditScoreService;
    }
}
