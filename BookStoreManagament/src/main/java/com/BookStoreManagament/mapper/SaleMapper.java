package com.BookStoreManagament.mapper;

import com.BookStoreManagament.dto.request.AddSaleRequestDto;
import com.BookStoreManagament.dto.response.AllSalesResponseDto;
import com.BookStoreManagament.dto.response.BookResponseDto;
import com.BookStoreManagament.entity.SaleEntity;
import com.BookStoreManagament.exception.SaleNotFoundException;
import com.BookStoreManagament.service.BookService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SaleMapper {

    private final BookService bookService;

    public SaleMapper(BookService bookService) {
        this.bookService = bookService;
    }

    public SaleEntity toEntityFromAddSaleRequestDto(AddSaleRequestDto request)
    {

        if(request == null)
        {
            throw new SaleNotFoundException("Request is null");
        }

        SaleEntity entity = new SaleEntity();
        entity.setSenderCardNumber(request.getSenderCardNumber());
        entity.setDateOfPayment(LocalDateTime.now());
        entity.setQuantityOfBooks(request.getQuantityOfBooks());
        entity.setTotalPrice(request.getTotalPrice());
        entity.setTotalProfit(request.getTotalProfit());
        entity.setFkBookId(request.getFkBookId());

        return entity;
    }

    /*public AllSalesResponseDto toAllSalesResponseDto(SaleEntity saleEntity)
    {
        return new AllSalesResponseDto(
                saleEntity.getSenderCardNumber(),
                saleEntity.getDateOfPayment(),
                saleEntity.getQuantityOfBooks(),
                saleEntity.getTotalPrice(),
                saleEntity.getTotalProfit(),
                saleEntity.getFkBookId()
        );
    }*/

    public List<AllSalesResponseDto> toAllSalesResponseDtoList(List<SaleEntity> entities)
    {

        if(entities.isEmpty())
        {
            throw new SaleNotFoundException("No Sales Aviable");
        }

        List<AllSalesResponseDto> dtos = new ArrayList<>();

        for (int i = 0; i < entities.size(); i++) {

            SaleEntity entity = entities.get(i);
            BookResponseDto bookById = bookService.getBookInfoById(entity.getFkBookId());

            dtos.add(new AllSalesResponseDto(
                    entity.getSenderCardNumber(),
                    entity.getDateOfPayment(),
                    entity.getQuantityOfBooks(),
                    entity.getTotalPrice(),
                    entity.getTotalProfit(),
                    bookById
            ));
        }

        return dtos;
    }
}
