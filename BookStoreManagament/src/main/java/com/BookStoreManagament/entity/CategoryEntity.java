package com.BookStoreManagament.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity(name = "categories")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {
        "category_name",
        "category_status"
})})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    String id;

    @Column(name = "category_name")
    String categoryName;

    @Column(name = "category_status")
    Byte categoryStatus;

    public CategoryEntity(String id, String categoryName, Byte categoryStatus) {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryStatus = categoryStatus;
    }

    public CategoryEntity() {
    }
}
