package com.aysegulapc.project.credit.service;

import com.aysegulapc.graduation.project.credit.dto.CreditResponseDto;
import com.aysegulapc.graduation.project.credit.enums.CreditEnum;
import com.aysegulapc.graduation.project.credit.service.CreditService;
import com.aysegulapc.graduation.project.credit.service.entityService.CreditEntityService;
import com.aysegulapc.graduation.project.user.entity.User;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class CreditServiceTest {

    @InjectMocks
    private CreditService creditService;

    @Mock
    private CreditEntityService creditEntityService;

    @Test
    void shouldReturnFirstUserCreditResult() {
        User user = UserDataProvider.getFirstStrategyUser();
        CreditResponseDto creditResponseDto = CreditDataProvider.getFirstCreditResponseDto(user.getId());
        Long creditScore = 123L;

        CreditResponseDto resultCreditResponseDto = creditService.createCreditResponseDtoByStrategy(
                user, user.getId(), creditScore, creditResponseDto.getCreditResult(), creditResponseDto.getCreditLimit(), 10L);

        assertEquals(resultCreditResponseDto.getCreditLimit(), new BigDecimal(0));
        assertEquals(resultCreditResponseDto.getCreditResult(), CreditEnum.RED);
    }

    @Test
    void shouldReturnSecondUserCreditResult() {
        User user = UserDataProvider.getSecondStrategyUser();
        CreditResponseDto creditResponseDto = CreditDataProvider.getSecondCreditResponseDto(user.getId());
        Long creditScore = 678L;
        long guaranteeRate = 10L;

        CreditResponseDto resultCreditResponseDto = creditService.createCreditResponseDtoByStrategy(
                user, user.getId(), creditScore, creditResponseDto.getCreditResult(), creditResponseDto.getCreditLimit(), guaranteeRate);

        assertEquals(resultCreditResponseDto.getCreditLimit(), new BigDecimal(10400));
        assertEquals(resultCreditResponseDto.getCreditResult(), CreditEnum.ONAY);
    }

    @Test
    void shouldReturnThirdUserCreditResult() {
        User user = UserDataProvider.getThirdStrategyUser();
        CreditResponseDto creditResponseDto = CreditDataProvider.getThirdCreditResponseDto(user.getId());
        Long creditScore = 678L;
        long guaranteeRate = 20L;

        CreditResponseDto resultCreditResponseDto = creditService.createCreditResponseDtoByStrategy(
                user, user.getId(), creditScore, creditResponseDto.getCreditResult(), creditResponseDto.getCreditLimit(), guaranteeRate);

        assertEquals(resultCreditResponseDto.getCreditLimit(), new BigDecimal(20800));
        assertEquals(resultCreditResponseDto.getCreditResult(), CreditEnum.ONAY);
    }

    @Test
    void shouldReturnFourthUserCreditResult() {
        User user = UserDataProvider.getFourthStrategyUser();
        CreditResponseDto creditResponseDto = CreditDataProvider.getFourthCreditResponseDto(user.getId());
        Long creditScore = 678L;
        long guaranteeRate = 25L;

        CreditResponseDto resultCreditResponseDto = creditService.createCreditResponseDtoByStrategy(
                user, user.getId(), creditScore, creditResponseDto.getCreditResult(), creditResponseDto.getCreditLimit(), guaranteeRate);

        assertEquals(resultCreditResponseDto.getCreditLimit(), new BigDecimal(23000));
        assertEquals(resultCreditResponseDto.getCreditResult(), CreditEnum.ONAY);
    }

    @Test
    void shouldReturnFifthUserCreditResult() {
        User user = UserDataProvider.getFifthStrategyUser();
        CreditResponseDto creditResponseDto = CreditDataProvider.getFifthCreditResponseDto(user.getId());
        Long creditScore = 678L;
        long guaranteeRate = 50L;

        CreditResponseDto resultCreditResponseDto = creditService.createCreditResponseDtoByStrategy(
                user, user.getId(), creditScore, creditResponseDto.getCreditResult(), creditResponseDto.getCreditLimit(), guaranteeRate);

        assertEquals(resultCreditResponseDto.getCreditLimit(), new BigDecimal(26000));
        assertEquals(resultCreditResponseDto.getCreditResult(), CreditEnum.ONAY);
    }

    @Test
    void shouldReturnReturnCreditResultWhenGuaranteeAmountIsNull() {
        User user = UserDataProvider.getGuaranteeAmountIsNullUser();
        CreditResponseDto creditResponseDto = CreditDataProvider.getThirdCreditResponseDto(user.getId());
        Long creditScore = 523L;

        CreditResponseDto resultCreditResponseDto = creditService.createCreditResponseDtoByStrategy(
                user, user.getId(), creditScore, creditResponseDto.getCreditResult(), creditResponseDto.getCreditLimit(), 20L);

        assertEquals(user.getGuaranteeAmount(), new BigDecimal(0));
        assertEquals(resultCreditResponseDto.getCreditLimit(), new BigDecimal(20000));
        assertEquals(resultCreditResponseDto.getCreditResult(), CreditEnum.ONAY);
    }
}