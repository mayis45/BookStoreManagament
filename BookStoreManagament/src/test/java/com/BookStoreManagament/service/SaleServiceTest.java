package com.BookStoreManagament.service;

import com.BookStoreManagament.dto.request.FilterBetweenByDateSaleRequestDto;
import com.BookStoreManagament.dto.response.AllSalesResponseDto;
import com.BookStoreManagament.dto.response.BookResponseDto;
import com.BookStoreManagament.entity.SaleEntity;
import com.BookStoreManagament.exception.SaleNotFoundException;
import com.BookStoreManagament.mapper.SaleMapper;
import com.BookStoreManagament.repository.SaleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

class SaleServiceTest {

    private SaleRepository saleRepository;
    private SaleMapper saleMapper;
    private SaleService saleService;

    @BeforeEach
    public void setUp() {
        saleRepository = mock(SaleRepository.class);
        saleMapper = mock(SaleMapper.class);
        saleService = new SaleService(saleRepository, saleMapper);
    }

    @Test
    void testGetAllSalesFilterByDateOfPaymentWithBetween() {

        List<SaleEntity> entities = new ArrayList<>();
        entities.add(new SaleEntity(
                "id",
                "123",
                LocalDateTime.now(),
                BigDecimal.ONE,
                BigDecimal.TEN,
                BigDecimal.ZERO,
                "mayis"
        ));

        List<AllSalesResponseDto> dtos = new ArrayList<>();
        dtos.add(new AllSalesResponseDto(
                "123",
                LocalDateTime.of(2025, 12, 12, 12, 12),
                BigDecimal.ONE,
                BigDecimal.TEN,
                BigDecimal.ZERO,
                new BookResponseDto()
        ));

        when(saleRepository.findSaleEntitiesFilterByDateOfPaymentWithBetween(
                LocalDateTime.of(2024, 12, 12, 12, 12),
                LocalDateTime.of(2027, 12, 12, 12, 12)
        )).thenReturn(entities);

        when(saleMapper.toAllSalesResponseDtoList(entities)).thenReturn(dtos);

        List<AllSalesResponseDto> result = saleService.getAllSalesFilterByDateOfPaymentWithBetween(new FilterBetweenByDateSaleRequestDto(
                LocalDateTime.of(2024, 12, 12, 12, 12),
                LocalDateTime.of(2027, 12, 12, 12, 12)
        ));

        assertEquals(result, dtos);
    }

    @Test
    void testGetAllSalesFilterByDateOfPaymentWithBetween_whenAllSalesIsEmpty_shouldThrowSaleNotFound() {
        when(saleRepository.findSaleEntitiesFilterByDateOfPaymentWithBetween(
                LocalDateTime.of(2024, 12, 12, 12, 12),
                LocalDateTime.of(2027, 12, 12, 12, 12))
        ).thenReturn(List.of());

        assertThrows(SaleNotFoundException.class,
                () -> saleService.getAllSalesFilterByDateOfPaymentWithBetween(new FilterBetweenByDateSaleRequestDto(
                        LocalDateTime.of(2024, 12, 12, 12, 12),
                        LocalDateTime.of(2027, 12, 12, 12, 12))
                ));
    }

    @Test
    void getAllSalesSortTotalPriceHighToLow() {
        List<SaleEntity> entities = new ArrayList<>();
        entities.add(new SaleEntity(
                "id1",
                "123",
                LocalDateTime.now(),
                BigDecimal.ONE,
                BigDecimal.ONE,
                BigDecimal.ZERO,
                "mayis"
        ));
        entities.add(new SaleEntity(
                "id2",
                "123",
                LocalDateTime.now(),
                BigDecimal.ONE,
                BigDecimal.TEN,
                BigDecimal.ZERO,
                "mayis"
        ));

        List<AllSalesResponseDto> dtos = new ArrayList<>();
        dtos.add(new AllSalesResponseDto(
                "123",
                LocalDateTime.now(),
                BigDecimal.ONE,
                BigDecimal.TEN,
                BigDecimal.ZERO,
                new BookResponseDto()
        ));
        dtos.add(new AllSalesResponseDto(
                "123",
                LocalDateTime.now(),
                BigDecimal.ONE,
                BigDecimal.ONE,
                BigDecimal.ZERO,
                new BookResponseDto()
        ));

        List<SaleEntity> reversed_entities = new ArrayList<>();
        for (int i = entities.size()-1; i >=0 ; i--) {
            reversed_entities.add(entities.get(i));
        }

        when(saleRepository.findSaleEntitiesSortTotalPriceHighToLow()).thenReturn(reversed_entities.stream().toList());
        when(saleMapper.toAllSalesResponseDtoList(reversed_entities)).thenReturn(dtos);

        List<AllSalesResponseDto> result = saleService.getAllSalesSortTotalPriceHighToLow();

        assertEquals(result, dtos);
    }

