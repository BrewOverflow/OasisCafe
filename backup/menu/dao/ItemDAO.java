package com.ncs.cafe.app.menu.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ncs.cafe.app.menu.model.Item;
import com.ncs.cafe.app.menu.model.ItemCategory;

@Repository
public interface ItemDAO extends JpaRepository<Item, Long> {

    // Basic CRUD operations are provided by JpaRepository

    // Derived query methods
    List<Item> findByCategory(ItemCategory category);
    List<Item> findByAvailableTrue();
    List<Item> findByAvailableFalse();
    List<Item> findByNameContainingIgnoreCase(String name);
    List<Item> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
    List<Item> findByCategoryAndAvailableTrue(ItemCategory category);

    // Complex queries with JPQL
    @Query("SELECT i FROM Item i WHERE i.category = :category AND i.available = true ORDER BY i.name")
    List<Item> findAvailableItemsByCategory(@Param("category") ItemCategory category);

    @Query("SELECT i FROM Item i WHERE i.price > :minPrice AND i.available = true ORDER BY i.price DESC")
    List<Item> findPremiumItems(@Param("minPrice") BigDecimal minPrice);

    @Query("SELECT i FROM Item i WHERE LOWER(i.name) LIKE LOWER(CONCAT('%', :keyword, '%')) AND i.available = true")
    List<Item> searchItems(@Param("keyword") String keyword);

    // Pagination support
    Page<Item> findByAvailableTrue(Pageable pageable);
    Page<Item> findByCategory(ItemCategory category, Pageable pageable);

    // Aggregation queries
    @Query("SELECT COUNT(i) FROM Item i WHERE i.available = true")
    long countAvailableItems();

    @Query("SELECT AVG(i.price) FROM Item i WHERE i.available = true")
    Double getAveragePrice();

    @Query("SELECT MAX(i.price) FROM Item i WHERE i.available = true")
    BigDecimal getMaxPrice();

    @Query("SELECT MIN(i.price) FROM Item i WHERE i.available = true")
    BigDecimal getMinPrice();

    // Check if item exists by name
    boolean existsByName(String name);

    // Find by multiple categories
    @Query("SELECT i FROM Item i WHERE i.category IN :categories AND i.available = true")
    List<Item> findByCategories(@Param("categories") List<ItemCategory> categories);
}