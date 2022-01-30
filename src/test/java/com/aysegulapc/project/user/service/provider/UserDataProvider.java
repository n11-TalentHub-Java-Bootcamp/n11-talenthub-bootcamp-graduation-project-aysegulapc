package com.aysegulapc.project.user.service.provider;

import com.aysegulapc.graduation.project.credit.entity.CreditResponse;
import com.aysegulapc.graduation.project.credit.enums.CreditEnum;
import com.aysegulapc.graduation.project.user.converter.UserConverter;
import com.aysegulapc.graduation.project.user.dto.UserDto;
import com.aysegulapc.graduation.project.user.dto.UserSaveRequestDto;
import com.aysegulapc.graduation.project.user.entity.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDataProvider {

    public static List<UserDto> convertUserToUserDtoList(List<User> userList) {
        return UserConverter.INSTANCE.convertAllUserToUserDtoList(userList);
    }

    public static User convertUserDtoToUser(UserDto userDto) {
        return UserConverter.INSTANCE.convertUserDtoToUser(userDto);
    }

    public static UserDto convertUserToUserDto(User user) {
        return UserConverter.INSTANCE.convertUserToUserDto(user);
    }

    public static User convertUserSaveRequestDtoToUser(UserSaveRequestDto userSaveRequestDto) {
        return UserConverter.INSTANCE.convertUserSaveRequestDtoToUser(userSaveRequestDto);
    }

    public static User getUser() {
        User user = new User();
        user.setId(1L);
        user.setName("Test name-1");
        user.setSurname("Test surname-1");
        user.setSalary(new BigDecimal(6000));
        user.setPhoneNumber("+905554443311");
        user.setBirthdate(null);
        user.setGuaranteeAmount(new BigDecimal(4000));
        user.setTCNo(12345678678L);

        return user;
    }

    public static User getFirstStrategyUser() {
        User user = new User();
        user.setId(1L);
        user.setName("Test name-1");
        user.setSurname("Test surname-1");
        user.setSalary(new BigDecimal(6000));
        user.setPhoneNumber("+905554443311");
        user.setBirthdate(null);
        user.setGuaranteeAmount(new BigDecimal(4000));
        user.setTCNo(12345678123L);

        return user;
    }

    public static User getGuaranteeAmountIsNullUser() {
        User user = new User();
        user.setId(1L);
        user.setName("Test name-1");
        user.setSurname("Test surname-1");
        user.setSalary(new BigDecimal(6000));
        user.setPhoneNumber("+905554443311");
        user.setBirthdate(null);
        user.setGuaranteeAmount(null);
        user.setTCNo(12345678523L);

        return user;
    }

    public static User getSecondStrategyUser() {
        User user = new User();
        user.setId(1L);
        user.setName("Test name-1");
        user.setSurname("Test surname-1");
        user.setSalary(new BigDecimal(4000));
        user.setPhoneNumber("+905554443311");
        user.setBirthdate(null);
        user.setGuaranteeAmount(new BigDecimal(4000));
        user.setTCNo(12345678678L);

        return user;
    }

    public static User getThirdStrategyUser() {
        User user = new User();
        user.setId(1L);
        user.setName("Test name-1");
        user.setSurname("Test surname-1");
        user.setSalary(new BigDecimal(7000));
        user.setPhoneNumber("+905554443311");
        user.setBirthdate(null);
        user.setGuaranteeAmount(new BigDecimal(4000));
        user.setTCNo(12345678678L);

        return user;
    }

    public static User getFourthStrategyUser() {
        User user = new User();
        user.setId(1L);
        user.setName("Test name-1");
        user.setSurname("Test surname-1");
        user.setSalary(new BigDecimal(11000));
        user.setPhoneNumber("+905554443311");
        user.setBirthdate(null);
        user.setGuaranteeAmount(new BigDecimal(4000));
        user.setTCNo(12345678678L);

        return user;
    }

    public static User getFifthStrategyUser() {
        User user = new User();
        user.setId(1L);
        user.setName("Test name-1");
        user.setSurname("Test surname-1");
        user.setSalary(new BigDecimal(6000));
        user.setPhoneNumber("+905554443311");
        user.setBirthdate(null);
        user.setGuaranteeAmount(new BigDecimal(4000));
        user.setTCNo(12345678034L);

        return user;
    }

    public static User getUpdatedUser() {
        User user = new User();
        user.setId(1L);
        user.setName("Test name-2");
        user.setSurname("Test surname");
        user.setSalary(new BigDecimal(7000));
        user.setPhoneNumber("+905554443311");
        user.setBirthdate(null);
        user.setGuaranteeAmount(new BigDecimal(4000));
        user.setTCNo(12345678678L);

        return user;
    }

    public static UserDto getUserDto(Long id) {
        return UserDto.builder()
                .id(id)
                .name("Test name")
                .surname("Test surname")
                .birthdate(null)
                .phoneNumber("+905554442211")
                .salary(new BigDecimal(5000))
                .TCNo(12345678678L)
                .guaranteeAmount(new BigDecimal(4000))
                .build();
    }

    public static UserSaveRequestDto getUserSaveRequestDto() {
        return UserSaveRequestDto.builder()
                .name("Test name")
                .surname("Test surname")
                .birthdate(null)
                .phoneNumber("+905554442211")
                .salary(new BigDecimal(5000))
                .TCNo(12345678123L)
                .guaranteeAmount(new BigDecimal(2000))
                .build();
    }

    public static List<UserDto> getUserDtoList() {
        List<UserDto> userDtoList = new ArrayList<>();

        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setName("Test name-1");
        userDto.setSurname("Test surname-1");
        userDto.setSalary(new BigDecimal(5000));
        userDto.setPhoneNumber("+905554443311");
        userDto.setTCNo(12345678123L);
        userDto.setBirthdate(null);
        userDto.setGuaranteeAmount(new BigDecimal(4000));
        userDtoList.add(userDto);

        return userDtoList;
    }

    public static List<User> getAllUserList(){

        List<User> userList = new ArrayList<>();

        User user1 = new User();
        user1.setId(1L);
        user1.setName("Test name-1");
        user1.setSurname("Test surname-1");
        user1.setSalary(new BigDecimal(5000));
        user1.setPhoneNumber("+905554443311");
        user1.setBirthdate(null);
        user1.setGuaranteeAmount(new BigDecimal(4000));
        user1.setTCNo(12345678123L);
        userList.add(user1);

        User user2 = new User();
        user1.setId(2L);
        user1.setName("Test name-2");
        user1.setSurname("Test surname-2");
        user1.setSalary(new BigDecimal(6000));
        user1.setPhoneNumber("+905554443322");
        user1.setBirthdate(null);
        user1.setGuaranteeAmount(new BigDecimal(5000));
        user1.setTCNo(12345678124L);
        userList.add(user2);

        return userList;
    }

    public static List<UserDto> getAllUserDtoList(){

        List<UserDto> userDtoList = new ArrayList<>();

        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setName("Test name-1");
        userDto.setSurname("Test surname-1");
        userDto.setSalary(new BigDecimal(5000));
        userDto.setPhoneNumber("+905554443311");
        userDto.setBirthdate(null);
        userDto.setGuaranteeAmount(new BigDecimal(4000));
        userDto.setTCNo(12345678123L);
        userDtoList.add(userDto);

        UserDto userDto1 = new UserDto();
        userDto1.setId(2L);
        userDto1.setName("Test name-2");
        userDto1.setSurname("Test surname-2");
        userDto1.setSalary(new BigDecimal(6000));
        userDto1.setPhoneNumber("+905554443322");
        userDto1.setBirthdate(null);
        userDto1.setGuaranteeAmount(new BigDecimal(5000));
        userDto1.setTCNo(12345678124L);
        userDtoList.add(userDto1);

        return userDtoList;
    }
}
