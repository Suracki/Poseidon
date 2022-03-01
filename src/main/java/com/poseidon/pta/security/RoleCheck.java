package com.poseidon.pta.security;

import com.poseidon.pta.domain.User;
import com.poseidon.pta.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class RoleCheck {

    @Autowired
    UserRepository userRepository;

    public boolean RoleCheck(String role) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = ((UserDetails) authentication.getPrincipal()).getUsername();
        User user = userRepository.findByUsername(userName);

        return user.getRole().equals(role);

    }

    public boolean isAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = ((UserDetails) authentication.getPrincipal()).getUsername();
        User user = userRepository.findByUsername(userName);

        return user.getRole().equals("ADMIN");

    }


}
