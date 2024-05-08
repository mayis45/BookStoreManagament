package com.BookStoreManagament.mapper;

import com.BookStoreManagament.dto.request.AddRoleRequestDto;
import com.BookStoreManagament.dto.response.RoleResponseDto;
import com.BookStoreManagament.entity.RoleEntity;
import com.BookStoreManagament.exception.RoleNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RoleMapper {

    public RoleEntity toEntityFromAddRoleRequestDto(AddRoleRequestDto request)
    {

        if(request == null)
        {
            throw new RoleNotFoundException("Request is null");
        }

        RoleEntity entity = new RoleEntity();
        entity.setAuthority(request.getAuthority());
        entity.setAuthorityStatus((byte)1);

        return entity;
    }

    public RoleResponseDto toRoleResponseDto(RoleEntity entity)
    {
        return new RoleResponseDto(
                entity.getAuthority()
        );
    }

}
