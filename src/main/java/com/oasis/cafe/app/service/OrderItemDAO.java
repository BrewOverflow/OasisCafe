package com.oasis.cafe.app.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oasis.cafe.app.model.OrderItem;

@Repository
public interface OrderItemDAO extends JpaRepository<OrderItem, Long> { }
