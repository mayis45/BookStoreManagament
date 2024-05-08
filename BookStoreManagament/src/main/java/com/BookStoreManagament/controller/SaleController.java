package com.BookStoreManagament.controller;

import com.BookStoreManagament.dto.request.FilterBetweenByDateSaleRequestDto;
import com.BookStoreManagament.dto.response.AllSalesResponseDto;
import com.BookStoreManagament.service.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/v1/sale-api")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping("/allsales-filterbydateofpayment")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE', 'ROLE_ADMIN')")
    public List<AllSalesResponseDto> getAllSalesFilterByDateOfPaymentWithBetween(
            @RequestBody FilterBetweenByDateSaleRequestDto request
    )
    {
        return saleService.getAllSalesFilterByDateOfPaymentWithBetween(request);
    }

    @GetMapping("/allsales-sortbytotalprice-desc")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE', 'ROLE_ADMIN')")
    public List<AllSalesResponseDto> getAllSalesSortTotalPriceHighToLow(){
        return saleService.getAllSalesSortTotalPriceHighToLow();
    }

    @GetMapping("/allsales-sortbytotalprice")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE', 'ROLE_ADMIN')")
    public List<AllSalesResponseDto> getAllSalesSortTotalPriceLowToHigh(){
        return saleService.getAllSalesSortTotalPriceLowToHigh();
    }

    @GetMapping("/allsales-sortbytotalprofit-desc")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE', 'ROLE_ADMIN')")
    public List<AllSalesResponseDto> getAllSalesSortTotalProfitHighToLow(){
        return saleService.getAllSalesSortTotalProfitHighToLow();
    }

    @GetMapping("/allsales-sortbytotalprofit")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE', 'ROLE_ADMIN')")
    public List<AllSalesResponseDto> getAllSalesSortTotalProfitLowToHigh(){
        return saleService.getAllSalesSortTotalProfitLowToHigh();
    }

    @GetMapping("/allsales-sortbydateofpayment")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE', 'ROLE_ADMIN')")
    public List<AllSalesResponseDto> getAllSalesSortDateOfPaymentLowToHigh(){
        return saleService.getAllSalesSortDateOfPaymentLowToHigh();
    }

    @GetMapping("/totalprofit")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE', 'ROLE_ADMIN')")
    public BigDecimal calculateTotalProfitAllTime(){
        return saleService.calculateTotalProfitAllTime();
    }

    @GetMapping("/totalprofit-filterbydateofpayment")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE', 'ROLE_ADMIN')")
    public BigDecimal calculateTotalProfitBetweenDate(@RequestBody FilterBetweenByDateSaleRequestDto request){
        return saleService.calculateTotalProfitBetweenDate(request);
    }

}
