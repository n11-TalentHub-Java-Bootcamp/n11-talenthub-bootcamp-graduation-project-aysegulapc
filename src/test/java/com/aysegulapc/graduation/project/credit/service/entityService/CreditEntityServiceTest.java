package com.aysegulapc.graduation.project.credit.service.entityService;

import com.aysegulapc.graduation.project.credit.dto.UsersCreditDetailsDto;
import com.aysegulapc.graduation.project.credit.entity.CreditResponse;
import com.aysegulapc.graduation.project.credit.repository.CreditRepository;
import com.aysegulapc.graduation.project.credit.service.UserCreditScoreService;
import com.aysegulapc.graduation.project.user.entity.User;
import com.aysegulapc.graduation.project.user.repository.UserRepository;
import com.aysegulapc.graduation.project.user.service.entityService.UserEntityService;
import com.aysegulapc.project.credit.service.strategyService.strategies.provider.CreditDataProvider;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Ignore;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class CreditEntityServiceTest {

    @Mock
    private UserEntityService userEntityService;

    @Mock
    private UserCreditScoreService userCreditScoreService;

    @Mock
    private CreditRepository creditRepository;

    @InjectMocks
    private CreditEntityService creditEntityService;

    @Test
    void shouldReturnAllUsersCreditDetailList() {
        List<UsersCreditDetailsDto> usersCreditDetailsDtoList = CreditDataProvider.getUsersCreditDetailsDtoList();

        when(creditRepository.findAllUsersCreditDetailList()).thenReturn(usersCreditDetailsDtoList);
        List<UsersCreditDetailsDto> resultList = creditEntityService.findAllUsersCreditDetailList();

        assertEquals(1, resultList.size());
        assertEquals(resultList.get(0).getName(), "Test user name-1");
        assertEquals(resultList.get(0).getSalary(), new BigDecimal(11000));
        assertEquals(resultList.get(0).getGuaranteeAmount(), new BigDecimal(7000));
        assertEquals(resultList.get(0).getCreditLimit(), new BigDecimal(23750));
    }

    @Test @Disabled
    void shouldReturnUserByTcNoAndBirthdate() {
        List<UsersCreditDetailsDto> usersCreditDetailsDtoList = CreditDataProvider.getUsersCreditDetailsDtoList();
        User user = CreditDataProvider.getUser();
        CreditResponse creditResponse = CreditDataProvider.getCreditResponse(user.getId());

        Long tcno = usersCreditDetailsDtoList.get(0).getTCNo();
        LocalDate birthdate = usersCreditDetailsDtoList.get(0).getBirthdate();

        when(userEntityService.findUserByTCNo(tcno)).thenReturn(user);
        when(creditRepository.findAllUsersCreditDetailList()).thenReturn(usersCreditDetailsDtoList);
        when(creditRepository.findCreditResponseByUserId(user.getId())).thenReturn(creditResponse);

        UsersCreditDetailsDto resultUser = creditEntityService.findUserByTcNoAndBirthdate(tcno, birthdate);

        assertEquals(tcno, resultUser.getTCNo());
        assertEquals(birthdate, resultUser.getBirthdate());
    }
}