package com.libraryhub.library_backend.service;

import com.libraryhub.library_backend.dto.AuthDTO;
import com.libraryhub.library_backend.dto.UserDTO;

public interface AuthService {

    AuthDTO login(UserDTO userRequest);

    UserDTO registerUser(UserDTO userRequest);

}
