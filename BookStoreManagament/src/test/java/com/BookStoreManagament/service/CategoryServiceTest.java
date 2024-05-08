package com.BookStoreManagament.service;

import com.BookStoreManagament.dto.response.AllCategoriesResponseDto;
import com.BookStoreManagament.dto.response.CategoryResponseDto;
import com.BookStoreManagament.entity.CategoryEntity;
import com.BookStoreManagament.exception.CategoryNotFoundException;
import com.BookStoreManagament.mapper.CategoryMapper;
import com.BookStoreManagament.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

class CategoryServiceTest {

    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;
    private CategoryService categoryService;

    @BeforeEach
    public void setUp() {
        categoryRepository = mock(CategoryRepository.class);
        categoryMapper = mock(CategoryMapper.class);
        categoryService = new CategoryService(categoryRepository, categoryMapper);
    }

    @Test
    void testGetAllCategories_whenAllCategoriesIsNotEmpty_shouldReturnAllCategories() {

        List<CategoryEntity> entities = new ArrayList<>();
        entities.add(new CategoryEntity("id", "kategori", (byte)1));
        List<AllCategoriesResponseDto> dtos = new ArrayList<>();
        dtos.add(new AllCategoriesResponseDto("kategori"));

        when(categoryRepository.findAll()).thenReturn(entities);

        List<AllCategoriesResponseDto> result = categoryService.getAllCategories();

        Assertions.assertEquals(result, dtos);
    }

    @Test
    void testGetAllCategories_whenAllCategoriesIsEmpty_shouldThrowCategoryNotFound() {
        List<CategoryEntity> entities = new ArrayList<>();

        when(categoryRepository.findAll()).thenReturn(entities);

        assertThrows(CategoryNotFoundException.class,
                () -> categoryService.getAllCategories());
    }

    @Test
    void testGetCategoryById_whenCategoryIdExist_shouldReturnCategorty() {
        CategoryEntity category = new CategoryEntity("id", "kategori", (byte)1);
        CategoryResponseDto dto = new CategoryResponseDto( "kategori");

        when(categoryRepository.findById("id")).thenReturn(Optional.of(category));
        when(categoryMapper.toCategoryResponseDto(category)).thenReturn(dto);

        CategoryResponseDto result = categoryService.getCategoryById("id");

        Assertions.assertEquals(result, dto);
    }

    @Test
    void testGetCategoryById_whenCategoryIdDoesNotExist_shouldThrowCategoryNotFound() {
        when(categoryRepository.findById("id")).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class,
                () -> categoryService.getCategoryById("id"));
    }
}