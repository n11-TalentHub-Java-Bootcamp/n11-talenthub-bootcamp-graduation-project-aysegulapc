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
public class SecondCreditScoreService implements CreditStrategy {
    @Autowired
    private UserCreditScoreService userCreditScoreService;

    @Autowired
    private CreditService creditService;

    @Autowired
    private UserEntityService userEntityService;

    @Override
    public void calculateCreditResult(User user) {
        Long creditScore = userCreditScoreService.findCreditScore(user);
        BigDecimal salary = user.getSalary();
        int res = salary.compareTo(new BigDecimal(5000));

        if(creditScore >= 500L && creditScore <= 1000L && res < 0) {
            log.info("User's information is in the second strategy service.");
            creditService.createCreditResponseDtoByStrategy(
                     user, user.getId(), creditScore, CreditEnum.ONAY, new BigDecimal(10000), 10L);
        }
    }

    @Override
    public StrategyNames getStrategy() {
        return StrategyNames.SecondCreditScoreService;
    }
}
