package com.portfolio.service;

import com.portfolio.entity.User;
import com.portfolio.exception.ResourceNotFoundException;
import com.portfolio.repository.PortfolioRepository;
import com.portfolio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createNewUser(User user) {
        return userRepository.save(user);

    }

    public User updateUserByEmail(User user) throws Exception {
        Optional<User> optUser = userRepository.findByEmail(user.getEmail());
        if(optUser.isEmpty()){
            throw  new ResourceNotFoundException("User Id not found");
        }
        return user;
    }

}
