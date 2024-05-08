package com.BookStoreManagament.repository;

import com.BookStoreManagament.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {

    @Transactional
    @Modifying
    @Query("update categories a set a.categoryStatus = 1 where a.id = ?1")
    void updateCategoryById(String categoryId);

}
