package com.BookStoreManagament.service;

import com.BookStoreManagament.dto.request.AddCategoryRequestDto;
import com.BookStoreManagament.dto.response.AllCategoriesResponseDto;
import com.BookStoreManagament.dto.response.CategoryResponseDto;
import com.BookStoreManagament.entity.CategoryEntity;
import com.BookStoreManagament.exception.CategoryNotFoundException;
import com.BookStoreManagament.mapper.CategoryMapper;
import com.BookStoreManagament.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public List<AllCategoriesResponseDto> getAllCategories() {

        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        List<CategoryEntity> entities = new ArrayList<>();

        for(int i = 0;i<categoryEntities.size();i++)
        {
            if(categoryEntities.get(i).getCategoryStatus() == 1)
            {
                CategoryEntity categoryEntity = categoryEntities.get(i);
                entities.add(categoryEntity);
            }
        }

        if (entities.isEmpty())
        {
            throw new CategoryNotFoundException("No Categories Available");
        }


        return categoryEntities.stream()
                .filter(i -> i.getCategoryStatus() == 1)
                .map(i -> new AllCategoriesResponseDto(i.getCategoryName())).toList();
    }

    public void addNewCategory(AddCategoryRequestDto request) {

        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        for(int i=0;i<categoryEntities.size();i++)
        {
            CategoryEntity categoryEntity = categoryEntities.get(i);

            if(request.getCategoryName().equals(categoryEntity.getCategoryName())
                    && categoryEntity.getCategoryStatus() == 0)
            {
                categoryRepository.updateCategoryById(categoryEntity.getId()); return;
            }
        }

        categoryRepository.save(categoryMapper.toEntityFromAddCategoryRequestDto(request));
    }

    public void deleteCategoryById(String id) {
        CategoryEntity category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category Not Aviable"));

        category.setCategoryStatus((byte) 0);
        categoryRepository.save(category);
    }

    public CategoryResponseDto getCategoryById(String categoryId)
    {
        return categoryMapper.toCategoryResponseDto(categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category Not Found")));
    }
}