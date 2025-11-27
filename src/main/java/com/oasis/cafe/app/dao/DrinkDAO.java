package com.oasis.cafe.app.dao;

import com.oasis.cafe.app.model.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinkDAO extends JpaRepository<Drink, Long> { }