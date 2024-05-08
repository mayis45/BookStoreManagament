package com.BookStoreManagament.service;

import com.BookStoreManagament.dto.request.*;
import com.BookStoreManagament.entity.CartEntity;
import com.BookStoreManagament.exception.CartNotFoundException;
import com.BookStoreManagament.mapper.CartMapper;
import com.BookStoreManagament.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartMapper hamperMapper;
    private final SaleService saleService;
    private final BookService bookService;

    public CartService(CartRepository cartRepository,
                       CartMapper hamperMapper,
                       SaleService saleService,
                       BookService bookService) {
        this.cartRepository = cartRepository;
        this.hamperMapper = hamperMapper;
        this.saleService = saleService;
        this.bookService = bookService;
    }

    public void addToCart(AddBookToCartRequestDto request) {
        cartRepository.save(hamperMapper.toEntityFromAddBookToCartRequestDto(request));
    }

    public void unCheckBookById(String id) {
        cartRepository.unCheckBookById(id);
    }

    public void checkBookById(String id) {
        cartRepository.checkBookById(id);
    }

    public void increementQuantityOfBookById(String id) {
        cartRepository.increementQuantityOfBookById(id);
        whenIncreementQuantityOfBookByIdIncreaseTotalPrice(id);
    }

    protected void whenIncreementQuantityOfBookByIdIncreaseTotalPrice(String id)
    {
        CartEntity entity = cartRepository.findById(id)
                .orElseThrow(() -> new CartNotFoundException("Cart Not Found"));
        BigDecimal price = bookService.getBookPriceById(entity.getFkBookId());
        cartRepository.whenIncreementQuantityOfBookByIdIncreaseTotalPrice(id, price);
    }

    public void decreementQuantityOfBookById(String id) {
        cartRepository.decreementQuantityOfBookById(id);
        whenDecreementQuantityOfBookByIdIncreaseTotalPrice(id);
    }

    protected void whenDecreementQuantityOfBookByIdIncreaseTotalPrice(String id)
    {
        CartEntity entity = cartRepository.findById(id)
                .orElseThrow(() -> new CartNotFoundException("Cart Not Found"));
        BigDecimal price = bookService.getBookPriceById(entity.getFkBookId());
        cartRepository.whenDecreementQuantityOfBookByIdIncreaseTotalPrice(id, price);
    }
    public void modifyQuantityOfBookById(ModifyQuantityOfBookCartRequestDto request) {
        cartRepository.modifyQuantityOfBookById(request.getId(), request.getQuantity());
        whenModifyQuantityOfBookByIdIncreaseTotalPrice(request.getId());
    }

    protected void whenModifyQuantityOfBookByIdIncreaseTotalPrice(String id)
    {
        CartEntity entity = cartRepository.findById(id)
                .orElseThrow(() -> new CartNotFoundException("Cart Not Found"));
        BigDecimal price = bookService.getBookPriceById(entity.getFkBookId());
        cartRepository.whenModifyQuantityOfBookByIdIncreaseTotalPrice(id, price);
    }

    public void addCardNoAndCardCVVcode(AddPaymentDetailsRequestDto request) {
        cartRepository.addCardNo(request.getId(), request.getCardNumber());
        cartRepository.addCardCVVCode(request.getId(), request.getCardCVVCode());
    }

    public BigDecimal getTotalPriceWhereBookIsChecked() {
        return cartRepository.findTotalPriceWhereBookIsChecked();
    }

    public void submitCart() {

        List<CartEntity> allBooks = cartRepository.findAll();

        if(allBooks.isEmpty())
        {
            throw new CartNotFoundException("No Carts Aviable");
        }

        for(int i=0;i<allBooks.size();i++)
        {
            if(allBooks.get(i).getBookIsChecked() == 1
                    &&
                        !allBooks.get(i).getCardNumber().equals("Please enter your card number")
                    &&
                        !allBooks.get(i).getCardCVVCode().equals("Please enter your card cvv code"))
            {
                CartEntity entity = allBooks.get(i);

                bookService.updateBookEntityByBookSoldQuantity(new UpdateBookSoldQuantityRequestDto(
                        entity.getFkBookId(),
                        entity.getQuantityOfBooks()
                ));

                saleService.addSale(new AddSaleRequestDto(
                        entity.getCardNumber(),
                        LocalDateTime.now(),
                        entity.getQuantityOfBooks(),
                        entity.getTotalPrice(),
                        executeTotalProfit(entity.getTotalPrice()),
                        entity.getFkBookId()
                ));
            }
        }

    }

    protected BigDecimal executeTotalProfit(BigDecimal cash)
    {
        Long a = cash.longValue();
        Long maya = (a * 100)/110;
        Long gelir = a - maya;

        return BigDecimal.valueOf(gelir);
    }

}
