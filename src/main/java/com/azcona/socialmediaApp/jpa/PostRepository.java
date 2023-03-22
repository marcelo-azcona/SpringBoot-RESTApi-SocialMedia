package com.azcona.socialmediaApp.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.azcona.socialmediaApp.user.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
