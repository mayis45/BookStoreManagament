package com.BookStoreManagament.mapper;

import com.BookStoreManagament.dto.request.AddFollowerRequestDto;
import com.BookStoreManagament.entity.FollowersEntity;
import com.BookStoreManagament.exception.FollowerNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class FollowerMapper {

    public FollowersEntity toEntityFromAddFollowerRequestDto(AddFollowerRequestDto request)
    {
        if(request == null)
        {
            throw new FollowerNotFoundException("Request is null");
        }

        if(request.getFkFollowedAccountId().equals(request.getFkFollowerAccountId()))
        {
            throw new FollowerNotFoundException("followedAccountId and followerAccountId are same");
        }

        FollowersEntity followersEntity = new FollowersEntity();
        followersEntity.setFollowStatus((byte)1);
        followersEntity.setFkFollowedAccountId(request.getFkFollowedAccountId());
        followersEntity.setFkFollowerAccountId(request.getFkFollowerAccountId());

        return followersEntity;
    }

    public AddFollowerRequestDto toAddFollowerRequestDto(FollowersEntity followersEntity)
    {
        return new AddFollowerRequestDto(
                followersEntity.getFkFollowedAccountId(),
                followersEntity.getFkFollowerAccountId()
        );
    }

}
