package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = Optional.ofNullable((userRepository.findByUsername(username)));
        if(username.isEmpty()){
            throw  new UsernameNotFoundException(String.format("User '%u not found",username));
        }
        return  new org.springframework.security.core.userdetails.
                User(user.get().getUsername(), user.get().getPassword(),
                mapRolesToAutorities(user.get().getRoles()));
    }
    private Collection<? extends GrantedAuthority> mapRolesToAutorities(Collection<Role> roles){
        return roles.stream().map(r-> new SimpleGrantedAuthority(r.getRoleName())).collect(Collectors.toList());
    }
}
