package com.aysegulapc.graduation.project.credit.service;

import com.aysegulapc.graduation.project.credit.converter.CreditConverter;
import com.aysegulapc.graduation.project.credit.dto.CreditResponseDto;
import com.aysegulapc.graduation.project.credit.entity.CreditResponse;
import com.aysegulapc.graduation.project.credit.enums.CreditEnum;
import com.aysegulapc.graduation.project.credit.service.entityService.CreditEntityService;
import com.aysegulapc.graduation.project.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreditService {

    private final CreditEntityService creditEntityService;

    private static final Logger logger = LoggerFactory.getLogger(CreditService.class);

    /**
     * Method returns CreditResponseDto by according to take parameter from strategies.
     * @param user
     * @param id
     * @param creditScore
     * @param creditEnum
     * @param creditLimit
     * @param guaranteeRate
     * @return CreditResponseDto
     */
    public CreditResponseDto createCreditResponseDtoByStrategy(
            User user,
            Long id,
            Long creditScore,
            CreditEnum creditEnum,
            BigDecimal creditLimit,
            Long guaranteeRate) {

        CreditResponseDto creditResponseDto = CreditResponseDto.builder()
                .id(id + 1L)
                .creditResult(creditEnum)
                .creditLimit(creditLimit)
                .savedDate(new Date(System.currentTimeMillis()))
                .userId(id)
                .build();
        if(user.getGuaranteeAmount() == null || Objects.equals(user.getGuaranteeAmount(), new BigDecimal(0))) {
            user.setGuaranteeAmount(new BigDecimal(0));
        }
        else if (user.getGuaranteeAmount().compareTo(BigDecimal.ZERO) > 0 && creditScore > 500L) {
            BigDecimal result = user.getGuaranteeAmount()
                    .divide(new BigDecimal(100))
                    .multiply(new BigDecimal(guaranteeRate));
            creditResponseDto.setCreditLimit(creditResponseDto.getCreditLimit().add(result));
        }

        checkAndSaveCreditResponseDto(creditResponseDto);
        logger.info("Credit response dto {}", creditResponseDto);
        return creditResponseDto;
    }

    private void checkAndSaveCreditResponseDto(CreditResponseDto creditResponseDto) {
        if(creditResponseDto != null) {
            CreditResponse creditResponse = CreditConverter.INSTANCE.convertCreditResponseDtoToCreditResponse(creditResponseDto);
            logger.info("Credit Response {}", creditResponse);
            creditEntityService.save(creditResponse);
        }
    }
}
