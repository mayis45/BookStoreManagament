package com.BookStoreManagament.repository;

import com.BookStoreManagament.entity.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<SaleEntity, String> {

    @Query("select a from sale a order by a.totalPrice desc")
    List<SaleEntity> findSaleEntitiesSortTotalPriceHighToLow();

    @Query("select a from sale a order by a.totalPrice")
    List<SaleEntity> findSaleEntitiesSortTotalPriceLowToHigh();

    @Query("select a from sale a order by a.totalProfit desc")
    List<SaleEntity> findSaleEntitiesSortTotalProfitHighToLow();

    @Query("select a from sale a order by a.totalProfit")
    List<SaleEntity> findSaleEntitiesSortTotalProfitLowToHigh();

    @Query("select a from sale a order by a.dateOfPayment")
    List<SaleEntity> findSaleEntitiesSortDateOfPaymentLowToHigh();

    @Query("select a from sale a where a.dateOfPayment between ?1 and ?2")
    List<SaleEntity> findSaleEntitiesFilterByDateOfPaymentWithBetween(LocalDateTime startDate, LocalDateTime endDate);

    @Query("select sum(a.totalProfit) from sale a")
    BigDecimal calculateTotalProfitAllTime();

    @Query("select sum(a.totalProfit) from sale a where a.dateOfPayment between ?1 and ?2")
    BigDecimal calculateTotalProfitBetweenDate(LocalDateTime startDate, LocalDateTime endDate);
}
