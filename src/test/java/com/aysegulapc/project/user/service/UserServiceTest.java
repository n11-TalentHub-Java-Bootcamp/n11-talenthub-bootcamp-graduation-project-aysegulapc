package com.aysegulapc.project.user.service;

import com.aysegulapc.graduation.project.credit.enums.StrategyNames;
import com.aysegulapc.graduation.project.credit.service.entityService.CreditEntityService;
import com.aysegulapc.graduation.project.credit.service.strategyService.CreditStrategy;
import com.aysegulapc.graduation.project.credit.service.strategyService.StrategyFactory;
import com.aysegulapc.graduation.project.credit.service.strategyService.strategies.FirstCreditScoreService;
import com.aysegulapc.graduation.project.user.converter.UserConverter;
import com.aysegulapc.graduation.project.user.dto.UserDto;
import com.aysegulapc.graduation.project.user.dto.UserSaveRequestDto;
import com.aysegulapc.graduation.project.user.entity.User;
import com.aysegulapc.graduation.project.user.service.UserService;
import com.aysegulapc.graduation.project.user.service.entityService.UserEntityService;
import com.aysegulapc.project.user.service.provider.UserDataProvider;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

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

    @Mock
    private CreditEntityService creditEntityService;

    @InjectMocks
    private UserService userService;

    private Map<StrategyNames, CreditStrategy> strategies;

    @Test
    void shouldFindAllUser() {
        List<User> userList = UserDataProvider.getAllUserList();
        List<UserDto> userDtoList = UserDataProvider.convertUserToUserDtoList(userList);

        when(userEntityService.findAll()).thenReturn(userList);

        List<UserDto> saveUserDtoList = userService.findAll();
        assertArrayEquals(userDtoList.toArray(), saveUserDtoList.toArray());
    }

    @Test @Disabled
    void shouldNotSaveUser() {
        UserSaveRequestDto userSaveRequestDto = UserDataProvider.getUserSaveRequestDto();
        User user = UserDataProvider.convertUserSaveRequestDtoToUser(userSaveRequestDto);
        List<User> userList = UserDataProvider.getAllUserList();
        List<UserDto> userDtoList = UserDataProvider.convertUserToUserDtoList(userList);

        when(userService.findAll()).thenReturn(userDtoList);
        //when(userService.saveUser(userSaveRequestDto)).thenReturn(userDto);
        userService.saveUser(userSaveRequestDto);

        assertThrows(RuntimeException.class, () -> userService.findAll());

    }

    @Test @Disabled
    void shouldSaveUser() {
        UserSaveRequestDto userSaveRequestDto = UserDataProvider.getUserSaveRequestDto();
        User user = UserDataProvider.convertUserSaveRequestDtoToUser(userSaveRequestDto);
        List<User> userList = UserDataProvider.getAllUserList();
        StrategyNames strategyName = StrategyNames.FirstCreditScoreService;

        UserDataProvider.convertUserToUserDtoList(userList);

        CreditStrategy strategy = strategyFactory.findStrategy(strategyName);
        strategies = new HashMap<StrategyNames, CreditStrategy>();
        strategies.put(strategyName, strategy);

        when(strategyFactory.findStrategy(strategyName))
                .thenReturn(strategies.get(strategyName));
        when(userEntityService.save(user)).thenReturn(user);
        when(firstCreditScoreService.getStrategy()).thenReturn(strategyName);

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