package com.sparta.mypostsparta.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("select p from Post p order by p.createdAt desc")
    List<Post> findAllDesc();
}
