package com.khiemtran.sanitizer.service;

import com.khiemtran.sanitizer.model.request.UserRequest;
import com.khiemtran.sanitizer.model.response.UserResponse;

public interface UserService {
  UserResponse create(UserRequest userRequest);
}
