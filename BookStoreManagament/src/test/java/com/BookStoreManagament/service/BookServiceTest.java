package com.BookStoreManagament.service;

import com.BookStoreManagament.dto.response.*;
import com.BookStoreManagament.entity.BookEntity;
import com.BookStoreManagament.exception.BookNotFoundException;
import com.BookStoreManagament.mapper.BookMapper;
import com.BookStoreManagament.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    private BookRepository bookRepository;
    private BookMapper bookMapper;
    private BookService bookService;

    @BeforeEach
    void setUp() {
        bookRepository = mock(BookRepository.class);
        bookMapper = mock(BookMapper.class);
        bookService = new BookService(bookRepository, bookMapper);
    }

    @Test
    void testGetBookInfoById_whenBookIdExist_shouldReturnBookInfo() {
        BookEntity entity = new BookEntity(
                "id",
                "mayis",
                BigDecimal.TEN,
                "asd",
                BigDecimal.ZERO,
                LocalDate.now(),
                "asd",
                "asd",
                "asd",
                (byte)1,
                "asd",
                "asd"
        );

        BookResponseDto dto = new BookResponseDto(
                "mayis",
                BigDecimal.TEN,
                BigDecimal.ZERO,
                LocalDate.now(),
                "asd",
                new CategoryResponseDto(),
                new PublisherResponseDto()
        );

        when(bookRepository.findById("id")).thenReturn(Optional.of(entity));
        when(bookMapper.toBookResponseDto(entity)).thenReturn(dto);

        BookResponseDto result = bookService.getBookInfoById("id");

        assertEquals(result, dto);
    }

    @Test
    void testGetBookInfoById_whenBookIdDoesNotExist_shouldThrowBookNotFound() {
        when(bookRepository.findById("id")).thenReturn(Optional.empty());

        assertThrows(BookNotFoundException.class,
                () -> bookService.getBookInfoById("id"));
    }

    @Test
    void testGetBookInitialPriceAndBookCategoryById_whenBookIdExist_shouldReturnInitialPriceAndCategory() {
        BookEntity entity = new BookEntity(
                "id",
                "mayis",
                BigDecimal.valueOf(110),
                "asd",
                BigDecimal.ZERO,
                LocalDate.now(),
                "asd",
                "asd",
                "asd",
                (byte)1,
                "asd",
                "asd"
        );

        BookInfoForDepotResponseDto dto = new BookInfoForDepotResponseDto(
                BigDecimal.valueOf(100),
                "asd"
        );

        when(bookRepository.findById("id")).thenReturn(Optional.of(entity));

        BookInfoForDepotResponseDto result = bookService.getBookInitialPriceAndBookCategoryById("id");

        assertEquals(result, dto);
    }

    @Test
    void testGetBookInitialPriceAndBookCategoryById_whenBookIdDoesNotExist_shouldThrowBookNotFound() {
        when(bookRepository.findById("id")).thenReturn(Optional.empty());

        assertThrows(BookNotFoundException.class,
                () -> bookService.getBookInitialPriceAndBookCategoryById("id"));
    }

    @Test
    void testGetBookPriceById_whenBookIdExist_shouldReturnBookPrice() {
        BookEntity entity = new BookEntity(
                "id",
                "mayis",
                BigDecimal.valueOf(110),
                "asd",
                BigDecimal.ZERO,
                LocalDate.now(),
                "asd",
                "asd",
                "asd",
                (byte)1,
                "asd",
                "asd"
        );

        BigDecimal price = BigDecimal.valueOf(110);

        when(bookRepository.findById("id")).thenReturn(Optional.of(entity));

        BigDecimal result = bookService.getBookPriceById("id");

        assertEquals(result, price);
    }

    @Test
    void testGetMostPopularBookByPublisherId_whenPublisherIdExist_shouldReturnMostPopularBook() {
        List<BookEntity> entities = new ArrayList<>();
        entities.add(new BookEntity(
                "id1",
                "mayis",
                BigDecimal.valueOf(110),
                "asd",
                BigDecimal.ZERO,
                LocalDate.now(),
                "asd",
                "asd",
                "asd",
                (byte)1,
                "asd",
                "asd"
        ));
        entities.add(new BookEntity(
                "id2",
                "mayis",
                BigDecimal.valueOf(110),
                "asd",
                BigDecimal.TEN,
                LocalDate.now(),
                "asd",
                "asd",
                "asd",
                (byte)1,
                "asd",
                "asd"
        ));

        MostPopularBookResponseDto dto = new MostPopularBookResponseDto(
                "mayis",
                BigDecimal.valueOf(110),
                "asd",
                BigDecimal.TEN,
                new CategoryResponseDto()
        );

        List<BookEntity> reversed_entities = new ArrayList<>();
        for (int i = entities.size()-1; i >=0 ; i--) {
            reversed_entities.add(entities.get(i));
        }

        when(bookRepository.findBooksSortBySoldQuantityHighToLow()).thenReturn(reversed_entities);
        when(bookMapper.toMostPopularBookResponseDto(reversed_entities.get(0))).thenReturn(dto);

        MostPopularBookResponseDto result = bookService.getMostPopularBookByPublisherId("asd");

        assertEquals(result, dto);
    }

    @Test
    void testGetMostPopularBookByPublisherId_whenPublisherIdDoesNotExist_shouldThrowBookNotFound() {
        List<BookEntity> entities = new ArrayList<>();
        when(bookRepository.findBooksSortBySoldQuantityHighToLow()).thenReturn(entities);

        assertThrows(BookNotFoundException.class,
                () -> bookService.getMostPopularBookByPublisherId("id"));
    }

    @Test
    void testGetPublishersAllBooksByCategoryId_whenCategoryIdExist_shouldReturnAllBooks() {
        List<BookEntity> entities = new ArrayList<>();
        entities.add(new BookEntity(
                "id1",
                "mayis",
                BigDecimal.valueOf(110),
                "asd",
                BigDecimal.ZERO,
                LocalDate.now(),
                "asd",
                "asd",
                "asd",
                (byte)1,
                "asd",
                "asd"
        ));
        entities.add(new BookEntity(
                "id2",
                "mayis",
                BigDecimal.valueOf(110),
                "asd",
                BigDecimal.TEN,
                LocalDate.now(),
                "asd",
                "asd",
                "asd",
                (byte)1,
                "asd",
                "asd"
        ));

        List<FilterAllBooksResponseDto> dtos = new ArrayList<>();
        dtos.add(new FilterAllBooksResponseDto(
                "mayis",
                BigDecimal.valueOf(110),
                "asd",
                BigDecimal.ZERO,
                new CategoryResponseDto()
        ));
        dtos.add(new FilterAllBooksResponseDto(
                "mayis",
                BigDecimal.valueOf(110),
                "asd",
                BigDecimal.TEN,
                new CategoryResponseDto()
        ));

        when(bookRepository.findBookEntitiesByFkCategoryId("asd")).thenReturn(entities);
        for(int i=0;i< entities.size();i++)
        {
            when(bookMapper.toFilterAllBooksResponseDto(entities.get(i))).thenReturn(dtos.get(i));
        }

        List<FilterAllBooksResponseDto> result = bookService.getPublishersAllBooksByCategoryId("asd", "asd");

        assertEquals(result, dtos);
    }

    @Test
    void testGetPublishersAllBooksByCategoryId_whenCategoryIdDoesNotExist_shouldThrowBookNotFound() {
        List<BookEntity> entities = new ArrayList<>();

        when(bookRepository.findBookEntitiesByFkCategoryId("asd")).thenReturn(entities);

        assertThrows(BookNotFoundException.class,
                () -> bookService.getPublishersAllBooksByCategoryId("asd", "asd"));
    }
}