package com.BookStoreManagament.service;

import com.BookStoreManagament.dto.request.LogInPublisherRequestDto;
import com.BookStoreManagament.dto.response.PublisherAccountResponseDto;
import com.BookStoreManagament.dto.response.PublisherResponseDto;
import com.BookStoreManagament.entity.PublisherAccountEntity;
import com.BookStoreManagament.exception.PublisherAccountNotFoundException;
import com.BookStoreManagament.mapper.PublisherAccountMapper;
import com.BookStoreManagament.repository.PublisherAccountRepository;
import com.BookStoreManagament.security.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

class PublisherAccountServiceTest {

    private  PublisherAccountRepository publisherAccountRepository;
    private  PublisherAccountMapper publisherAccountMapper;
    private  JwtService jwtService;
    private  AuthenticationManager authenticationManager;
    private PublisherAccountService publisherAccountService;

    @BeforeEach
    public void setUp()
    {
        publisherAccountRepository = mock(PublisherAccountRepository.class);
        publisherAccountMapper = mock(PublisherAccountMapper.class);
        jwtService = mock(JwtService.class);
        authenticationManager = mock(AuthenticationManager.class);
        publisherAccountService = new PublisherAccountService(
                publisherAccountRepository,
                publisherAccountMapper,
                jwtService,
                authenticationManager
        );

    }

    @Test
    void testGetPublisherById_whenPublisherIdExist_shouldReturnPublisher() {

        PublisherAccountEntity entity = new PublisherAccountEntity(
                "id",
                "mayis",
                "asd",
                "asd",
                "asd",
                BigDecimal.TEN,
                LocalDate.now(),
                (byte)1,
                BigDecimal.ZERO,
                "mayis",
                "mayis",
                true,
                true,
                true,
                true,
                "ROLE_ADMIN"
                );

        PublisherResponseDto dto = new PublisherResponseDto(
                "mayis",
                "asd",
                "asd",
                BigDecimal.TEN,
                BigDecimal.ZERO
        );

        when(publisherAccountRepository.findById("id")).thenReturn(Optional.of(entity));
        when(publisherAccountMapper.toPublisherResponseDto(entity)).thenReturn(dto);

        PublisherResponseDto result = publisherAccountService.getPublisherById("id");

        assertEquals(result, dto);
    }

    @Test
    void testGetPublisherById_whenPublisherIdDoesNotExist_shouldThrowPublisherNotFound(){

        when(publisherAccountRepository.findById("id")).thenReturn(Optional.empty());

        assertThrows(PublisherAccountNotFoundException.class,
                () -> publisherAccountService.getPublisherById("id"));
    }

    @Test
    void testGetPublisherInfo_whenPublisherIdExist_shouldReturnPublisher() {
        PublisherAccountEntity entity = new PublisherAccountEntity(
                "id",
                "mayis",
                "asd",
                "asd",
                "asd",
                BigDecimal.TEN,
                LocalDate.now(),
                (byte)1,
                BigDecimal.ZERO,
                "mayis",
                "mayis",
                true,
                true,
                true,
                true,
                "ROLE_ADMIN"
        );

        PublisherAccountResponseDto dto = new PublisherAccountResponseDto(
                "mayis",
                "asd",
                "asd",
                "asd",
                BigDecimal.TEN,
                BigDecimal.ZERO
        );

        when(publisherAccountRepository.findById("id")).thenReturn(Optional.of(entity));
        when(publisherAccountMapper.toResponseDto(entity)).thenReturn(dto);

        PublisherAccountResponseDto result = publisherAccountService.getPublisherInfo("id");

        assertEquals(result, dto);
    }

    @Test
    void testGetPublisherInfo_whenPublisherIdDoesNotExist_shouldThrowPublisherNotFound(){

        when(publisherAccountRepository.findById("id")).thenReturn(Optional.empty());

        assertThrows(PublisherAccountNotFoundException.class,
                () -> publisherAccountService.getPublisherInfo("id"));
    }
}