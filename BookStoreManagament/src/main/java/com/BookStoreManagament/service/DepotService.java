package com.BookStoreManagament.service;

import com.BookStoreManagament.dto.request.AddBookToDepotRequestDto;
import com.BookStoreManagament.dto.request.FilterBetweenByDateDepotRequestDto;
import com.BookStoreManagament.dto.response.AllDepotsBooksResponseDto;
import com.BookStoreManagament.entity.DepotEntity;
import com.BookStoreManagament.exception.DepotNotFoundException;
import com.BookStoreManagament.mapper.DepotMapper;
import com.BookStoreManagament.repository.DepotRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepotService {

    private final DepotRepository depotRepository;
    private final DepotMapper depotMapper;

    public DepotService(DepotRepository depotRepository, DepotMapper depotMapper) {
        this.depotRepository = depotRepository;
        this.depotMapper = depotMapper;
    }

    public void addBookToDepot(AddBookToDepotRequestDto request) {
        depotRepository.save(depotMapper.toEntityFromAddBookToDepotRequestDto(request));
    }

    public List<AllDepotsBooksResponseDto> getAllDepotsBooksBySortInitialPriceHighToLow() {

        List<DepotEntity> entities = depotRepository.findDepotEntitiesBySortInitialPriceHighToLow();

        if(entities.isEmpty())
        {
            throw new DepotNotFoundException("No Books aviable");
        }

        return depotMapper
                .toAllDepotsBooksResponseDtoList(entities);
    }

    public List<AllDepotsBooksResponseDto> getAllDepotsBooksBySortInitialPriceLowToHigh() {

        List<DepotEntity> entities = depotRepository.findDepotEntitiesBySortInitialPriceLowToHigh();

        if(entities.isEmpty())
        {
            throw new DepotNotFoundException("No Books aviable");
        }

        return depotMapper
                .toAllDepotsBooksResponseDtoList(entities);
    }

    public List<AllDepotsBooksResponseDto> getAllDepotsBooksFilterByImportDateWithBetween(
            FilterBetweenByDateDepotRequestDto request) {

        List<DepotEntity> entities = depotRepository.findDepotEntitiesFilterByImportDateWithBetween(
                request.getStartCaseDate(),
                request.getEndCaseDate());

        if(entities.isEmpty())
        {
            throw new DepotNotFoundException("No Books aviable");
        }

        return depotMapper.toAllDepotsBooksResponseDtoList(entities);
    }

}