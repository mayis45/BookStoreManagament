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
import java.time.LocalDateTime;

@Data
@Entity(name = "sale")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SaleEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    String id;

    @Column(name = "sender_card_number")
    String senderCardNumber;

    @Column(name = "date_of_payment")
    LocalDateTime dateOfPayment;

    @Column(name = "quantity_of_books")
    BigDecimal quantityOfBooks;

    @Column(name = "total_price")
    BigDecimal totalPrice;

    @Column(name = "total_profit")
    BigDecimal totalProfit;

    @Column(name = "fk_book_id")
    String fkBookId;

    public SaleEntity(String id, String senderCardNumber, LocalDateTime dateOfPayment, BigDecimal quantityOfBooks, BigDecimal totalPrice, BigDecimal totalProfit, String fkBookId) {
        this.id = id;
        this.senderCardNumber = senderCardNumber;
        this.dateOfPayment = dateOfPayment;
        this.quantityOfBooks = quantityOfBooks;
        this.totalPrice = totalPrice;
        this.totalProfit = totalProfit;
        this.fkBookId = fkBookId;
    }

    public SaleEntity() {
    }
}
