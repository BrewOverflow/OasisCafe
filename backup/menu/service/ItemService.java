package com.ncs.cafe.app.menu.service;

import com.ncs.cafe.app.exception.ItemNotFoundException;
import com.ncs.cafe.app.menu.model.Item;
import com.ncs.cafe.app.menu.model.ItemCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

/**
 * Service interface for item management operations.
 * Demonstrates proper service layer abstraction and business logic separation.
 */
public interface ItemService {

    // Basic CRUD operations
//    Item createItem(Item item);
    Item getItemById(Long id) throws ItemNotFoundException;
    List<Item> getAllItems();
    Item updateItem(Long id, Item item);
    void deleteItem(Long id);

    // Availability management
    Item activateItem(Long id);
    Item deactivateItem(Long id);

    // Query operations
//    List<Item> getAvailableItems();
    List<Item> getItemsByCategory(ItemCategory category);
    List<Item> getAvailableItemsByCategory(ItemCategory category);
    List<Item> searchItems(String keyword);
    List<Item> getItemsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);

    // Specialized queries
    List<Item> getPremiumItems(BigDecimal minPrice);

    // Pagination support
    Page<Item> getAvailableItems(Pageable pageable);
    Page<Item> getItemsByCategory(ItemCategory category, Pageable pageable);

    // Business logic operations
    boolean isItemNameAvailable(String name);
    Item updateItemPrice(Long id, BigDecimal newPrice);
    Item updateItemStock(Long id, boolean available);

    // Analytics and reporting
    int getAvailableItemsCount();
    BigDecimal getAverageItemPrice();
    Item getMostExpensiveItem();
    Item getLeastExpensiveItem();

    // Bulk operations
    List<Item> createItems(List<Item> items);
    void updateItemsAvailability(List<Long> itemIds, boolean available);


    Item createItem(Item item);
    List<Item> getAvailableItems();
    void run();
    void doSomething();


}