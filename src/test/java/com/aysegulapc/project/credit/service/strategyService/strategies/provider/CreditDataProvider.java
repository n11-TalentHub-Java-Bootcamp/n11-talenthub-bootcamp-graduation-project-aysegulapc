package com.aysegulapc.project.credit.service.strategyService.strategies.provider;

import com.aysegulapc.graduation.project.credit.converter.CreditConverter;
import com.aysegulapc.graduation.project.credit.dto.CreditResponseDto;
import com.aysegulapc.graduation.project.credit.entity.CreditResponse;
import com.aysegulapc.graduation.project.credit.enums.CreditEnum;

import java.math.BigDecimal;

public class CreditDataProvider {

    public static CreditResponse convertCreditResponseDtoToCreditResponse(CreditResponseDto creditResponseDto) {
        return CreditConverter.INSTANCE.convertCreditResponseDtoToCreditResponse(creditResponseDto);
    }

    public static CreditResponseDto getFirstCreditResponseDto(Long userId) {
        CreditResponseDto creditResponseDto = new CreditResponseDto();

        creditResponseDto.setId(1L);
        creditResponseDto.setUserId(userId);
        creditResponseDto.setCreditLimit(new BigDecimal(0));
            creditResponseDto.setCreditResult(CreditEnum.RED);
        return creditResponseDto;
    }

    public static CreditResponseDto getSecondCreditResponseDto(Long userId) {
        CreditResponseDto creditResponseDto = new CreditResponseDto();

        creditResponseDto.setId(1L);
        creditResponseDto.setUserId(userId);
        creditResponseDto.setCreditLimit(new BigDecimal(10000));
        creditResponseDto.setCreditResult(CreditEnum.ONAY);
        return creditResponseDto;
    }

    public static CreditResponseDto getThirdCreditResponseDto(Long userId) {
        CreditResponseDto creditResponseDto = new CreditResponseDto();

        creditResponseDto.setId(1L);
        creditResponseDto.setUserId(userId);
        creditResponseDto.setCreditLimit(new BigDecimal(20000));
        creditResponseDto.setCreditResult(CreditEnum.ONAY);
        return creditResponseDto;
    }

    public static CreditResponseDto getFourthCreditResponseDto(Long userId) {
        CreditResponseDto creditResponseDto = new CreditResponseDto();

        creditResponseDto.setId(1L);
        creditResponseDto.setUserId(userId);
        creditResponseDto.setCreditLimit(new BigDecimal(22000));
        creditResponseDto.setCreditResult(CreditEnum.ONAY);
        return creditResponseDto;
    }

    public static CreditResponseDto getFifthCreditResponseDto(Long userId) {
        CreditResponseDto creditResponseDto = new CreditResponseDto();

        creditResponseDto.setId(1L);
        creditResponseDto.setUserId(userId);
        creditResponseDto.setCreditLimit(new BigDecimal(24000));
        creditResponseDto.setCreditResult(CreditEnum.ONAY);
        return creditResponseDto;
    }
}
