package com.aysegulapc.graduation.project.user.service;

import com.aysegulapc.graduation.project.credit.dto.UsersCreditDetailsDto;
import com.aysegulapc.graduation.project.credit.enums.StrategyNames;
import com.aysegulapc.graduation.project.credit.service.entityService.CreditEntityService;
import com.aysegulapc.graduation.project.credit.service.strategyService.CreditStrategy;
import com.aysegulapc.graduation.project.credit.service.strategyService.StrategyFactory;
import com.aysegulapc.graduation.project.notification.entity.SmsRequest;
import com.aysegulapc.graduation.project.notification.service.SmsSenderService;
import com.aysegulapc.graduation.project.user.converter.UserConverter;
import com.aysegulapc.graduation.project.user.dto.UserDto;
import com.aysegulapc.graduation.project.user.dto.UserSaveRequestDto;
import com.aysegulapc.graduation.project.user.entity.User;
import com.aysegulapc.graduation.project.user.exception.UserNotFoundException;
import com.aysegulapc.graduation.project.user.service.entityService.UserEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService {

    private final UserEntityService userEntityService;
    private final StrategyFactory strategyFactory;
    private final CreditEntityService creditEntityService;
    private final SmsSenderService smsSenderService;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public List<UserDto> findAll() {
        List<User> userList = userEntityService.findAll();
        log.info("User list is fetched!");
        List<UserDto> userDtoList = UserConverter.INSTANCE.convertAllUserToUserDtoList(userList);
        logger.info("User Dto list {}", userDtoList);
        return userDtoList;
    }

    public UserDto saveUser(UserSaveRequestDto userSaveRequestDto) {
        User user = UserConverter.INSTANCE.convertUserSaveRequestDtoToUser(userSaveRequestDto);
        List<UserDto> userDtoList = findAll();
        logger.info("User list {}", userDtoList);

        for (UserDto userDto : userDtoList) {
            if(Objects.equals(userDto.getTCNo(), user.getTCNo())) {
                throw new RuntimeException("Bu TC no ile bir kullanıcı zaten kayıtlı!");
            }
        }
        user = userEntityService.save(user);
        calculateCreditResult(user);
        sendSms(user);
        logger.info("Saved user {}", user);
        UserDto userDto = UserConverter.INSTANCE.convertUserToUserDto(user);
        return userDto;
    }

    private void calculateCreditResult(User user) {
        for (StrategyNames strategyName : StrategyNames.values()) {
            try{
                CreditStrategy strategy = strategyFactory.findStrategy(strategyName);
                strategy.calculateCreditResult(user);
            } catch(RuntimeException e) {
                throw new RuntimeException("An error was occured when calculate credit result!");
            }
        }
    }

    public UserDto update(Long id, UserDto userDto) {
        User user = UserConverter.INSTANCE.convertUserDtoToUser(userDto);
        user.setId(id);
        logger.info("Updated user {}", user);
        calculateCreditResult(user);
        userEntityService.save(user);
        userDto = UserConverter.INSTANCE.convertUserToUserDto(user);
        return userDto;
    }

    public void deleteUser(Long id) {
        User user = userEntityService.getById(id);
        if(user == null) {
            throw new UserNotFoundException("User not found!");
        }
        logger.info("Deleted user {}", user);
        userEntityService.delete(user);
        creditEntityService.deleteCreditDetailByUserId(id);
    }

    private void sendSms(User user) {
        List<UsersCreditDetailsDto> creditDetailList = creditEntityService.findAllUsersCreditDetailList();
        DecimalFormat df = new DecimalFormat("#,###");
        creditDetailList.forEach(creditDetail ->{
            if(Objects.equals(user.getTCNo(), creditDetail.getTCNo())) {
                SmsRequest smsRequest = new SmsRequest(user.getPhoneNumber(),
                        ("Kredi limitiniz: " + df.format(creditDetail.getCreditLimit()) + " TL " +
                                "Onay durumu: " + creditDetail.getCreditResult().toString()));
                smsSenderService.sendSms(smsRequest);
            }
        });
    }

}
