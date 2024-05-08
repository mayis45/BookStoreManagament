package com.BookStoreManagament.mapper;

import com.BookStoreManagament.dto.request.AddBookRequestDto;
import com.BookStoreManagament.dto.response.*;
import com.BookStoreManagament.entity.BookEntity;
import com.BookStoreManagament.exception.BookNotFoundException;
import com.BookStoreManagament.service.CategoryService;
import com.BookStoreManagament.service.PublisherAccountService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class BookMapper {

    private final CategoryService categoryService;
    private final PublisherAccountService publisherAccountService;

    public BookMapper(CategoryService categoryService, PublisherAccountService publisherAccountService) {
        this.categoryService = categoryService;
        this.publisherAccountService = publisherAccountService;
    }

    public MostPopularBookResponseDto toMostPopularBookResponseDto(BookEntity entity)
    {
        CategoryResponseDto categoryById = categoryService.getCategoryById(entity.getFkCategoryId());

        return new MostPopularBookResponseDto(
            entity.getBookName(),
            entity.getBookPrice(),
            entity.getBookPhotoUrl(),
            entity.getBookSoldQuantity(),
            categoryById
        );
    }

    public FilterAllBooksResponseDto toFilterAllBooksResponseDto(BookEntity entity)
    {
        CategoryResponseDto categoryById = categoryService.getCategoryById(entity.getFkCategoryId());

        return new FilterAllBooksResponseDto(
                entity.getBookName(),
                entity.getBookPrice(),
                entity.getBookPhotoUrl(),
                entity.getBookSoldQuantity(),
                categoryById
        );
    }

    public SearchedBookResponseDto toSearchedBookResponseDto(BookEntity entity){

        CategoryResponseDto categoryById = categoryService.getCategoryById(entity.getFkCategoryId());
        PublisherResponseDto publisherById = publisherAccountService.getPublisherById(entity.getFkPublisherId());

        return new SearchedBookResponseDto(
                entity.getBookName(),
                entity.getBookPrice(),
                entity.getBookPhotoUrl(),
                entity.getBookSoldQuantity(),
                entity.getPublishingDate(),
                entity.getBookDescription(),
                entity.getBookLanguage(),
                entity.getPagesOfBook(),
                categoryById,
                publisherById
        );
    }

    public AllBooksResponseDto toAllBooksResponseDto(BookEntity entity)
    {
        return new AllBooksResponseDto(
                entity.getBookName(),
                entity.getBookPhotoUrl(),
                entity.getBookSoldQuantity()
        );
    }

    public BookEntity toEntityFromAddBookRequestDto(AddBookRequestDto request)
    {

        if(request == null)
        {
            throw new BookNotFoundException("Request is null");
        }

        BookEntity entity = new BookEntity();

        //Verilen qiymetin uzerine 10% geliy payi qoyulur!!!
        long acceptedPrice = request.getBookPrice().longValue();
        long profitPrice = (acceptedPrice * 10)/100;
        long soldPrice = acceptedPrice + profitPrice;

        entity.setBookName(request.getBookName());
        entity.setBookPrice(BigDecimal.valueOf(soldPrice));
        entity.setBookPhotoUrl(request.getBookPhotoUrl());
        entity.setBookSoldQuantity(BigDecimal.ZERO);
        entity.setPublishingDate(LocalDate.now());
        entity.setBookDescription(request.getBookDescription());
        entity.setBookLanguage(request.getBookLanguage());
        entity.setPagesOfBook(request.getPagesOfBook());
        entity.setBookStatus((byte)1);
        entity.setFkCategoryId(request.getFkCategoryId());
        entity.setFkPublisherId(request.getFkPublisherId());

        return entity;
    }

    public BookResponseDto toBookResponseDto(BookEntity entity)
    {
        CategoryResponseDto categoryById = categoryService.getCategoryById(entity.getFkCategoryId());
        PublisherResponseDto publisherById = publisherAccountService.getPublisherById(entity.getFkPublisherId());

        return new BookResponseDto(
                entity.getBookName(),
                entity.getBookPrice(),
                entity.getBookSoldQuantity(),
                entity.getPublishingDate(),
                entity.getBookLanguage(),
                categoryById,
                publisherById
        );
    }

}
