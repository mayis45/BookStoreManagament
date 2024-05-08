package com.BookStoreManagament.controller;

import com.BookStoreManagament.dto.request.AddBookRequestDto;
import com.BookStoreManagament.dto.response.*;
import com.BookStoreManagament.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/v1/book-api")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/add-book")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public void addBook(@RequestBody AddBookRequestDto request){
        bookService.addBook(request);
    }

    @GetMapping("/totalbookcount/{publisherId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public BigDecimal getTotalBookCountByPublisherId(@PathVariable String publisherId){
        return bookService.getTotalBookCountByPublisherId(publisherId);
    }

    @GetMapping("/mostpopularbook/{publisherId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public MostPopularBookResponseDto getMostPopularBookByPublisherId(@PathVariable String publisherId){
        return bookService.getMostPopularBookByPublisherId(publisherId);
    }

    @GetMapping("/allbooksby-categoryid-and-publisherid/{categoryId}/{publisherId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public List<FilterAllBooksResponseDto> getPublishersAllBooksByCategoryId(
            @PathVariable String categoryId,
            @PathVariable String publisherId
    ){
        return bookService.getPublishersAllBooksByCategoryId(categoryId, publisherId);
    }

    @GetMapping("/allbooksby-sortbyprice-desc/{publisherId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public List<FilterAllBooksResponseDto> getPublishersAllBooksSortByPriceHighToLow(@PathVariable String publisherId){
        return bookService.getPublishersAllBooksSortByPriceHighToLow(publisherId);
    }

    @GetMapping("/allbooksby-sortbyprice/{publisherId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public List<FilterAllBooksResponseDto> getPublishersAllBooksSortByPriceLowToHigh(@PathVariable String publisherId){
        return bookService.getPublishersAllBooksSortByPriceLowToHigh(publisherId);
    }

    @GetMapping("/allbooksby-sortbydate/{publisherId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public List<FilterAllBooksResponseDto> getPublishersAllBooksSortByPublishingDate(@PathVariable String publisherId){
        return bookService.getPublishersAllBooksSortByPublishingDate(publisherId);
    }

    @GetMapping("/allbooksby-sortbysoldquantity-desc/{publisherId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public List<FilterAllBooksResponseDto> getPublishersAllBooksSortBySoldQuantityHighToLow(@PathVariable String publisherId){
        return bookService.getPublishersAllBooksSortBySoldQuantityHighToLow(publisherId);
    }

    @GetMapping("/allbooksby-sortbysoldquantity/{publisherId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public List<FilterAllBooksResponseDto> getPublishersAllBooksSortBySoldQuantityLowToHigh(@PathVariable String publisherId){
        return bookService.getPublishersAllBooksSortBySoldQuantityLowToHigh(publisherId);
    }

    @GetMapping("/searchbook/{bookName}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public SearchedBookResponseDto searchBookByName(@PathVariable String bookName){
        return bookService.searchBookByName(bookName);
    }

    @GetMapping("/allbooks/{publisherId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public List<AllBooksResponseDto> seeAllBooksByPublisherId(@PathVariable String publisherId){
        return bookService.seeAllBooksByPublisherId(publisherId);
    }

    @GetMapping("/allbooks-pagination/{publisherId}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public PageResponseDto seeAllBooksByPublisherIdWithPagination(
            @RequestParam(value = "page")  int page,
            @RequestParam(value = "count") int count,
            @PathVariable String publisherId){
        return bookService.seeAllBooksByPublisherIdWithPagination(page, count, publisherId);
    }
}