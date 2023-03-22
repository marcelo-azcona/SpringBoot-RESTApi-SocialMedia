package com.azcona.socialmediaApp.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.azcona.socialmediaApp.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
