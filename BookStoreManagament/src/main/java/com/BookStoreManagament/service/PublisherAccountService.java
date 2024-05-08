package com.BookStoreManagament.service;

import com.BookStoreManagament.dto.request.CreatePublisherAccountRequestDto;
import com.BookStoreManagament.dto.request.LogInPublisherRequestDto;
import com.BookStoreManagament.dto.request.UpdateDescriptionPublisherAccountRequestDto;
import com.BookStoreManagament.dto.request.UpdateProfilePhotoPublisherAccountRequestDto;
import com.BookStoreManagament.dto.response.PublisherAccountResponseDto;
import com.BookStoreManagament.dto.response.PublisherResponseDto;
import com.BookStoreManagament.exception.PublisherAccountNotFoundException;
import com.BookStoreManagament.mapper.PublisherAccountMapper;
import com.BookStoreManagament.repository.PublisherAccountRepository;
import com.BookStoreManagament.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class PublisherAccountService {

    private final PublisherAccountRepository publisherAccountRepository;
    private final PublisherAccountMapper publisherAccountMapper;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public PublisherAccountService(PublisherAccountRepository publisherAccountRepository,
                                   PublisherAccountMapper publisherAccountMapper,
                                   JwtService jwtService,
                                   AuthenticationManager authenticationManager) {
        this.publisherAccountRepository = publisherAccountRepository;
        this.publisherAccountMapper = publisherAccountMapper;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public PublisherResponseDto getPublisherById(String publisherId) {
        return publisherAccountMapper.toPublisherResponseDto(publisherAccountRepository.findById(publisherId)
                .orElseThrow(() -> new PublisherAccountNotFoundException("Publisher Not Found")));
    }

    public PublisherAccountResponseDto getPublisherInfo(String id) {
        return publisherAccountMapper.toResponseDto(publisherAccountRepository.findById(id)
                .orElseThrow(() -> new PublisherAccountNotFoundException("Publisher Not Found")));
    }

    protected void updatePublisherAccountCountOfFollowerById(String publisherId) {
        publisherAccountRepository.updatePublisherAccountCountOfFollowerById(publisherId);
    }

    public void createAccount(CreatePublisherAccountRequestDto request) {
        publisherAccountRepository.save(publisherAccountMapper.toEntityFromCreatePublisherAccountRequestDto(request));
    }

    public String logInAccount(LogInPublisherRequestDto request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        if(authentication.isAuthenticated())
        {
            return jwtService.generateToken(request.getUsername());
        }

        throw new PublisherAccountNotFoundException("Publisher Not Found");
    }

    public void deleteAccount(String publisherId) {
        publisherAccountRepository.deleteAccountEnabled(publisherId);
        publisherAccountRepository.deleteAccountStatus(publisherId);
    }

    public void changePublisherAccountProfilePhoto(UpdateProfilePhotoPublisherAccountRequestDto request) {
        publisherAccountRepository.changePublisherAccountProfilePhoto(
                request.getPublisherId(),
                request.getNewProfilePhotoURL()
        );
    }

    public void changePublisherAccountDescription(UpdateDescriptionPublisherAccountRequestDto request) {
        publisherAccountRepository.changePublisherAccountDescription(
                request.getPublisherId(),
                request.getNewDescription()
        );
    }


}
