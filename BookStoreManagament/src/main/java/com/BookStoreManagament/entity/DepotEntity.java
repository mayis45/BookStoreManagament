package com.BookStoreManagament.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity(name = "depot")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DepotEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    String id;

    @Column(name = "quantity_of_books")
    BigDecimal quantityOfBooks;

    @Column(name = "initial_price")
    BigDecimal initialPrice;

    @Column(name = "import_date")
    LocalDate importDate;

    @Column(name = "fk_book_id")
    String fkBookId;

    @Column(name = "fk_category_id")
    String fkCategoryId;

    public DepotEntity(String id, BigDecimal quantityOfBooks, BigDecimal initialPrice, LocalDate importDate, String fkBookId, String fkCategoryId) {
        this.id = id;
        this.quantityOfBooks = quantityOfBooks;
        this.initialPrice = initialPrice;
        this.importDate = importDate;
        this.fkBookId = fkBookId;
        this.fkCategoryId = fkCategoryId;
    }

    public DepotEntity() {
    }
}
