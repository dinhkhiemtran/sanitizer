package com.khiemtran.sanitizer.model.request;

import com.khiemtran.sanitizer.security.Sanitizer;
import com.khiemtran.sanitizer.security.SanitizerUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserRequest implements Sanitizer<UserRequest> {
  private String id;
  private String name;
  private int age;
  private String city;

  @Override
  public UserRequest sanitize(UserRequest user) {
    UserRequest sanitizer = new UserRequest();
    sanitizer.id = SanitizerUtils.sanitizeString(user.id);
    sanitizer.name = SanitizerUtils.sanitizeString(user.name);
    sanitizer.age = Integer.parseInt(SanitizerUtils.sanitizeString(String.valueOf(user.age)));
    sanitizer.city = SanitizerUtils.sanitizeString(user.city);
    return sanitizer;
  }
}
