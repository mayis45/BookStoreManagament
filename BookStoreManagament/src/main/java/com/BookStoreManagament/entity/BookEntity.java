package com.BookStoreManagament.entity;

import com.BookStoreManagament.repository.PublisherAccountRepository;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity(name = "books")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    String id;

    @Column(name = "book_name")
    String bookName;

    @Column(name = "book_price")
    BigDecimal bookPrice;

    @Column(name = "book_photo_url")
    String bookPhotoUrl;

    @Column(name = "book_sold_quantity")
    BigDecimal bookSoldQuantity;

    @Column(name = "publishing_date")
    LocalDate publishingDate;

    @Column(name = "book_description")
    String bookDescription;

    @Column(name = "book_language")
    String bookLanguage;

    @Column(name = "pages_of_book")
    String pagesOfBook;

    @Column(name = "book_status")
    Byte bookStatus;

    @Column(name = "fk_category_id")
    String fkCategoryId;

    @Column(name = "fk_publisher_id")
    String fkPublisherId;

    public BookEntity(String id, String bookName, BigDecimal bookPrice, String bookPhotoUrl, BigDecimal bookSoldQuantity, LocalDate publishingDate, String bookDescription, String bookLanguage, String pagesOfBook, Byte bookStatus, String fkCategoryId, String fkPublisherId) {
        this.id = id;
        this.bookName = bookName;
        this.bookPrice = bookPrice;
        this.bookPhotoUrl = bookPhotoUrl;
        this.bookSoldQuantity = bookSoldQuantity;
        this.publishingDate = publishingDate;
        this.bookDescription = bookDescription;
        this.bookLanguage = bookLanguage;
        this.pagesOfBook = pagesOfBook;
        this.bookStatus = bookStatus;
        this.fkCategoryId = fkCategoryId;
        this.fkPublisherId = fkPublisherId;
    }

    public BookEntity() {
    }
}
