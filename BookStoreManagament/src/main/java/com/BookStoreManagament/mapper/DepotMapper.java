package com.BookStoreManagament.mapper;

import com.BookStoreManagament.dto.request.AddBookToDepotRequestDto;
import com.BookStoreManagament.dto.response.AllDepotsBooksResponseDto;
import com.BookStoreManagament.dto.response.BookInfoForDepotResponseDto;
import com.BookStoreManagament.dto.response.BookResponseDto;
import com.BookStoreManagament.entity.DepotEntity;
import com.BookStoreManagament.exception.DepotNotFoundException;
import com.BookStoreManagament.service.BookService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DepotMapper {

    private final BookService bookService;

    public DepotMapper(BookService bookService) {
        this.bookService = bookService;
    }

    public DepotEntity toEntityFromAddBookToDepotRequestDto(AddBookToDepotRequestDto request)
    {
        if(request == null)
        {
            throw new DepotNotFoundException("Request is null");
        }

        DepotEntity entity = new DepotEntity();
        BookInfoForDepotResponseDto info = bookService.getBookInitialPriceAndBookCategoryById(request.getFkBookId());

        entity.setQuantityOfBooks(request.getQuantityOfBooks());
        entity.setFkBookId(request.getFkBookId());
        entity.setImportDate(LocalDate.now());
        entity.setInitialPrice(info.getInitialPrice());
        entity.setFkCategoryId(info.getCategoryId());

        return entity;
    }

    /*public AllDepotsBooksResponseDto toAllDepotsBooksResponseDto(DepotEntity entity)
    {
        return new AllDepotsBooksResponseDto(
                entity.getQuantityOfBooks(),
                entity.getInitialPrice(),
                entity.getImportDate(),
                entity.getFkBookId(),
                entity.getFkCategoryId()
        );
    }*/

    public List<AllDepotsBooksResponseDto> toAllDepotsBooksResponseDtoList(List<DepotEntity> depotEntities)
    {
        List<AllDepotsBooksResponseDto> responseDtoList = new ArrayList<>();

        for (int i = 0; i < depotEntities.size(); i++) {

            DepotEntity entity = depotEntities.get(i);
            BookResponseDto bookById = bookService.getBookInfoById(entity.getFkBookId());

            responseDtoList.add(new AllDepotsBooksResponseDto(
                    entity.getQuantityOfBooks(),
                    entity.getInitialPrice(),
                    entity.getImportDate(),
                    bookById,
                    bookById.getFkCategoryId()
            ));
        }

        return responseDtoList;
    }

}
