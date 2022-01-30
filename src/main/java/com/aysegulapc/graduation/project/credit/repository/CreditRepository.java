package com.aysegulapc.graduation.project.credit.repository;

import com.aysegulapc.graduation.project.credit.dto.UsersCreditDetailsDto;
import com.aysegulapc.graduation.project.credit.entity.CreditResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditRepository extends JpaRepository<CreditResponse, Long> {

    @Query("select " +
            " new com.aysegulapc.graduation.project.credit.dto.UsersCreditDetailsDto( " +
            " user.id, " +
            " user.TCNo, " +
            " user.name, " +
            " user.surname, " +
            " user.salary, " +
            " user.phoneNumber, " +
            " user.birthdate, " +
            " user.guaranteeAmount," +
            " credit.creditLimit," +
            " credit.creditResult," +
            " credit.savedDate " +
            " ) " +
            " from User user " +
            " left join CreditResponse credit " +
            " on credit.userId = user.id ")
    List<UsersCreditDetailsDto> findAllUsersCreditDetailList();

    CreditResponse findCreditResponseByUserId(Long userId);

    void deleteCreditResponseByUserId(@Param("userId") Long userId);
}
