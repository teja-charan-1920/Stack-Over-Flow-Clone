package com.majorproject.StackOverflowClone.service;

import com.majorproject.StackOverflowClone.model.User;
import com.majorproject.StackOverflowClone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByEmail(email);
        return user.map(UserInfoUserDetails::new)
                .orElseThrow(()->new UsernameNotFoundException("User not found"+email));
    }
}
