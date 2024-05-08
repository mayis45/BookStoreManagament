package com.BookStoreManagament.controller;

import com.BookStoreManagament.dto.request.CreatePublisherAccountRequestDto;
import com.BookStoreManagament.dto.request.LogInPublisherRequestDto;
import com.BookStoreManagament.dto.request.UpdateDescriptionPublisherAccountRequestDto;
import com.BookStoreManagament.dto.request.UpdateProfilePhotoPublisherAccountRequestDto;
import com.BookStoreManagament.dto.response.PublisherAccountResponseDto;
import com.BookStoreManagament.service.PublisherAccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/publisher-api")
public class PublisherAccountController {

    private final PublisherAccountService publisherAccountService;

    public PublisherAccountController(PublisherAccountService publisherAccountService) {
        this.publisherAccountService = publisherAccountService;
    }

    @GetMapping("/publisher/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public PublisherAccountResponseDto getPublisherInfo(@PathVariable String id) {
       return publisherAccountService.getPublisherInfo(id);
    }

    @PostMapping("/create-account")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAccount(@RequestBody @Valid CreatePublisherAccountRequestDto request) {
        publisherAccountService.createAccount(request);
    }

    @PostMapping("/login-account")
    public String logInAccount(@RequestBody LogInPublisherRequestDto request){
        return publisherAccountService.logInAccount(request);
    }

    @PutMapping("/delete-account/{publisherId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteAccount(@PathVariable String publisherId) {
        publisherAccountService.deleteAccount(publisherId);
    }

    @PutMapping("/change-profilephoto")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public void changePublisherAccountProfilePhoto(@RequestBody UpdateProfilePhotoPublisherAccountRequestDto request){
        publisherAccountService.changePublisherAccountProfilePhoto(request);
    }

    @PutMapping("/change-description")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public void changePublisherAccountDescription(@RequestBody UpdateDescriptionPublisherAccountRequestDto request){
        publisherAccountService.changePublisherAccountDescription(request);
    }
}
