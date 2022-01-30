package com.aysegulapc.project.credit.service.strategyService.strategies;

import com.aysegulapc.graduation.project.credit.dto.CreditResponseDto;
import com.aysegulapc.graduation.project.credit.enums.CreditEnum;
import com.aysegulapc.graduation.project.credit.service.CreditService;
import com.aysegulapc.graduation.project.credit.service.UserCreditScoreService;
import com.aysegulapc.graduation.project.credit.service.strategyService.strategies.FirstCreditScoreService;
import com.aysegulapc.graduation.project.user.entity.User;
import com.aysegulapc.graduation.project.user.service.entityService.UserEntityService;
import com.aysegulapc.project.credit.service.strategyService.strategies.provider.CreditDataProvider;
import com.aysegulapc.project.user.service.provider.UserDataProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class FirstCreditScoreServiceTest {

    @InjectMocks
    private FirstCreditScoreService firstCreditScoreService;

    @Mock
    private UserCreditScoreService userCreditScoreService;

    @Mock
    private CreditService creditService;

    @Mock
    private UserEntityService userEntityService;

    @Test
    void calculateCreditResult() {
        User user = UserDataProvider.getFirstStrategyUser();
        Long creditScore = 123L;
        CreditResponseDto creditResponseDto = CreditDataProvider.getFirstCreditResponseDto(user.getId());

        when(userCreditScoreService.findCreditScore(user)).thenReturn(creditScore);
        when(creditService.createCreditResponseDtoByStrategy(
                user,
                user.getId(),
                creditScore,
                creditResponseDto.getCreditResult(),
                creditResponseDto.getCreditLimit(),
                0L)).thenReturn(creditResponseDto);

        firstCreditScoreService.calculateCreditResult(user);
        assertEquals(CreditEnum.RED, creditResponseDto.getCreditResult());
        assertEquals(new BigDecimal(0), creditResponseDto.getCreditLimit());
    }
}