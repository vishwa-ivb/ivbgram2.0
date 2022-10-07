package com.ivb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ivb.entity.Login;

@Repository
public interface LoginDAO extends JpaRepository<Login, String>{

}
