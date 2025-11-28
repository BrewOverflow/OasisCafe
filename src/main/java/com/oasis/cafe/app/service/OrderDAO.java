package com.oasis.cafe.app.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oasis.cafe.app.model.Order;

@Repository
public interface OrderDAO extends JpaRepository<Order, Long> { }
