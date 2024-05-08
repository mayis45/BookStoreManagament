package com.BookStoreManagament.controller;

import com.BookStoreManagament.dto.request.AddBookToDepotRequestDto;
import com.BookStoreManagament.dto.request.FilterBetweenByDateDepotRequestDto;
import com.BookStoreManagament.dto.response.AllDepotsBooksResponseDto;
import com.BookStoreManagament.service.DepotService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/depot-api")
public class DepotController {

    private final DepotService depotService;

    public DepotController(DepotService depotService) {
        this.depotService = depotService;
    }

    @PostMapping("/addbook")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE', 'ROLE_ADMIN')")
    public void addBookToDepot(@RequestBody @Valid AddBookToDepotRequestDto request) {
        depotService.addBookToDepot(request);
    }

    @GetMapping("/allbooks-sortbyinitialprice-desc")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE', 'ROLE_ADMIN')")
    public List<AllDepotsBooksResponseDto> getAllDepotsBooksBySortInitialPriceHighToLow() {
        return depotService.getAllDepotsBooksBySortInitialPriceHighToLow();
    }

    @GetMapping("/allbooks-sortbyinitialprice")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE', 'ROLE_ADMIN')")
    public List<AllDepotsBooksResponseDto> getAllDepotsBooksBySortInitialPriceLowToHigh() {
        return depotService.getAllDepotsBooksBySortInitialPriceLowToHigh();
    }

    @GetMapping("/allbooks-filterbyimportdate")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE', 'ROLE_ADMIN')")
    public List<AllDepotsBooksResponseDto> getAllDepotsBooksFilterByImportDateWithBetween
            (@RequestBody @Valid FilterBetweenByDateDepotRequestDto request)
    {
        return depotService.getAllDepotsBooksFilterByImportDateWithBetween(request);
    }

}