package com.BookStoreManagament.service;

import com.BookStoreManagament.dto.response.RoleResponseDto;
import com.BookStoreManagament.entity.RoleEntity;
import com.BookStoreManagament.exception.RoleNotFoundException;
import com.BookStoreManagament.mapper.RoleMapper;
import com.BookStoreManagament.repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

class RoleServiceTest {

    private RoleRepository roleRepository;
    private RoleMapper roleMapper;
    private RoleService roleService;

    @BeforeEach
    public void setUp()
    {
        roleRepository = mock(RoleRepository.class);
        roleMapper = mock(RoleMapper.class);
        roleService = new RoleService(roleRepository, roleMapper);
    }

    @Test
    void testGetRoleById_whenRoleIdExist_shouldReturnRole() {
        RoleEntity entity = new RoleEntity("id", "ROLE_USER", (byte)1);
        RoleResponseDto responseDto = new RoleResponseDto("ROLE_USER");

        when(roleRepository.findById("id")).thenReturn(Optional.of(entity));
        when(roleMapper.toRoleResponseDto(entity)).thenReturn(responseDto);

        RoleResponseDto result = roleService.getRoleById("id");

        assertEquals(result, responseDto);
    }

    @Test
    void testGetRoleById_whenRoleIdDoesNotExist_shouldThrowRoleNotFound()
    {
        when(roleRepository.findById("id")).thenReturn(Optional.empty());

        assertThrows(RoleNotFoundException.class,
                () -> roleService.getRoleById("id"));
    }
}