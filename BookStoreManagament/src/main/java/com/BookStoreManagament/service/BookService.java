package com.BookStoreManagament.service;

import com.BookStoreManagament.dto.request.AddBookRequestDto;
import com.BookStoreManagament.dto.request.UpdateBookSoldQuantityRequestDto;
import com.BookStoreManagament.dto.response.*;
import com.BookStoreManagament.entity.BookEntity;
import com.BookStoreManagament.exception.BookNotFoundException;
import com.BookStoreManagament.mapper.BookMapper;
import com.BookStoreManagament.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    //Is not api
    public BookResponseDto getBookInfoById(String bookId) {
        return bookMapper.toBookResponseDto(bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book Not Found")));
    }

    //is not api
    public BookInfoForDepotResponseDto getBookInitialPriceAndBookCategoryById(String id) {

        BookEntity entity = bookRepository.findById(id).orElseThrow(
                () -> new BookNotFoundException("Book Not Found"));

        long soldPrice = entity.getBookPrice().longValue();
        long acceptedPrice = (soldPrice * 100) / 110;

        return new BookInfoForDepotResponseDto(
                BigDecimal.valueOf(acceptedPrice),
                entity.getFkCategoryId()
        );
    }

    //is not api
    protected void updateBookEntityByBookSoldQuantity(UpdateBookSoldQuantityRequestDto request) {
        bookRepository.updateBookEntityByBookSoldQuantity(
                request.getId(),
                request.getSoldQuantity()
        );
    }


    public void addBook(AddBookRequestDto request) {
        bookRepository.save(bookMapper.toEntityFromAddBookRequestDto(request));
    }

    public BigDecimal getTotalBookCountByPublisherId(String publisherId) {
        return bookRepository.calculateTotalBookCountByPublisherId(publisherId);
    }

    //is not api
    public BigDecimal getBookPriceById(String id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book Not Found")).getBookPrice();
    }

    public MostPopularBookResponseDto getMostPopularBookByPublisherId(String publisherId) {
        return bookRepository.findBooksSortBySoldQuantityHighToLow().stream()
                .filter(i -> i.getFkPublisherId() == publisherId).findFirst()
                .map(bookMapper::toMostPopularBookResponseDto)
                .orElseThrow(() -> new BookNotFoundException("Most Popular Book Not Found"));
    }

    //Publisherin butun kitablari arasinda categoriyaya gore filterleme edir
    public List<FilterAllBooksResponseDto> getPublishersAllBooksByCategoryId(String categoryId, String publisherId) {

        List<FilterAllBooksResponseDto> collect = bookRepository.findBookEntitiesByFkCategoryId(categoryId).stream()
                .filter(i -> i.getFkPublisherId() == publisherId)
                .map(bookMapper::toFilterAllBooksResponseDto).collect(Collectors.toList());

        if(collect.isEmpty())
        {
            throw new BookNotFoundException("No Books Aviable");
        }

        return collect;
    }

    public List<FilterAllBooksResponseDto> getPublishersAllBooksSortByPriceHighToLow(String publisherId) {
        List<FilterAllBooksResponseDto> collect = bookRepository.findBooksSortByPriceHighToLow().stream()
                .filter(i -> i.getFkPublisherId() == publisherId)
                .map(bookMapper::toFilterAllBooksResponseDto).collect(Collectors.toList());

        if(collect.isEmpty())
        {
            throw new BookNotFoundException("No Books Aviable");
        }

        return collect;
    }

    public List<FilterAllBooksResponseDto> getPublishersAllBooksSortByPriceLowToHigh(String publisherId) {
        List<FilterAllBooksResponseDto> collect = bookRepository.findBooksSortByPriceLowToHigh().stream()
                .filter(i -> i.getFkPublisherId() == publisherId)
                .map(bookMapper::toFilterAllBooksResponseDto).toList();

        if(collect.isEmpty())
        {
            throw new BookNotFoundException("No Books Aviable");
        }

        return collect;
    }

    public List<FilterAllBooksResponseDto> getPublishersAllBooksSortByPublishingDate(String publisherId) {
        List<FilterAllBooksResponseDto> collect = bookRepository.findBooksSortByPublishingDate().stream()
                .filter(i -> i.getFkPublisherId() == publisherId)
                .map(bookMapper::toFilterAllBooksResponseDto).toList();

        if(collect.isEmpty())
        {
            throw new BookNotFoundException("No Books Aviable");
        }

        return collect;
    }

    public List<FilterAllBooksResponseDto> getPublishersAllBooksSortBySoldQuantityHighToLow(String publisherId) {
        List<FilterAllBooksResponseDto> collect = bookRepository.findBooksSortBySoldQuantityHighToLow().stream()
                .filter(i -> i.getFkPublisherId() == publisherId)
                .map(bookMapper::toFilterAllBooksResponseDto).toList();

        if(collect.isEmpty())
        {
            throw new BookNotFoundException("No Books Aviable");
        }

        return collect;
    }

    public List<FilterAllBooksResponseDto> getPublishersAllBooksSortBySoldQuantityLowToHigh(String publisherId) {
        List<FilterAllBooksResponseDto> collect = bookRepository.findBooksSortBySoldQuantityLowToHigh().stream()
                .filter(i -> i.getFkPublisherId() == publisherId)
                .map(bookMapper::toFilterAllBooksResponseDto).toList();

        if(collect.isEmpty())
        {
            throw new BookNotFoundException("No Books Aviable");
        }

        return collect;
    }

    public SearchedBookResponseDto searchBookByName(String bookName) {
        return bookMapper.toSearchedBookResponseDto(bookRepository.findBookEntityByBookName(bookName)
                .orElseThrow(() -> new BookNotFoundException("The book is not available")));
    }

    public List<AllBooksResponseDto> seeAllBooksByPublisherId(String publisherId) {
        List<AllBooksResponseDto> collect = bookRepository.findBookEntitiesByFkPublisherId(publisherId).stream()
                .map(bookMapper::toAllBooksResponseDto).toList();

        if(collect.isEmpty())
        {
            throw new BookNotFoundException("No Books Aviable");
        }

        return collect;
    }

    public PageResponseDto seeAllBooksByPublisherIdWithPagination(int page, int count, String publisherId) {

        Page<BookEntity> allBooks = bookRepository.findAll(PageRequest.of(count, page));

        List<AllBooksResponseDto> collect = allBooks.getContent().stream()
                .filter(i -> i.getFkPublisherId() == publisherId)
                .map(bookMapper::toAllBooksResponseDto).toList();

        if(collect.isEmpty())
        {
            throw new BookNotFoundException("No Books Aviable");
        }

        return new PageResponseDto(
                collect,
                allBooks.getTotalPages(),
                allBooks.getTotalElements(),
                allBooks.hasNext()
        );
    }
}