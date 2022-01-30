package com.aysegulapc.test.user;

import com.aysegulapc.graduation.project.credit.service.entityService.CreditEntityService;
import com.aysegulapc.graduation.project.credit.service.strategyService.StrategyFactory;
import com.aysegulapc.graduation.project.notification.service.SmsSenderService;
import com.aysegulapc.graduation.project.user.dto.UserDto;
import com.aysegulapc.graduation.project.user.entity.User;
import com.aysegulapc.graduation.project.user.service.UserService;
import com.aysegulapc.graduation.project.user.service.entityService.UserEntityService;
import com.aysegulapc.project.user.service.provider.UserDataProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceDenemeTest {
    @Mock
    private UserEntityService userEntityService;

    @Mock
    private StrategyFactory strategyFactory;

    @Mock
    private CreditEntityService creditEntityService;

    @Mock
    private SmsSenderService smsSenderService;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldFindAllUser() {
        //given
        List<User> userList = UserDataProvider.getAllUserList();
        List<UserDto> userDtoList = UserDataProvider.convertUserToSaveRequestDto(userList);

        //when
        when(userEntityService.findAll()).thenReturn(userList);

        //then
        List<UserDto> saveUserDtoList = userService.findAll();
        assertArrayEquals(userDtoList.toArray(), saveUserDtoList.toArray());
    }
}
