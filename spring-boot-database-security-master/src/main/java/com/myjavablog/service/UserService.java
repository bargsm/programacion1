package com.myjavablog.service;

import com.myjavablog.model.User;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

public interface UserService {
    public User findUserById(Long id);
    public String deleteUser(User user);
    public User findUserByEmail(String email) ;
    public User saveUser(User user, String type);
    public User findFirstByOrderByIdAsc();
    public List<User> findAllByOrderByIdAsc();
    public Optional<User> findById(Integer id);
}