    @Test
    void getAllSalesSortTotalPriceHighToLow_whenAllSalesIsEmpty_shouldThrowSaleNotFound(){
        when(saleRepository.findSaleEntitiesSortTotalPriceHighToLow()).thenReturn(List.of());

        assertThrows(SaleNotFoundException.class,
                () -> saleService.getAllSalesSortTotalPriceHighToLow());
    }

    @Test
    void testGetAllSalesSortTotalPriceLowToHigh() {
        List<SaleEntity> entities = new ArrayList<>();
        entities.add(new SaleEntity(
                "id1",
                "123",
                LocalDateTime.now(),
                BigDecimal.TEN,
                BigDecimal.TEN,
                BigDecimal.ZERO,
                "mayis"
        ));
        entities.add(new SaleEntity(
                "id2",
                "123",
                LocalDateTime.now(),
                BigDecimal.ONE,
                BigDecimal.ONE,
                BigDecimal.ZERO,
                "mayis"
        ));

        List<AllSalesResponseDto> dtos = new ArrayList<>();
        dtos.add(new AllSalesResponseDto(
                "123",
                LocalDateTime.now(),
                BigDecimal.ONE,
                BigDecimal.TEN,
                BigDecimal.ZERO,
                new BookResponseDto()
        ));
        dtos.add(new AllSalesResponseDto(
                "123",
                LocalDateTime.now(),
                BigDecimal.TEN,
                BigDecimal.ONE,
                BigDecimal.ZERO,
                new BookResponseDto()
        ));

        List<SaleEntity> reversed_entities = new ArrayList<>();
        for (int i = entities.size()-1; i >=0 ; i--) {
            reversed_entities.add(entities.get(i));
        }

        when(saleRepository.findSaleEntitiesSortTotalPriceLowToHigh()).thenReturn(reversed_entities.stream().toList());
        when(saleMapper.toAllSalesResponseDtoList(reversed_entities)).thenReturn(dtos);

        List<AllSalesResponseDto> result = saleService.getAllSalesSortTotalPriceLowToHigh();

        assertEquals(result, dtos);
    }

    @Test
    void testGetAllSalesSortTotalPriceLowToHigh_whenAllSalesIsEmpty_shouldThrowSaleNotFound() {
        when(saleRepository.findSaleEntitiesSortTotalPriceLowToHigh()).thenReturn(List.of());

        assertThrows(SaleNotFoundException.class,
                () -> saleService.getAllSalesSortTotalPriceLowToHigh());
    }

    @Test
    void getAllSalesSortTotalProfitHighToLow() {
        List<SaleEntity> entities = new ArrayList<>();
        entities.add(new SaleEntity(
                "id1",
                "123",
                LocalDateTime.now(),
                BigDecimal.TEN,
                BigDecimal.TEN,
                BigDecimal.ONE,
                "mayis"
        ));
        entities.add(new SaleEntity(
                "id2",
                "123",
                LocalDateTime.now(),
                BigDecimal.ONE,
                BigDecimal.ONE,
                BigDecimal.TEN,
                "mayis"
        ));

        List<AllSalesResponseDto> dtos = new ArrayList<>();
        dtos.add(new AllSalesResponseDto(
                "123",
                LocalDateTime.now(),
                BigDecimal.ONE,
                BigDecimal.TEN,
                BigDecimal.TEN,
                new BookResponseDto()
        ));
        dtos.add(new AllSalesResponseDto(
                "123",
                LocalDateTime.now(),
                BigDecimal.TEN,
                BigDecimal.ONE,
                BigDecimal.ONE,
                new BookResponseDto()
        ));

        List<SaleEntity> reversed_entities = new ArrayList<>();
        for (int i = entities.size()-1; i >=0 ; i--) {
            reversed_entities.add(entities.get(i));
        }

        when(saleRepository.findSaleEntitiesSortTotalProfitHighToLow()).thenReturn(reversed_entities.stream().toList());
        when(saleMapper.toAllSalesResponseDtoList(reversed_entities)).thenReturn(dtos);

        List<AllSalesResponseDto> result = saleService.getAllSalesSortTotalProfitHighToLow();

        assertEquals(result, dtos);
    }

    @Test
    void getAllSalesSortTotalProfitHighToLow_whenAllSalesIsEmpty_shouldThrowSaleNotFound() {
        when(saleRepository.findSaleEntitiesSortTotalProfitHighToLow()).thenReturn(List.of());

        assertThrows(SaleNotFoundException.class,
                () -> saleService.getAllSalesSortTotalProfitHighToLow());
    }

