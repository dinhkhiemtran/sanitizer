package com.khiemtran.sanitizer.service.imp;

import com.khiemtran.sanitizer.model.request.UserRequest;
import com.khiemtran.sanitizer.model.response.UserResponse;
import com.khiemtran.sanitizer.service.UserService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImp implements UserService {
  private final Map<String, UserRequest> users = new HashMap<>();

  @Override
  public UserResponse create(UserRequest userRequest) {
    users.put(userRequest.getId(), userRequest);
    UserResponse userResponse = new UserResponse();
    userResponse.setId(userRequest.getId());
    userResponse.setName(userRequest.getName());
    userResponse.setAge(userRequest.getAge());
    userResponse.setCity(userRequest.getCity());
    return userResponse;
  }
}
