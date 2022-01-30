package com.aysegulapc.graduation.project.credit.converter;

import com.aysegulapc.graduation.project.credit.dto.CreditResponseDto;
import com.aysegulapc.graduation.project.credit.entity.CreditResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CreditConverter {
    CreditConverter INSTANCE = Mappers.getMapper(CreditConverter.class);

    CreditResponse convertCreditResponseDtoToCreditResponse(CreditResponseDto creditResponseDto);
}
