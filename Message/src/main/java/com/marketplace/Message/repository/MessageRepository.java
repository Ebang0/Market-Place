package com.marketplace.Message.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marketplace.Message.entity.Message;

public interface MessageRepository extends JpaRepository<Message,Long>{
    List<Message> findByUserId1(Long userId1);
    List<Message> findByUserId2(Long userId2);
}
