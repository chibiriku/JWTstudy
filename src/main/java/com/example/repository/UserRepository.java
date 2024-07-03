// UserRepository.java

package com.example.repository;



import org.apache.ibatis.annotations.Mapper;

import com.example.model.UserForm;


@Mapper
public interface UserRepository {
    UserForm findByUsername(String username);
}