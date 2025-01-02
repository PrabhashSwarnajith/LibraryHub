package com.libraryhub.library_backend.service;

import com.libraryhub.library_backend.dto.UserDTO;

public interface UserService {

    UserDTO verify(UserDTO userRequest);

    UserDTO registerUser(UserDTO userRequest);

}
