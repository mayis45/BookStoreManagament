package com.BookStoreManagament.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity(name = "publishers")
@Table(
        uniqueConstraints = {@UniqueConstraint(columnNames = {
                "username"
        })}
)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PublisherAccountEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    String id;

    @Column(name = "publisher_account_name")
    String publisherAccountName;

    @Column(name = "publisher_account_description")
    String publisherAccountDescription;

    @Column(name = "publisher_account_profile_photo_url")
    String publisherAccountProfilePhotoUrl;

    @Column(name = "publisher_account_email")
    String publisherAccountEmail;

    @Column(name = "publisher_account_total_followers")
    BigDecimal publisherAccountTotalFollowers;

    @Column(name = "publisher_account_creation_date")
    LocalDate publisherAccountCreationDate;

    @Column(name = "publisher_account_status")
    Byte publisherAccountStatus;

    @Column(name = "total_book_count")
    BigDecimal totalBookCount;

    @Column(name = "username")
    String username;

    String password;

    boolean isEnabled;

    boolean isAccountNonExpired;

    boolean isAccountNonLocked;

    boolean isCredentialsNonExpired;

    String authority;

    public PublisherAccountEntity(String id, String publisherAccountName, String publisherAccountDescription, String publisherAccountProfilePhotoUrl, String publisherAccountEmail, BigDecimal publisherAccountTotalFollowers, LocalDate publisherAccountCreationDate, Byte publisherAccountStatus, BigDecimal totalBookCount, String username, String password, boolean isEnabled, boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired, String authority) {
        this.id = id;
        this.publisherAccountName = publisherAccountName;
        this.publisherAccountDescription = publisherAccountDescription;
        this.publisherAccountProfilePhotoUrl = publisherAccountProfilePhotoUrl;
        this.publisherAccountEmail = publisherAccountEmail;
        this.publisherAccountTotalFollowers = publisherAccountTotalFollowers;
        this.publisherAccountCreationDate = publisherAccountCreationDate;
        this.publisherAccountStatus = publisherAccountStatus;
        this.totalBookCount = totalBookCount;
        this.username = username;
        this.password = password;
        this.isEnabled = isEnabled;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.authority = authority;
    }

    public PublisherAccountEntity() {
    }
}
