package com.example.Image.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Image.entity.Image;

public interface ImageRepository extends JpaRepository<Image,Long>{

}
