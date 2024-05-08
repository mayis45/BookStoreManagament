package com.BookStoreManagament.controller;

import com.BookStoreManagament.dto.request.AddBookToCartRequestDto;
import com.BookStoreManagament.dto.request.AddPaymentDetailsRequestDto;
import com.BookStoreManagament.dto.request.ModifyQuantityOfBookCartRequestDto;
import com.BookStoreManagament.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/v1/cart-api")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/addtocart")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public void addToCart(@RequestBody AddBookToCartRequestDto request) {
        cartService.addToCart(request);
    }

    @PutMapping("/uncheck/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public void unCheckBookById(@PathVariable String id) {
        cartService.unCheckBookById(id);
    }

    @PutMapping("/check/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public void checkBookById(@PathVariable String id) {
        cartService.checkBookById(id);
    }

    @PutMapping("/increementquantity/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public void increementQuantityOfBookById(@PathVariable String id){
        cartService.increementQuantityOfBookById(id);
    }

    @PutMapping("/decreementquantity/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public void decreementQuantityOfBookById(@PathVariable String id){
        cartService.decreementQuantityOfBookById(id);
    }

    @PutMapping("/modifyquantity")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public void modifyQuantityOfBookById(@RequestBody ModifyQuantityOfBookCartRequestDto request){
        cartService.modifyQuantityOfBookById(request);
    }

    @PutMapping("/addcardnumber-and-cvvcode")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public void addCardNoAndCardCVVcode(@RequestBody AddPaymentDetailsRequestDto request){
        cartService.addCardNoAndCardCVVcode(request);
    }

    @GetMapping("/totalprice")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public BigDecimal getTotalPriceWhereBookIsChecked(){
        return cartService.getTotalPriceWhereBookIsChecked();
    }

    @PostMapping("/submitcart")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public void submitCart(){
        cartService.submitCart();
    }

}
