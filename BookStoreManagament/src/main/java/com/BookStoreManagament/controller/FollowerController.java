package com.BookStoreManagament.controller;

import com.BookStoreManagament.dto.request.AddFollowerRequestDto;
import com.BookStoreManagament.dto.request.UpdateFollowRequestDto;
import com.BookStoreManagament.service.FollowersService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.math.BigDecimal;

@RestController
@RequestMapping("/v1/f1")
public class FollowerController {

    private final FollowersService followersService;

    public FollowerController(FollowersService followersService) {
        this.followersService = followersService;
    }

    @PostMapping("/follow")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public void follow(@RequestBody @Valid AddFollowerRequestDto request) {
        followersService.follow(request);
    }

    @PutMapping("/unfollow")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public void unFollow(@RequestBody @Valid UpdateFollowRequestDto request) {
        followersService.unFollow(request);
    }

    @GetMapping("/totalfollowers/{publisherAccountId}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BigDecimal getTotalFollowers(@PathVariable String publisherAccountId) {
        return followersService.getTotalFollowers(publisherAccountId);
    }

}