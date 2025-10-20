package com.inflatable.freelancer.service;

import com.inflatable.freelancer.dto.UserDTO;
import com.inflatable.freelancer.model.User;

import java.util.List;

public interface UserService {
    List<UserDTO> findAll();
    UserDTO findById(Long id);
    UserDTO create(User user);
    UserDTO update(Long id, User user);
    void delete(Long id);
    User getCurrentUser();
}