package com.investmentapplication.investmentapplication.services.implementation;

import com.investmentapplication.investmentapplication.entity.UserAccountsEntity;
import com.investmentapplication.investmentapplication.repository.UserAccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserAccountsRepository userRepository;

    // This method implements the loadUserByUsername method of UserDetailsService interface
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Retrieve user information from the database based on the provided username
        UserAccountsEntity user = userRepository.findByUsername(username);

        // If no user found with the provided username, throw UsernameNotFoundException
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // Return the user details fetched from the database
        return user;
    }
}