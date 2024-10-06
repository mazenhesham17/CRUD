package com.siemens.crud.security.implementation;

import com.siemens.crud.security.CustomUserDetails;
import com.siemens.crud.model.WebUser;
import com.siemens.crud.repository.WebUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private WebUserRepository webUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        WebUser webUser = webUserRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + webUser.getRole().toUpperCase()));
        return new CustomUserDetails(webUser.getId(), webUser.getEmail(), webUser.getPassword(), true,
                authorities);

    }
}
