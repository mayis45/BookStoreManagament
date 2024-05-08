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
@RequestMapping("/v1/s1")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping("/allsales-filterby-dateofpayment")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE', 'ROLE_ADMIN')")
    public List<AllSalesResponseDto> getAllSalesFilterByDateOfPaymentWithBetween(
            @RequestBody FilterBetweenByDateSaleRequestDto request
    )
    {
        return saleService.getAllSalesFilterByDateOfPaymentWithBetween(request);
    }

    @GetMapping("/allsales-sortby-totalprice-desc")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE', 'ROLE_ADMIN')")
    public List<AllSalesResponseDto> getAllSalesSortTotalPriceHighToLow(){
        return saleService.getAllSalesSortTotalPriceHighToLow();
    }

    @GetMapping("/allsales-sortby-totalprice")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE', 'ROLE_ADMIN')")
    public List<AllSalesResponseDto> getAllSalesSortTotalPriceLowToHigh(){
        return saleService.getAllSalesSortTotalPriceLowToHigh();
    }

    @GetMapping("/allsales-sortby-totalprofit-desc")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE', 'ROLE_ADMIN')")
    public List<AllSalesResponseDto> getAllSalesSortTotalProfitHighToLow(){
        return saleService.getAllSalesSortTotalProfitHighToLow();
    }

    @GetMapping("/allsales-sortby-totalprofit")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE', 'ROLE_ADMIN')")
    public List<AllSalesResponseDto> getAllSalesSortTotalProfitLowToHigh(){
        return saleService.getAllSalesSortTotalProfitLowToHigh();
    }

    @GetMapping("/allsales-sortby-dateofpayment")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE', 'ROLE_ADMIN')")
    public List<AllSalesResponseDto> getAllSalesSortDateOfPaymentLowToHigh(){
        return saleService.getAllSalesSortDateOfPaymentLowToHigh();
    }

    @GetMapping("/calculate-totalprofit")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE', 'ROLE_ADMIN')")
    public BigDecimal calculateTotalProfitAllTime(){
        return saleService.calculateTotalProfitAllTime();
    }

    @GetMapping("/calculate-totalprofit-filterby-dateofpayment")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE', 'ROLE_ADMIN')")
    public BigDecimal calculateTotalProfitBetweenDate(@RequestBody FilterBetweenByDateSaleRequestDto request){
        return saleService.calculateTotalProfitBetweenDate(request);
    }

}
