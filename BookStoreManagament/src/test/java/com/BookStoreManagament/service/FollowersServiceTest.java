package com.BookStoreManagament.service;

import com.BookStoreManagament.mapper.FollowerMapper;
import com.BookStoreManagament.repository.FollowersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

class FollowersServiceTest {

    private FollowersRepository followersRepository;
    private FollowerMapper followerMapper;
    private PublisherAccountService publisherAccountService;

    private FollowersService followersService;

    @BeforeEach
    public void setUp()
    {
        followersRepository = mock(FollowersRepository.class);
        followerMapper = mock(FollowerMapper.class);
        publisherAccountService = mock(PublisherAccountService.class);
        followersService = new FollowersService(followersRepository, followerMapper, publisherAccountService);
    }

    @Test
    void testGetTotalFollowers_when() {
        BigDecimal one = BigDecimal.ONE;

        when(followersRepository.findCountTotalFollowersById("id")).thenReturn(one);

        BigDecimal result = followersService.getTotalFollowers("id");

        assertEquals(result, one);
    }
}