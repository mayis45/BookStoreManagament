package com.BookStoreManagament.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;


@Data
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"authority"})})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    String id;

    @Column(name = "authority")
    String authority;

    @Column(name = "authority_status")
    Byte authorityStatus;

    public RoleEntity(String id, String authority, Byte authorityStatus) {
        this.id = id;
        this.authority = authority;
        this.authorityStatus = authorityStatus;
    }

    public RoleEntity() {
    }
}
