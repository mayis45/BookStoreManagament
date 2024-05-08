package com.BookStoreManagament.security;

import com.BookStoreManagament.entity.PublisherAccountEntity;
import com.BookStoreManagament.exception.PublisherAccountNotFoundException;
import com.BookStoreManagament.repository.PublisherAccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final PublisherAccountRepository publisherAccountRepository;

    public MyUserDetailsService(PublisherAccountRepository publisherAccountRepository) {
        this.publisherAccountRepository = publisherAccountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        PublisherAccountEntity entity = publisherAccountRepository.findPublisherAccountEntityByUsername(username)
                .orElseThrow(() -> new PublisherAccountNotFoundException("Publisher Not Found"));

        return new MyUserDetails(
                entity.getUsername(),
                entity.getPassword(),
                entity.isEnabled(),
                entity.isAccountNonExpired(),
                entity.isAccountNonLocked(),
                entity.isCredentialsNonExpired(),
                entity.getAuthority()
        );
    }
}
