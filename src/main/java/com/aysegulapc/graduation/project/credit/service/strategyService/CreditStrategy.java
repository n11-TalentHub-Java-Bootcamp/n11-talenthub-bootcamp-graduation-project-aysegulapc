package com.aysegulapc.graduation.project.credit.service.strategyService;

import com.aysegulapc.graduation.project.credit.enums.StrategyNames;
import com.aysegulapc.graduation.project.user.entity.User;

public interface CreditStrategy {
    void calculateCreditResult(User user);
    StrategyNames getStrategy();
}
