package com.aysegulapc.graduation.project.user.service.entityService;

import com.aysegulapc.graduation.project.common.service.BaseEntityService;
import com.aysegulapc.graduation.project.user.entity.User;
import com.aysegulapc.graduation.project.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserEntityService extends BaseEntityService<User, UserRepository> {
    public UserEntityService(UserRepository dao) {
        super(dao);
    }

    public User findUserByTCNo(Long tcno) {
        return getRepository().findUserByTCNo(tcno);
    }
}
