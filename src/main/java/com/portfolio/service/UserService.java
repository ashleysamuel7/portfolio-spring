package com.portfolio.service;

import com.portfolio.config.security.PortfolioPasswordEncoder;
import com.portfolio.entity.User;
import com.portfolio.exception.ResourceNotFoundException;
import com.portfolio.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final PortfolioPasswordEncoder portfolioPasswordEncoder;

  public User createNewUser(User user) {
    user.setPass(portfolioPasswordEncoder.passwordEncoder().encode(user.getPass()));
    return userRepository.save(user);
  }

  public boolean userLogin(User user) {
    Optional<User> userOpt = userRepository.findByEmail(user.getEmail());
    if (userOpt.isEmpty()) {
      throw new ResourceNotFoundException("User not found");
    }
    User validUser = userOpt.get();
    portfolioPasswordEncoder.passwordEncoder().matches(user.getPass(), validUser.getPass());
    return true;
  }

  public User updateUserByEmail(User user) throws Exception {
    Optional<User> optUser = userRepository.findByEmail(user.getEmail());
    if (optUser.isEmpty()) {
      throw new ResourceNotFoundException("User Id not found");
    }
    return user;
  }
}
