package com.BookStoreManagament.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "followers")
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {
                        "fk_followed_account_id",
                        "fk_follower_account_id",
                        "follow_status"}
                )
        }
)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FollowersEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    String  id;

    @Column(name = "follow_status")
    Byte followStatus;

    @Column(name = "fk_followed_account_id")
    String fkFollowedAccountId;

    @Column(name = "fk_follower_account_id")
    String fkFollowerAccountId;

}