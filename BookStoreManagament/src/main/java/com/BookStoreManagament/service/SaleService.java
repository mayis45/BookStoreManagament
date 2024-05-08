package com.BookStoreManagament.service;

import com.BookStoreManagament.dto.request.AddSaleRequestDto;
import com.BookStoreManagament.dto.request.FilterBetweenByDateSaleRequestDto;
import com.BookStoreManagament.dto.response.AllSalesResponseDto;
import com.BookStoreManagament.entity.SaleEntity;
import com.BookStoreManagament.exception.SaleNotFoundException;
import com.BookStoreManagament.mapper.SaleMapper;
import com.BookStoreManagament.repository.SaleRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final SaleMapper saleMapper;

    public SaleService(SaleRepository saleRepository, SaleMapper saleMapper) {
        this.saleRepository = saleRepository;
        this.saleMapper = saleMapper;
    }

    protected void addSale(AddSaleRequestDto request) {
        saleRepository.save(saleMapper.toEntityFromAddSaleRequestDto(request));
    }

    public List<AllSalesResponseDto> getAllSalesFilterByDateOfPaymentWithBetween(
            FilterBetweenByDateSaleRequestDto request
    ) {

        List<SaleEntity> entities = saleRepository.findSaleEntitiesFilterByDateOfPaymentWithBetween(
                request.getStartCaseDate(),
                request.getEndCaseDate()
        );

        if(entities.isEmpty())
        {
            throw new SaleNotFoundException("No Sales aviable");
        }

        return saleMapper.toAllSalesResponseDtoList(entities);
    }

    public List<AllSalesResponseDto> getAllSalesSortTotalPriceHighToLow() {

        List<SaleEntity> entities = saleRepository.findSaleEntitiesSortTotalPriceHighToLow();

        if(entities.isEmpty())
        {
            throw new SaleNotFoundException("No Sales aviable");
        }

        return saleMapper.toAllSalesResponseDtoList(entities);
    }

    public List<AllSalesResponseDto> getAllSalesSortTotalPriceLowToHigh() {

        List<SaleEntity> entities = saleRepository.findSaleEntitiesSortTotalPriceLowToHigh();

        if(entities.isEmpty())
        {
            throw new SaleNotFoundException("No Sales aviable");
        }

        return saleMapper.toAllSalesResponseDtoList(entities);
    }

    public List<AllSalesResponseDto> getAllSalesSortTotalProfitHighToLow() {

        List<SaleEntity> entities = saleRepository.findSaleEntitiesSortTotalProfitHighToLow();

        if(entities.isEmpty())
        {
            throw new SaleNotFoundException("No Sales aviable");
        }

        return saleMapper.toAllSalesResponseDtoList(entities);
    }

    public List<AllSalesResponseDto> getAllSalesSortTotalProfitLowToHigh() {

        List<SaleEntity> entities = saleRepository.findSaleEntitiesSortTotalProfitLowToHigh();

        if(entities.isEmpty())
        {
            throw new SaleNotFoundException("No Sales aviable");
        }

        return saleMapper.toAllSalesResponseDtoList(entities);
    }

    public List<AllSalesResponseDto> getAllSalesSortDateOfPaymentLowToHigh() {

        List<SaleEntity> entities = saleRepository.findSaleEntitiesSortDateOfPaymentLowToHigh();

        if(entities.isEmpty())
        {
            throw new SaleNotFoundException("No Sales aviable");
        }

        return saleMapper.toAllSalesResponseDtoList(entities);
    }

    public BigDecimal calculateTotalProfitAllTime() {
        return saleRepository.calculateTotalProfitAllTime();
    }

    public BigDecimal calculateTotalProfitBetweenDate(FilterBetweenByDateSaleRequestDto request) {
        return saleRepository.calculateTotalProfitBetweenDate(
                request.getStartCaseDate(),
                request.getEndCaseDate()
        );
    }

}
