package com.BookStoreManagament.repository;

import com.BookStoreManagament.entity.DepotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DepotRepository extends JpaRepository<DepotEntity, String> {

    @Query("select a from depot a order by a.initialPrice desc")
    List<DepotEntity> findDepotEntitiesBySortInitialPriceHighToLow();

    @Query("select a from depot a order by a.initialPrice")
    List<DepotEntity> findDepotEntitiesBySortInitialPriceLowToHigh();

    @Query("select a from depot a where a.importDate between ?1 and ?2")
    List<DepotEntity> findDepotEntitiesFilterByImportDateWithBetween(LocalDate startDate, LocalDate endDate);

}