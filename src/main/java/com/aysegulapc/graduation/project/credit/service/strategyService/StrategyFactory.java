package com.aysegulapc.graduation.project.credit.service.strategyService;

import com.aysegulapc.graduation.project.credit.enums.StrategyNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class StrategyFactory {
    private Map<StrategyNames, CreditStrategy> strategies;

    @Autowired
    public StrategyFactory(Set<CreditStrategy> strategySet) {
        createStrategy(strategySet);
    }

    public CreditStrategy findStrategy(StrategyNames strategyName) {
        return strategies.get(strategyName);
    }

    private void createStrategy(Set<CreditStrategy> strategySet) {
        strategies = new HashMap<StrategyNames, CreditStrategy>();
        strategySet.forEach(
                strategy ->strategies.put(strategy.getStrategy(), strategy));
    }
}
