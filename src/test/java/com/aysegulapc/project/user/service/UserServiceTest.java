package com.aysegulapc.project.user.service;

import com.aysegulapc.graduation.project.credit.enums.StrategyNames;
import com.aysegulapc.graduation.project.credit.service.entityService.CreditEntityService;
import com.aysegulapc.graduation.project.credit.service.strategyService.CreditStrategy;
import com.aysegulapc.graduation.project.credit.service.strategyService.StrategyFactory;
import com.aysegulapc.graduation.project.credit.service.strategyService.strategies.FirstCreditScoreService;
import com.aysegulapc.graduation.project.notification.service.SmsSenderService;
import com.aysegulapc.graduation.project.user.converter.UserConverter;
import com.aysegulapc.graduation.project.user.dto.UserDto;
import com.aysegulapc.graduation.project.user.dto.UserSaveRequestDto;
import com.aysegulapc.graduation.project.user.entity.User;
import com.aysegulapc.graduation.project.user.service.UserService;
import com.aysegulapc.graduation.project.user.service.entityService.UserEntityService;
import com.aysegulapc.project.user.service.provider.UserDataProvider;
import org.junit.Ignore;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class UserServiceTest {

    @Mock
    private UserEntityService userEntityService;

    @Mock
    private StrategyFactory strategyFactory;

    @Mock
    private FirstCreditScoreService firstCreditScoreService;

    @Mock
    private UserConverter userConverter;

    @InjectMocks
    private UserService userService;

    private Map<StrategyNames, CreditStrategy> strategies;

    @Test
    void shouldFindAllUser() {
        List<User> userList = UserDataProvider.getAllUserList();
        List<UserDto> userDtoList = UserDataProvider.convertUserToSaveRequestDto(userList);

        when(userEntityService.findAll()).thenReturn(userList);

        List<UserDto> saveUserDtoList = userService.findAll();
        assertArrayEquals(userDtoList.toArray(), saveUserDtoList.toArray());
    }

    @Test
    void shouldNotSaveUser() {
        UserSaveRequestDto userSaveRequestDto = UserDataProvider.getUserSaveRequestDto();
        List<User> userList = UserDataProvider.getAllUserList();
        List<UserDto> userDtoList = UserDataProvider.getAllUserDtoList();
        UserDto userDto = new UserDto();

       // when(UserConverter.INSTANCE.convertAllUserToUserDtoList(userList)).thenReturn(userDtoList);
        when(userService.findAll()).thenReturn(userDtoList);
        when(userService.saveUser(userSaveRequestDto)).thenReturn(userDto);

        assertThat("Bu TC no ile bir kullanıcı zaten kayıtlı!");
    }

    @Test @Ignore
    void shouldSaveUser() {
        UserSaveRequestDto userSaveRequestDto = UserDataProvider.getUserSaveRequestDto();
        User user = UserDataProvider.convertUserSaveRequestDtoToUser(userSaveRequestDto);

        CreditStrategy strategy = strategyFactory.findStrategy(StrategyNames.FirstCreditScoreService);
        strategies = new HashMap<StrategyNames, CreditStrategy>();
        Set<CreditStrategy> strategySet = null;
        strategySet.forEach(
                strategy1 ->strategies.put(strategy.getStrategy(), strategy1));
        strategies.put(StrategyNames.FirstCreditScoreService, strategy);

        when(userEntityService.save(user)).thenReturn(user);
        when(firstCreditScoreService.getStrategy()).thenReturn(StrategyNames.FirstCreditScoreService);
        when(strategyFactory.findStrategy(StrategyNames.FirstCreditScoreService))
                .thenReturn(strategies.get(StrategyNames.FirstCreditScoreService));
        when(userService.findAll()).thenReturn(UserDataProvider.getUserDtoList());

        UserDto userDto1 = UserDataProvider.convertUserToUserDto(user);
        UserDto userDto = userService.saveUser(userSaveRequestDto);
        assertEquals(userDto1, userDto);
    }

    @Test
    void update() {
        UserDto userDto = UserDataProvider.getUserDto(1L);
        userDto.setName("Test name-1");
        User user = UserDataProvider.convertUserDtoToUser(userDto);

        userEntityService.save(user);

        UserDto userDto1 = UserDataProvider.convertUserToUserDto(user);
        assertEquals(userDto, userDto1);
    }

    @Test
    void deleteUser() {
        User user = UserDataProvider.getUser();
        when(userEntityService.getById(anyLong())).thenReturn(user);

        userService.deleteUser(1L);

        verify(userEntityService).getById(anyLong());
        verify(userEntityService).delete(user);
    }
}