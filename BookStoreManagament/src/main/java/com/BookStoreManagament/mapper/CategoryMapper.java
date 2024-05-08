package com.BookStoreManagament.mapper;

import com.BookStoreManagament.dto.request.AddCategoryRequestDto;
import com.BookStoreManagament.dto.response.CategoryResponseDto;
import com.BookStoreManagament.entity.CategoryEntity;
import com.BookStoreManagament.exception.CategoryNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CategoryMapper {

    public CategoryEntity toEntityFromAddCategoryRequestDto(AddCategoryRequestDto request)
    {
        if(request == null)
        {
            throw new CategoryNotFoundException("Request is null");
        }

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryName(request.getCategoryName());
        categoryEntity.setCategoryStatus((byte)1);

        return categoryEntity;
    }

    public CategoryResponseDto toCategoryResponseDto(CategoryEntity categoryEntity)
    {
        return new CategoryResponseDto(
            categoryEntity.getCategoryName()
        );
    }

}
