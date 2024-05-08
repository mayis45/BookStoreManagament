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

@Data
@Entity(name = "cart")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    String id;

    @Column(name = "quantity_of_books")
    BigDecimal quantityOfBooks;

    @Column(name = "total_price")
    BigDecimal totalPrice;

    @Column(name = "book_is_checked")
    Byte bookIsChecked;

    @Column(name = "card_number")
    String cardNumber;

    @Column(name = "card_cvv_code")
    String cardCVVCode;

    @Column(name = "fk_book_id")
    String fkBookId;

}