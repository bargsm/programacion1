package com.myjavablog.repository;

import com.myjavablog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByEmail(String email);
    public User findFirstByOrderByIdAsc();
    public List<User> findAllByOrderByIdAsc();
    public User findUserById(Long id);
    public Optional<User> findById(Integer id);


}
