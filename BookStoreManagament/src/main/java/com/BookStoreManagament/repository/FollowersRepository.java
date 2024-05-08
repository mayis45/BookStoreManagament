package com.BookStoreManagament.repository;

import com.BookStoreManagament.entity.FollowersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Repository
public interface FollowersRepository extends JpaRepository<FollowersEntity, String> {

    @Query("""
        select count(a.fkFollowedAccountId) from followers a
        where a.fkFollowedAccountId = :publisherId and a.followStatus = 1 group by a.fkFollowedAccountId
    """)
    BigDecimal findCountTotalFollowersById(@Param("publisherId") String publisherId);

    @Modifying
    @Transactional
    @Query("update followers a set a.followStatus = 1 where a.id = ?1")
    void updateFollowers(String id);
}
