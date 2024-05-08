package com.BookStoreManagament.service;

import com.BookStoreManagament.dto.request.FilterBetweenByDateDepotRequestDto;
import com.BookStoreManagament.dto.response.AllDepotsBooksResponseDto;
import com.BookStoreManagament.dto.response.AllSalesResponseDto;
import com.BookStoreManagament.dto.response.BookResponseDto;
import com.BookStoreManagament.dto.response.CategoryResponseDto;
import com.BookStoreManagament.entity.DepotEntity;
import com.BookStoreManagament.entity.SaleEntity;
import com.BookStoreManagament.mapper.DepotMapper;
import com.BookStoreManagament.repository.DepotRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

class DepotServiceTest {

    private DepotRepository depotRepository;
    private DepotMapper depotMapper;
    private DepotService depotService;

    @BeforeEach
    public void setUp()
    {
        depotRepository = mock(DepotRepository.class);
        depotMapper = mock(DepotMapper.class);
        depotService = new DepotService(depotRepository, depotMapper);
    }

    @Test
    void testGetAllDepotsBooksBySortInitialPriceHighToLow() {
        List<DepotEntity> entities = new ArrayList<>();
        entities.add(new DepotEntity(
                "id1",
                BigDecimal.ONE,
                BigDecimal.ONE,
                LocalDate.now(),
                "bookid",
                "categoryid"
        ));
        entities.add(new DepotEntity(
                "id2",
                BigDecimal.ONE,
                BigDecimal.TEN,
                LocalDate.now(),
                "bookid",
                "categoryid"
        ));

        List<AllDepotsBooksResponseDto> dtos = new ArrayList<>();
        dtos.add(new AllDepotsBooksResponseDto(
                BigDecimal.ONE,
                BigDecimal.TEN,
                LocalDate.now(),
                new BookResponseDto(),
                new CategoryResponseDto()
        ));
        dtos.add(new AllDepotsBooksResponseDto(
                BigDecimal.ONE,
                BigDecimal.ONE,
                LocalDate.now(),
                new BookResponseDto(),
                new CategoryResponseDto()
        ));

        List<DepotEntity> reversed_entities = new ArrayList<>();
        for (int i = entities.size()-1; i >=0 ; i--) {
            reversed_entities.add(entities.get(i));
        }

        when(depotRepository.findDepotEntitiesBySortInitialPriceHighToLow()).thenReturn(reversed_entities);
        when(depotMapper.toAllDepotsBooksResponseDtoList(reversed_entities)).thenReturn(dtos);

        List<AllDepotsBooksResponseDto> result = depotService.getAllDepotsBooksBySortInitialPriceHighToLow();

        assertEquals(result, dtos);
    }

    @Test
    void getAllDepotsBooksBySortInitialPriceLowToHigh() {
        List<DepotEntity> entities = new ArrayList<>();
        entities.add(new DepotEntity(
                "id1",
                BigDecimal.ONE,
                BigDecimal.TEN,
                LocalDate.now(),
                "bookid",
                "categoryid"
        ));
        entities.add(new DepotEntity(
                "id2",
                BigDecimal.ONE,
                BigDecimal.ONE,
                LocalDate.now(),
                "bookid",
                "categoryid"
        ));

        List<AllDepotsBooksResponseDto> dtos = new ArrayList<>();
        dtos.add(new AllDepotsBooksResponseDto(
                BigDecimal.ONE,
                BigDecimal.ONE,
                LocalDate.now(),
                new BookResponseDto(),
                new CategoryResponseDto()
        ));
        dtos.add(new AllDepotsBooksResponseDto(
                BigDecimal.ONE,
                BigDecimal.TEN,
                LocalDate.now(),
                new BookResponseDto(),
                new CategoryResponseDto()
        ));

        List<DepotEntity> reversed_entities = new ArrayList<>();
        for (int i = entities.size()-1; i >=0 ; i--) {
            reversed_entities.add(entities.get(i));
        }

        when(depotRepository.findDepotEntitiesBySortInitialPriceLowToHigh()).thenReturn(reversed_entities);
        when(depotMapper.toAllDepotsBooksResponseDtoList(reversed_entities)).thenReturn(dtos);

        List<AllDepotsBooksResponseDto> result = depotService.getAllDepotsBooksBySortInitialPriceLowToHigh();

        assertEquals(result, dtos);
    }

    @Test
    void getAllDepotsBooksFilterByImportDateWithBetween() {
        List<DepotEntity> entities = new ArrayList<>();
        entities.add(new DepotEntity(
                "id1",
                BigDecimal.ONE,
                BigDecimal.TEN,
                LocalDate.of(2005, 12, 12),
                "bookid",
                "categoryid"
        ));
        entities.add(new DepotEntity(
                "id2",
                BigDecimal.ONE,
                BigDecimal.ONE,
                LocalDate.of(2007, 12,12),
                "bookid",
                "categoryid"
        ));

        List<AllDepotsBooksResponseDto> dtos = new ArrayList<>();
        dtos.add(new AllDepotsBooksResponseDto(
                BigDecimal.ONE,
                BigDecimal.TEN,
                LocalDate.of(2005, 12, 12),
                new BookResponseDto(),
                new CategoryResponseDto()
        ));
        dtos.add(new AllDepotsBooksResponseDto(
                BigDecimal.ONE,
                BigDecimal.ONE,
                LocalDate.of(2007, 12,12),
                new BookResponseDto(),
                new CategoryResponseDto()
        ));

        when(depotRepository.findDepotEntitiesFilterByImportDateWithBetween(
               LocalDate.of(2004, 12, 12),
               LocalDate.of(2010, 12, 12)
        )).thenReturn(entities);

        when(depotMapper.toAllDepotsBooksResponseDtoList(entities)).thenReturn(dtos);

        List<AllDepotsBooksResponseDto> result = depotService.getAllDepotsBooksFilterByImportDateWithBetween(new FilterBetweenByDateDepotRequestDto(
                LocalDate.of(2004, 12, 12),
                LocalDate.of(2010, 12, 12)
        ));

        assertEquals(result, dtos);
    }
}