package com.aysegulapc.project.credit.service.strategyService.strategies.provider;

import com.aysegulapc.graduation.project.credit.converter.CreditConverter;
import com.aysegulapc.graduation.project.credit.dto.CreditResponseDto;
import com.aysegulapc.graduation.project.credit.dto.UsersCreditDetailsDto;
import com.aysegulapc.graduation.project.credit.entity.CreditResponse;
import com.aysegulapc.graduation.project.credit.enums.CreditEnum;
import com.aysegulapc.graduation.project.user.entity.User;

import javax.swing.plaf.basic.BasicGraphicsUtils;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

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

    public static User getUser() {
        User user = new User();
        LocalDate birthdate = LocalDate.of(1996, Month.JANUARY, 8);

        user.setId(1L);
        user.setName("Test user name");
        user.setSurname("Test user surname");
        user.setSalary(new BigDecimal(17000));
        user.setGuaranteeAmount(new BigDecimal(1000));
        user.setPhoneNumber("+905554448899");
        user.setTCNo(12345678546L);
        user.setBirthdate(birthdate);

        return user;
    }

    public static CreditResponse getCreditResponse(Long userId) {
        CreditResponse creditResponse = new CreditResponse();

        creditResponse.setId(1L);
        creditResponse.setUserId(userId);
        creditResponse.setCreditLimit(new BigDecimal(34250));
        creditResponse.setCreditResult(CreditEnum.ONAY);
        return creditResponse;
    }

    public static List<UsersCreditDetailsDto> getUsersCreditDetailsDtoList() {
        List<UsersCreditDetailsDto> usersCreditDetailsDtoList = new ArrayList<>();

        UsersCreditDetailsDto usersCreditDetailsDto = new UsersCreditDetailsDto();
        Date date = new GregorianCalendar(2022, Calendar.JANUARY, 29).getTime();
        LocalDate birthdate = LocalDate.of(1996, Month.JANUARY, 8);
        usersCreditDetailsDto.setId(1L);
        usersCreditDetailsDto.setTCNo(12345678546L);
        usersCreditDetailsDto.setName("Test user name");
        usersCreditDetailsDto.setSurname("Test user surname");
        usersCreditDetailsDto.setSalary(new BigDecimal(17000));
        usersCreditDetailsDto.setBirthdate(birthdate);
        usersCreditDetailsDto.setGuaranteeAmount(new BigDecimal(1000));
        usersCreditDetailsDto.setPhoneNumber("+905554448899");
        usersCreditDetailsDto.setCreditLimit(new BigDecimal(34250));
        usersCreditDetailsDto.setCreditResult(CreditEnum.ONAY);
        usersCreditDetailsDto.setSavedDate(date);
        usersCreditDetailsDtoList.add(usersCreditDetailsDto);

        UsersCreditDetailsDto usersCreditDetailsDto1 = new UsersCreditDetailsDto();
        Date date1 = new GregorianCalendar(2022, Calendar.JANUARY, 30).getTime();
        LocalDate birthdate1 = LocalDate.of(1996, Month.JANUARY, 12);
        usersCreditDetailsDto1.setId(1L);
        usersCreditDetailsDto1.setTCNo(12345678546L);
        usersCreditDetailsDto1.setName("Test user name-1");
        usersCreditDetailsDto1.setSurname("Test user surname");
        usersCreditDetailsDto1.setSalary(new BigDecimal(11000));
        usersCreditDetailsDto1.setBirthdate(birthdate1);
        usersCreditDetailsDto1.setGuaranteeAmount(new BigDecimal(7000));
        usersCreditDetailsDto1.setPhoneNumber("+905554448899");
        usersCreditDetailsDto1.setCreditLimit(new BigDecimal(23750));
        usersCreditDetailsDto1.setCreditResult(CreditEnum.ONAY);
        usersCreditDetailsDto1.setSavedDate(date1);
        usersCreditDetailsDtoList.add(usersCreditDetailsDto1);

        return usersCreditDetailsDtoList;
    }
}
