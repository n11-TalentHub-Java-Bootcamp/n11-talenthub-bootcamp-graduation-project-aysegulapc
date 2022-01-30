package com.aysegulapc.graduation.project.credit.service.entityService;

import com.aysegulapc.graduation.project.common.service.BaseEntityService;
import com.aysegulapc.graduation.project.credit.dto.UsersCreditDetailsDto;
import com.aysegulapc.graduation.project.credit.entity.CreditResponse;
import com.aysegulapc.graduation.project.credit.repository.CreditRepository;
import com.aysegulapc.graduation.project.credit.service.UserCreditScoreService;
import com.aysegulapc.graduation.project.user.converter.UserConverter;
import com.aysegulapc.graduation.project.user.dto.UserDto;
import com.aysegulapc.graduation.project.user.entity.User;
import com.aysegulapc.graduation.project.user.service.entityService.UserEntityService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@CrossOrigin
@Slf4j
public class CreditEntityService extends BaseEntityService<CreditResponse, CreditRepository> {
    private UserEntityService userEntityService;
    private UserCreditScoreService userCreditScoreService;

    private static final Logger logger = LoggerFactory.getLogger(CreditEntityService.class);

    public CreditEntityService(CreditRepository repository) {
        super(repository);
    }

    public Long getUserCreditScore(UserDto userdto) {
        User user = UserConverter.INSTANCE.convertUserDtoToUser(userdto);
        return userCreditScoreService.findCreditScore(user);
    }

    public List<UsersCreditDetailsDto> findAllUsersCreditDetailList() {
        List<UsersCreditDetailsDto> usersCreditDetailList = getRepository().findAllUsersCreditDetailList();
        logger.info("Users Credit Detail List {}", usersCreditDetailList);
        return new ArrayList<>(usersCreditDetailList.stream()
                .collect(Collectors.toMap(UsersCreditDetailsDto::getId,
                        Function.identity(),
                        (user, user2) -> user.getSavedDate().compareTo(user2.getSavedDate()) > 0 ? user : user2))
                .values());
    }

    public UsersCreditDetailsDto findUserByTcNoAndBirthdate(Long tcno, LocalDate birthdate) {
        List<UsersCreditDetailsDto> usersCreditDetailList = getRepository().findAllUsersCreditDetailList();
        UsersCreditDetailsDto resultUser = new UsersCreditDetailsDto();
        logger.info("Users Credit Detail List for search user by birthdate and tcno {}", usersCreditDetailList);

        for (UsersCreditDetailsDto usersCreditDetailsDto : usersCreditDetailList) {
            if(Objects.equals(usersCreditDetailsDto.getTCNo(), tcno)
                    && Objects.equals(usersCreditDetailsDto.getBirthdate(), birthdate)) {
                logger.info("User's tcNo {} and birthdate {}", tcno, birthdate);
                User user = userEntityService.findUserByTCNo(tcno);
                CreditResponse creditResponse = getRepository().findCreditResponseByUserId(user.getId());
                resultUser.setId(user.getId());
                resultUser.setName(user.getName());
                resultUser.setSurname(user.getSurname());
                resultUser.setBirthdate(user.getBirthdate());
                resultUser.setTCNo(user.getTCNo());
                resultUser.setPhoneNumber(user.getPhoneNumber());
                resultUser.setSalary(user.getSalary());
                resultUser.setGuaranteeAmount(user.getGuaranteeAmount());
                resultUser.setCreditResult(creditResponse.getCreditResult());
                resultUser.setCreditLimit(creditResponse.getCreditLimit());
            }
        }
        return resultUser;
    }

    public void deleteCreditDetailByUserId(Long userId) {
        getRepository().deleteCreditResponseByUserId(userId);
    }
}
