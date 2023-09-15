package com.khiemtran.sanitizer.security;

public interface Sanitizer<T> {
  T sanitize(T t);
}
