package com.BookStoreManagament.mapper;

import com.BookStoreManagament.dto.request.AddBookToCartRequestDto;
import com.BookStoreManagament.entity.CartEntity;
import com.BookStoreManagament.exception.CartNotFoundException;
import com.BookStoreManagament.service.BookService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CartMapper {

    private final BookService bookService;

    public CartMapper(BookService bookService) {
        this.bookService = bookService;
    }

    public CartEntity toEntityFromAddBookToCartRequestDto(AddBookToCartRequestDto request)
    {

        if(request == null)
        {
            throw new CartNotFoundException("Request is null");
        }

        CartEntity entity = new CartEntity();
        entity.setFkBookId(request.getFkBookId());
        entity.setQuantityOfBooks(BigDecimal.ONE);
        entity.setBookIsChecked((byte)1);
        entity.setCardNumber("Please enter your card number");
        entity.setCardCVVCode("Please enter your card cvv code");
        entity.setTotalPrice(bookService.getBookPriceById(request.getFkBookId()));
        return entity;
    }

}
