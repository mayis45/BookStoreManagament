package com.BookStoreManagament.repository;

import com.BookStoreManagament.entity.PublisherAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface PublisherAccountRepository extends JpaRepository<PublisherAccountEntity, String> {

    @Transactional
    @Modifying
    @Query("""
            update publishers a
            set a.publisherAccountTotalFollowers = a.publisherAccountTotalFollowers + 1 
            where a.id = ?1 
    """)
    void updatePublisherAccountCountOfFollowerById(String publisherId);

    @Transactional
    @Modifying
    @Query("""
            update publishers a
            set a.publisherAccountProfilePhotoUrl = ?2
            where a.id = ?1
    """)
    void changePublisherAccountProfilePhoto(String publisherId, String url);

    @Transactional
    @Modifying
    @Query("""
            update publishers a
            set a.publisherAccountDescription = ?2
            where a.id = ?1
    """)
    void changePublisherAccountDescription(String publisherId, String description);

    @Transactional
    @Modifying
    @Query("""
            update publishers a
            set a.publisherAccountStatus = 0
            where a.id = ?1
    """)
    void deleteAccountStatus(String publisherId);

    @Transactional
    @Modifying
    @Query("""
            update publishers a
            set a.isEnabled = false
            where a.id = ?1
    """)
    void deleteAccountEnabled(String publisherId);

    Optional<PublisherAccountEntity> findPublisherAccountEntityByUsername(String username);
}
