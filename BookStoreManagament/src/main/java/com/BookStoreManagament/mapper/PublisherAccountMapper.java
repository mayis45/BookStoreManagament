package com.BookStoreManagament.mapper;

import com.BookStoreManagament.config.PasswordConfig;
import com.BookStoreManagament.dto.request.AddRoleRequestDto;
import com.BookStoreManagament.dto.request.CreatePublisherAccountRequestDto;
import com.BookStoreManagament.dto.response.PublisherAccountResponseDto;
import com.BookStoreManagament.dto.response.PublisherResponseDto;
import com.BookStoreManagament.dto.response.RoleResponseDto;
import com.BookStoreManagament.entity.PublisherAccountEntity;
import com.BookStoreManagament.exception.PublisherAccountNotFoundException;
import com.BookStoreManagament.service.RoleService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class PublisherAccountMapper {

    private final PasswordConfig passwordConfig;
    private final RoleService roleService;

    public PublisherAccountMapper(PasswordConfig passwordConfig, RoleService roleService) {
        this.passwordConfig = passwordConfig;
        this.roleService = roleService;
    }

    public PublisherAccountResponseDto toResponseDto(PublisherAccountEntity publisherAccountEntity) {

        PublisherAccountResponseDto publisherAccountResponseDto = new PublisherAccountResponseDto();

        publisherAccountResponseDto.setPublisherAccountName(publisherAccountEntity.getPublisherAccountName());
        publisherAccountResponseDto.setPublisherAccountDescription(publisherAccountEntity.getPublisherAccountDescription());
        publisherAccountResponseDto.setPublisherAccountProfilePhotoUrl(publisherAccountEntity.getPublisherAccountProfilePhotoUrl());
        publisherAccountResponseDto.setPublisherAccountEmail(publisherAccountEntity.getPublisherAccountEmail());
        publisherAccountResponseDto.setPublisherAccountTotalFollowers(publisherAccountEntity.getPublisherAccountTotalFollowers());
        publisherAccountResponseDto.setTotalBookCount(publisherAccountEntity.getTotalBookCount());

        return publisherAccountResponseDto;
    }

    public PublisherAccountEntity toEntityFromCreatePublisherAccountRequestDto(CreatePublisherAccountRequestDto request)
    {

        if(request == null)
        {
            throw new PublisherAccountNotFoundException("Request is null");
        }

        PublisherAccountEntity entity = new PublisherAccountEntity();
        RoleResponseDto role = roleService.getRoleById(request.getRoleId());
        entity.setPublisherAccountName(request.getPublisherAccountName());
        entity.setPublisherAccountDescription("Default Description");
        entity.setPublisherAccountProfilePhotoUrl("Default URL");
        entity.setPublisherAccountEmail(request.getPublisherAccountEmail());
        entity.setPublisherAccountTotalFollowers(BigDecimal.ZERO);
        entity.setPublisherAccountCreationDate(LocalDate.now());
        entity.setPublisherAccountStatus((byte)1);
        entity.setTotalBookCount(BigDecimal.ZERO);
        entity.setUsername(request.getUsername());
        entity.setPassword(passwordConfig.passwordEncoder().encode(request.getPassword()));
        entity.setEnabled(true);
        entity.setAccountNonExpired(true);
        entity.setAccountNonLocked(true);
        entity.setCredentialsNonExpired(true);
        entity.setAuthority(role.getAuthority());

        return entity;
    }

    public PublisherResponseDto toPublisherResponseDto(PublisherAccountEntity entity)
    {
        return new PublisherResponseDto(
                entity.getPublisherAccountName(),
                entity.getPublisherAccountDescription(),
                entity.getPublisherAccountEmail(),
                entity.getPublisherAccountTotalFollowers(),
                entity.getTotalBookCount()
        );
    }

}
