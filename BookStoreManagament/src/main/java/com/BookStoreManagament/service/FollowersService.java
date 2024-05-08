package com.BookStoreManagament.service;

import com.BookStoreManagament.dto.request.UpdateFollowRequestDto;
import com.BookStoreManagament.dto.request.AddFollowerRequestDto;
import com.BookStoreManagament.entity.FollowersEntity;
import com.BookStoreManagament.exception.FollowerNotFoundException;
import com.BookStoreManagament.mapper.FollowerMapper;
import com.BookStoreManagament.repository.FollowersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class FollowersService {

    private final FollowersRepository followersRepository;
    private final FollowerMapper followerMapper;
    private final PublisherAccountService publisherAccountService;

    public FollowersService(FollowersRepository followersRepository, FollowerMapper followerMapper, PublisherAccountService publisherAccountService) {
        this.followersRepository = followersRepository;
        this.followerMapper = followerMapper;
        this.publisherAccountService = publisherAccountService;
    }

    public void follow(AddFollowerRequestDto request)
    {
        List<FollowersEntity> allFollowers = followersRepository.findAll();

        for(int i=0;i<allFollowers.size();i++)
        {
            FollowersEntity entity = allFollowers.get(i);
            AddFollowerRequestDto dto = followerMapper.toAddFollowerRequestDto(entity);

            if(dto.equals(request) && entity.getFollowStatus() == 0)
            {
                followersRepository.updateFollowers(entity.getId());
                return;
            }
        }

        followersRepository.save(followerMapper.toEntityFromAddFollowerRequestDto(request));
        publisherAccountService.updatePublisherAccountCountOfFollowerById(request.getFkFollowedAccountId());
    }

    public void unFollow(UpdateFollowRequestDto request)
    {
        FollowersEntity entity = followersRepository.findById(request.getId())
                .orElseThrow(() -> new FollowerNotFoundException("Follower Not Found"));
        entity.setFollowStatus((byte)0);

        followersRepository.save(entity);
    }

    public BigDecimal getTotalFollowers(String publisherAccountId)
    {
        return followersRepository.findCountTotalFollowersById(publisherAccountId);
    }
}
