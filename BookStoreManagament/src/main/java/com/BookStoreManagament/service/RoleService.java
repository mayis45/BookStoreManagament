package com.BookStoreManagament.service;

import com.BookStoreManagament.dto.request.AddRoleRequestDto;
import com.BookStoreManagament.dto.response.RoleResponseDto;
import com.BookStoreManagament.exception.RoleNotFoundException;
import com.BookStoreManagament.mapper.RoleMapper;
import com.BookStoreManagament.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleService(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    public void addRole(AddRoleRequestDto request) {
        roleRepository.save(roleMapper.toEntityFromAddRoleRequestDto(request));
    }

    public RoleResponseDto getRoleById(String id)
    {
        return roleMapper.toRoleResponseDto(roleRepository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException("Role Not Found")));
    }

}

