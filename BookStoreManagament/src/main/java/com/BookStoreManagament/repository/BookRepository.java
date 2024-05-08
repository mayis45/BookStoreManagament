package com.BookStoreManagament.repository;

import com.BookStoreManagament.dto.response.SearchedBookResponseDto;
import com.BookStoreManagament.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, String> {

    @Query("select a from books a order by a.bookPrice")
    List<BookEntity> findBooksSortByPriceLowToHigh();
    @Query("select a from books a order by a.bookPrice desc ")
    List<BookEntity> findBooksSortByPriceHighToLow();
    @Query("select a from books a order by a.publishingDate desc")
    List<BookEntity> findBooksSortByPublishingDate();
    @Query("select a from books a order by a.bookSoldQuantity desc")
    List<BookEntity> findBooksSortBySoldQuantityHighToLow();
    @Query("select a from books a order by a.bookSoldQuantity")
    List<BookEntity> findBooksSortBySoldQuantityLowToHigh();
    @Query("select count(a) from books a where a.fkPublisherId = ?1")
    BigDecimal calculateTotalBookCountByPublisherId(String publisherId);
    @Transactional
    @Modifying
    @Query("update books a set a.bookSoldQuantity = a.bookSoldQuantity + ?2 where a.id = ?1")
        void updateBookEntityByBookSoldQuantity(String id, BigDecimal soldQuantity);
    List<BookEntity> findBookEntitiesByFkCategoryId(String categoryId);
    List<BookEntity> findBookEntitiesByFkPublisherId(String publisherId);
    Optional<BookEntity> findBookEntityByBookName(String bookName);
}
