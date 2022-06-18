package com.alnicode.funvirtualreading.domain.service.impl;

import com.alnicode.funvirtualreading.constants.UserConstants;
import com.alnicode.funvirtualreading.persistence.entity.Role;
import com.alnicode.funvirtualreading.persistence.repository.UserRepository;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * The custom user details service implementation.
 *
 * @author Alben Bustamante
 * @since 1.0
 * @version 1.0
 */
@Service(UserConstants.USER_DETAILS_SERVICE)
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final var user = repository.findByUsernameOrEmail(username, username)
                .orElseThrow(() -> new UsernameNotFoundException("Username or email not found, try again"));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), toAuthorities(user.getRoles()));
    }

    /**
     * Map a roles' collection to {@link GrantedAuthority} collection.
     *
     * @param roles the collection to be mapped.
     * @return the collection mapped.
     */
    private Collection<GrantedAuthority> toAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());
    }

}