    @Test
    void getAllSalesSortTotalProfitLowToHigh() {
        List<SaleEntity> entities = new ArrayList<>();
        entities.add(new SaleEntity(
                "id1",
                "123",
                LocalDateTime.now(),
                BigDecimal.TEN,
                BigDecimal.TEN,
                BigDecimal.TEN,
                "mayis"
        ));
        entities.add(new SaleEntity(
                "id2",
                "123",
                LocalDateTime.now(),
                BigDecimal.ONE,
                BigDecimal.ONE,
                BigDecimal.ONE,
                "mayis"
        ));

        List<AllSalesResponseDto> dtos = new ArrayList<>();
        dtos.add(new AllSalesResponseDto(
                "123",
                LocalDateTime.now(),
                BigDecimal.ONE,
                BigDecimal.TEN,
                BigDecimal.ONE,
                new BookResponseDto()
        ));
        dtos.add(new AllSalesResponseDto(
                "123",
                LocalDateTime.now(),
                BigDecimal.TEN,
                BigDecimal.ONE,
                BigDecimal.TEN,
                new BookResponseDto()
        ));

        List<SaleEntity> reversed_entities = new ArrayList<>();
        for (int i = entities.size()-1; i >=0 ; i--) {
            reversed_entities.add(entities.get(i));
        }

        when(saleRepository.findSaleEntitiesSortTotalProfitLowToHigh()).thenReturn(reversed_entities.stream().toList());
        when(saleMapper.toAllSalesResponseDtoList(reversed_entities)).thenReturn(dtos);

        List<AllSalesResponseDto> result = saleService.getAllSalesSortTotalProfitLowToHigh();

        assertEquals(result, dtos);
    }

    @Test
    void getAllSalesSortTotalProfitLowToHigh_whenAllSalesIsEmpty_shouldThrowSaleNotFound() {
        when(saleRepository.findSaleEntitiesSortTotalProfitLowToHigh()).thenReturn(List.of());

        assertThrows(SaleNotFoundException.class,
                () -> saleService.getAllSalesSortTotalProfitLowToHigh());
    }

    @Test
    void getAllSalesSortDateOfPaymentLowToHigh() {
        List<SaleEntity> entities = new ArrayList<>();
        entities.add(new SaleEntity(
                "id1",
                "123",
                LocalDateTime.now(),
                BigDecimal.TEN,
                BigDecimal.TEN,
                BigDecimal.TEN,
                "mayis"
        ));
        entities.add(new SaleEntity(
                "id2",
                "123",
                LocalDateTime.of(2020, 04,28, 12,12),
                BigDecimal.ONE,
                BigDecimal.ONE,
                BigDecimal.ONE,
                "mayis"
        ));

        List<AllSalesResponseDto> dtos = new ArrayList<>();
        dtos.add(new AllSalesResponseDto(
                "123",
                LocalDateTime.of(2020, 04,28, 12,12),
                BigDecimal.ONE,
                BigDecimal.TEN,
                BigDecimal.ONE,
                new BookResponseDto()
        ));
        dtos.add(new AllSalesResponseDto(
                "123",
                LocalDateTime.now(),
                BigDecimal.TEN,
                BigDecimal.ONE,
                BigDecimal.TEN,
                new BookResponseDto()
        ));

        List<SaleEntity> reversed_entities = new ArrayList<>();
        for (int i = entities.size()-1; i >=0 ; i--) {
            reversed_entities.add(entities.get(i));
        }

        when(saleRepository.findSaleEntitiesSortDateOfPaymentLowToHigh()).thenReturn(reversed_entities);
        when(saleMapper.toAllSalesResponseDtoList(reversed_entities)).thenReturn(dtos);

        List<AllSalesResponseDto> result = saleService.getAllSalesSortDateOfPaymentLowToHigh();

        assertEquals(result, dtos);
    }

    @Test
    void getAllSalesSortDateOfPaymentLowToHigh_whenAllSalesIsEmpty_shouldThrowSaleNotFound() {
        when(saleRepository.findSaleEntitiesSortDateOfPaymentLowToHigh()).thenReturn(List.of());

        assertThrows(SaleNotFoundException.class,
                () -> saleService.getAllSalesSortDateOfPaymentLowToHigh());
    }

    @Test
    void calculateTotalProfitAllTime() {
        BigDecimal totalProfit = BigDecimal.TEN;

        when(saleRepository.calculateTotalProfitAllTime()).thenReturn(totalProfit);

        BigDecimal result = saleService.calculateTotalProfitAllTime();

        assertEquals(result, totalProfit);
    }

    @Test
    void calculateTotalProfitBetweenDate() {

        BigDecimal totalProfitBetweenDate = BigDecimal.valueOf(11);

        when(saleRepository.calculateTotalProfitBetweenDate(
                LocalDateTime.of(2020, 04,27, 12,12),
                LocalDateTime.of(2025, 04,28, 12,12)
        )).thenReturn(totalProfitBetweenDate);

        BigDecimal result = saleService.calculateTotalProfitBetweenDate(new FilterBetweenByDateSaleRequestDto(
                LocalDateTime.of(2020, 04,27, 12,12),
                LocalDateTime.of(2025, 04,28, 12,12)
        ));

        assertEquals(result, totalProfitBetweenDate);
    }
}