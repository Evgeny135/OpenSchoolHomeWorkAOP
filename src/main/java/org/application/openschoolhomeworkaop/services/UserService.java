package org.application.openschoolhomeworkaop.services;

import jakarta.transaction.Transactional;

import org.application.openschoolhomeworkaop.exceptions.ElementNotFoundException;
import org.application.openschoolhomeworkaop.models.User;
import org.application.openschoolhomeworkaop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void addNewUser(User user){
        userRepository.save(user);
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(()-> new ElementNotFoundException("Пользователь не найден"));
    }
    @Transactional
    public User updateUserById(Long id, User updatedUser){
        updatedUser.setId(id);
        return userRepository.save(updatedUser);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
