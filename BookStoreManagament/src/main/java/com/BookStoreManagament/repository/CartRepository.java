package com.BookStoreManagament.repository;

import com.BookStoreManagament.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, String> {

    @Transactional
    @Modifying
    @Query("update cart a set a.bookIsChecked = 0 where a.id = ?1")
    void unCheckBookById(String id);

    @Transactional
    @Modifying
    @Query("update cart a set a.bookIsChecked = 1 where a.id = ?1")
    void checkBookById(String id);

    @Transactional
    @Modifying
    @Query("update cart a set a.quantityOfBooks = a.quantityOfBooks + 1 where a.id = ?1")
    void increementQuantityOfBookById(String id);

    @Transactional
    @Modifying
    @Query("update cart a set a.totalPrice = a.totalPrice + ?2 where a.id = ?1")
    void whenIncreementQuantityOfBookByIdIncreaseTotalPrice(String id, BigDecimal price);
    @Transactional
    @Modifying
    @Query("update cart a set a.quantityOfBooks = a.quantityOfBooks - 1 where a.id = ?1")
    void decreementQuantityOfBookById(String id);

    @Transactional
    @Modifying
    @Query("update cart a set a.totalPrice = a.totalPrice - ?2 where a.id = ?1")
    void whenDecreementQuantityOfBookByIdIncreaseTotalPrice(String id, BigDecimal price);

    @Transactional
    @Modifying
    @Query("update cart a set a.quantityOfBooks = ?2 where a.id = ?1")
    void modifyQuantityOfBookById(String id, BigDecimal quantity);

    @Transactional
    @Modifying
    @Query("update cart a set a.totalPrice = a.quantityOfBooks * ?2 where a.id = ?1")
    void whenModifyQuantityOfBookByIdIncreaseTotalPrice(String id, BigDecimal price);

    @Transactional
    @Modifying
    @Query("update cart a set a.cardNumber = ?2 where a.id = ?1")
    void addCardNo(String id, String cardNumber);

    @Transactional
    @Modifying
    @Query("update cart a set a.cardCVVCode = ?2 where a.id = ?1")
    void addCardCVVCode(String id, String cardCVVCode);

    @Query("select sum(a.totalPrice) from cart a where a.bookIsChecked = 1")
    BigDecimal findTotalPriceWhereBookIsChecked();
}
