package com.aysegulapc.graduation.project.user.converter;

import com.aysegulapc.graduation.project.user.dto.UserDto;
import com.aysegulapc.graduation.project.user.dto.UserSaveRequestDto;
import com.aysegulapc.graduation.project.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserConverter {
    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    List<UserDto> convertAllUserToUserDtoList(List<User> userList);

    User convertUserSaveRequestDtoToUser(UserSaveRequestDto userSaveRequestDto);

    UserSaveRequestDto convertUserToUserSaveRequestDto(User user);

    UserDto convertUserToUserDto(User user);

    User convertUserDtoToUser(UserDto userDto);
}
