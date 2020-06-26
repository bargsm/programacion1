package com.myjavablog.service;

import com.myjavablog.model.Role;
import com.myjavablog.model.User;
import com.myjavablog.repository.RoleRepository;
import com.myjavablog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findFirstByOrderByIdAsc() {
        return userRepository.findFirstByOrderByIdAsc();
    }

    @Override
    public List<User> findAllByOrderByIdAsc() {
        return userRepository.findAllByOrderByIdAsc();
    }

    @Override
    public User findUserById(Long id){return userRepository.findUserById(id);}

    @Override
    public Optional<User> findById(Integer id){

        return userRepository.findById(id);

    }

    @Override
    public String deleteUser(User user){

        if(user.getActive()==1){
            user.setActive(0);
        }else{
            user.setActive(1);
        }

        userRepository.save(user);

        return  "OK";
    }

    @Override
    public User saveUser(User user, String type) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);

        Role userRole = roleRepository.findByRole(type);
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }
}
