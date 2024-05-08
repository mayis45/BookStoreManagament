package com.BookStoreManagament.controller;

import com.BookStoreManagament.dto.request.AddCategoryRequestDto;
import com.BookStoreManagament.dto.response.AllCategoriesResponseDto;
import com.BookStoreManagament.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/category-api")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/allcategories")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public List<AllCategoriesResponseDto> getAllCategories()
    {
        return categoryService.getAllCategories();
    }

    @PostMapping("/addcategory")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void addNewCategory(@RequestBody @Valid AddCategoryRequestDto request) {
        categoryService.addNewCategory(request);
    }

    @PutMapping("/deletecategory/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteCategoryById(@PathVariable String id)
    {
        categoryService.deleteCategoryById(id);
    }

}
