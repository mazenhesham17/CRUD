package com.siemens.crud.service.implementation;

import com.siemens.crud.repository.WebUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private WebUserRepository webUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return webUserRepository.findByEmail(username)
                .map(user -> User.builder()
                        .username(user.getEmail())
                        .password(user.getPassword())
                        .roles(user.getRole().toUpperCase())
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    }
}
