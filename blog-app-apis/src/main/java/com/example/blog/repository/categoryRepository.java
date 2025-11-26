package com.example.blog.repository;

import com.example.blog.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface categoryRepository extends JpaRepository<Category, Integer> {

}
