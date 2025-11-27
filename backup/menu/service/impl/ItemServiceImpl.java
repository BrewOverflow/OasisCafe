package com.ncs.cafe.app.menu.service.impl;

import com.ncs.cafe.app.constants.AppConstants;
import com.ncs.cafe.app.exception.ItemNotFoundException;
import com.ncs.cafe.app.menu.model.Item;
import com.ncs.cafe.app.menu.model.ItemCategory;
import com.ncs.cafe.app.menu.dao.ItemDAO;
import com.ncs.cafe.app.menu.service.ItemService;
import com.ncs.cafe.app.order.model.Order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Service implementation for item management.
 * Demonstrates proper business logic, transaction management, and error handling.
 */
@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    private static final Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);

    private final ItemDAO itemDAO;
    private final ItemService itemService;

    @Autowired
    public ItemServiceImpl(ItemDAO itemDAO, ItemService itemService) {
        this.itemDAO = itemDAO;
        logger.info("ItemServiceImpl initialized successfully");
        this.itemService = itemService;
    }

    @Override
    @Transactional
    public Item createItem(Item item) {
        logger.debug("Attempting to create new item: {}", item.getName());

        try {
            // Validate item name uniqueness
            if (itemDAO.existsByName(item.getName())) {
                logger.warn("Item with name '{}' already exists", item.getName());
                throw new IllegalArgumentException("Item with name '" + item.getName() + "' already exists");
            }

            // Validate business rules
            validateItem(item);

            var savedItem = itemDAO.save(item);
            logger.info("Successfully created item with ID: {} and name: {}", savedItem.getId(), savedItem.getName());

            return savedItem;

        } catch (Exception exception) {
            logger.error("Failed to create item: {}", item.getName(), exception);
            throw exception;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Item getItemById(Long id) throws ItemNotFoundException {
        logger.debug("Retrieving item by ID: {}", id);

        Item item = itemDAO.findById(id).orElseThrow(() -> {
                    logger.warn("Item not found with ID: {}", id);
                    return new ItemNotFoundException(id);
                });

        logger.debug("Successfully retrieved item: {}", item.getName());
        return item;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Item> getAllItems() {
        logger.debug("Retrieving all items");

        var items = itemDAO.findAll();
        logger.info("Retrieved {} items from database", items.size());

        return items;
    }

    @Override
    @Transactional
    public Item updateItem(Long id, Item itemDetails) {
        logger.debug("Updating item with ID: {}", id);

        try {
            var existingItem = getItemById(id);

            // Check if name is being changed and validate uniqueness
            if (!existingItem.getName().equals(itemDetails.getName()) &&
                    itemDAO.existsByName(itemDetails.getName())) {
                logger.warn("Item with name '{}' already exists", itemDetails.getName());
                throw new IllegalArgumentException("Item with name '" + itemDetails.getName() + "' already exists");
            }

            // Update fields
            existingItem.setName(itemDetails.getName());
            existingItem.setDescription(itemDetails.getDescription());
            existingItem.setPrice(itemDetails.getPrice());
            existingItem.setCategory(itemDetails.getCategory());
            existingItem.setType(itemDetails.getType());

            // Validate updated item
            validateItem(existingItem);

            var updatedItem = itemDAO.save(existingItem);
            logger.info("Successfully updated item with ID: {}", id);

            return updatedItem;

        } catch (Exception exception) {
            logger.error("Failed to update item with ID: {}", id, exception);
            throw exception;
        }
    }

    @Override
    @Transactional
    public void deleteItem(Long id) {
        logger.debug("Deleting item with ID: {}", id);

        try {
            if (!itemDAO.existsById(id)) {
                logger.warn("Attempted to delete non-existent item with ID: {}", id);
                throw new IllegalArgumentException("Item not found with ID: " + id);
            }

            itemDAO.deleteById(id);
            logger.info("Successfully deleted item with ID: {}", id);

        } catch (Exception exception) {
            logger.error("Failed to delete item with ID: {}", id, exception);
            throw exception;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Item> getAvailableItems() {
        logger.debug("Retrieving all available items");

        var availableItems = itemDAO.findByAvailableTrue();
        logger.debug("Found {} available items", availableItems.size());

        return availableItems;
    }

    @Override
    public void run() {

    }

    @Override
    @Transactional(readOnly = true)
    public List<Item> getItemsByCategory(ItemCategory category) {
        logger.debug("Retrieving items by category: {}", category);

        var items = itemDAO.findByCategory(category);
        logger.debug("Found {} items in category: {}", items.size(), category);

        return items;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Item> getAvailableItemsByCategory(ItemCategory category) {
        logger.debug("Retrieving available items by category: {}", category);

        var items = itemDAO.findByCategoryAndAvailableTrue(category);
        logger.debug("Found {} available items in category: {}", items.size(), category);

        return items;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Item> searchItems(String keyword) {
        logger.debug("Searching items with keyword: {}", keyword);

        if (keyword == null || keyword.trim().isEmpty()) {
            logger.warn("Search keyword is empty");
            return List.of();
        }

        var items = itemDAO.searchItems(keyword.trim());
        logger.debug("Found {} items matching keyword: {}", items.size(), keyword);

        return items;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Item> getItemsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        logger.debug("Retrieving items in price range: {} - {}", minPrice, maxPrice);

        validatePriceRange(minPrice, maxPrice);

        var items = itemDAO.findByPriceBetween(minPrice, maxPrice);
        logger.debug("Found {} items in price range {} - {}", items.size(), minPrice, maxPrice);

        return items;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Item> getPremiumItems(BigDecimal minPrice) {
        logger.debug("Retrieving premium items with min price: {}", minPrice);

        if (minPrice == null || minPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Minimum price must be non-negative");
        }

        var premiumItems = itemDAO.findPremiumItems(minPrice);
        logger.debug("Found {} premium items", premiumItems.size());

        return premiumItems;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Item> getAvailableItems(Pageable pageable) {
        logger.debug("Retrieving available items with pagination: {}", pageable);

        var itemPage = itemDAO.findByAvailableTrue(pageable);
        logger.debug("Retrieved page {} of {} with {} items",
                pageable.getPageNumber(), itemPage.getTotalPages(), itemPage.getNumberOfElements());

        return itemPage;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Item> getItemsByCategory(ItemCategory category, Pageable pageable) {
        logger.debug("Retrieving items by category {} with pagination: {}", category, pageable);

        var itemPage = itemDAO.findByCategory(category, pageable);
        logger.debug("Retrieved page {} of {} with {} items in category {}",
                pageable.getPageNumber(), itemPage.getTotalPages(),
                itemPage.getNumberOfElements(), category);

        return itemPage;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isItemNameAvailable(String name) {
        logger.debug("Checking if item name is available: {}", name);

        if (name == null || name.trim().isEmpty()) {
            return false;
        }

        var isAvailable = !itemDAO.existsByName(name.trim());
        logger.debug("Item name '{}' available: {}", name, isAvailable);

        return isAvailable;
    }

    @Override
    @Transactional
    public Item updateItemPrice(Long id, BigDecimal newPrice) {
        logger.debug("Updating price for item ID: {} to {}", id, newPrice);

        if (newPrice == null || newPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price must be non-negative");
        }

        var item = getItemById(id);
        item.setPrice(newPrice);

        var updatedItem = itemDAO.save(item);
        logger.info("Successfully updated price for item ID: {} to {}", id, newPrice);

        return updatedItem;
    }

    @Override
    @Transactional
    public Item updateItemStock(Long id, boolean available) {
        logger.debug("Updating stock availability for item ID: {} to {}", id, available);

        var item = getItemById(id);
        item.setAvailable(available);

        var updatedItem = itemDAO.save(item);
        logger.info("Successfully updated stock availability for item ID: {} to {}", id, available);

        return updatedItem;
    }

    @Override
    @Transactional(readOnly = true)
    public int getAvailableItemsCount() {
        logger.debug("Counting available items");

        var count = itemDAO.countAvailableItems();
        logger.debug("Total available items: {}", count);

        return count;
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal getAverageItemPrice() {
        logger.debug("Calculating average item price");

        var averagePrice = itemDAO.getAveragePrice();
        var result = averagePrice != null ? BigDecimal.valueOf(averagePrice) : BigDecimal.ZERO;

        logger.debug("Average item price: {}", result);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Item getMostExpensiveItem() {
        logger.debug("Finding most expensive item");

        var maxPrice = itemDAO.getMaxPrice();
        if (maxPrice == null) {
            return null;
        }

        var items = itemDAO.findByPriceBetween(maxPrice, maxPrice);
        var mostExpensive = items.isEmpty() ? null : items.get(0);

        logger.debug("Most expensive item: {}", mostExpensive);
        return mostExpensive;
    }

    @Override
    @Transactional(readOnly = true)
    public Item getLeastExpensiveItem() {
        logger.debug("Finding least expensive item");

        var minPrice = itemDAO.getMinPrice();
        if (minPrice == null) {
            return null;
        }

        var items = itemDAO.findByPriceBetween(minPrice, minPrice);
        var leastExpensive = items.isEmpty() ? null : items.get(0);

        logger.debug("Least expensive item: {}", leastExpensive);
        return leastExpensive;
    }

    @Override
    @Transactional
    public List<Item> createItems(List<Item> items) {
        logger.debug("Creating {} items in bulk", items.size());

        try {
            // Validate all items before saving
            items.forEach(this::validateItem);

            var savedItems = itemDAO.saveAll(items);
            logger.info("Successfully created {} items in bulk", savedItems.size());

            return savedItems;

        } catch (Exception exception) {
            logger.error("Failed to create items in bulk", exception);
            throw exception;
        }
    }

    @Override
    @Transactional
    public void updateItemsAvailability(List<Long> itemIds, boolean available) {
        logger.debug("Updating availability for {} items to {}", itemIds.size(), available);

        try {
            var items = itemDAO.findAllById(itemIds);
            var now = LocalDateTime.now();

            items.forEach(item -> {
                item.setAvailable(available);
            });

            itemDAO.saveAll(items);
            logger.info("Successfully updated availability for {} items to {}", items.size(), available);

        } catch (Exception exception) {
            logger.error("Failed to update items availability", exception);
            throw exception;
        }
    }

    final String SIZE_SMALL = "SMALL";
    final String SIZE_MEDIUM = "MEDIUM";
    final String SIZE_LARGE = "LARGE";
    final String SIZE_EXTRA_LARGE = "EXTRA_LARGE";

    Order order = new Order();

    Order order2 = new Order("a", "b");

    int count = 0;

    public void orderItem() {};

    public void run(Item item, boolean condition1, boolean condition2, boolean condition3, boolean condition4, boolean condition5, boolean condition6) throws Exception {

        int a = 0 + 1;
        int b = 0;
        int c = 0;
        int d = 0;
        int e = 0;


        try {
            doSomething();
        } catch (Exception exception) {
            logger.error("Failed", exception);
        } finally {
            try {
                doSomethingElse();
            } catch (Exception exception) {
                logger.error("Failed", exception);
            }
        }


//        if (condition1) {
//            if (condition2) {
//                if (condition3) {
//                    if (condition4) {
//                        if (condition5) {
//                            doSomething(); //AVOID
//                        }
//                    }
//                }
//            }
//        }



//        if (item.isAvailable()) {
//            int count = 0; // Avoid
//            doSomething(count);
//        }
//
//        count ++;
//
//        Item item = new Item();
//
//        if (item.isAvailable()) {
//            doSomething();
//        }
//
//        computeAverage();
//        compAvg(); // AVOID
//
//
//
//
//
//        // AVOID
//
//        a = SIZE_SMALL;
//        a = SIZE_MEDIUM;
//        a = SIZE_LARGE;
//        a = SIZE_EXTRA_LARGE;
//
//        int c = AppConstants.MAX_NAME_LENGTH;
//        c = AppConstants.MAX_DESCRIPTION_LENGTH;
//        c = AppConstants.MAX_EMAIL_LENGTH;
//        c = AppConstants.MAX_PHONE_LENGTH;
//        c = AppConstants.MAX_CODE_LENGTH;
//        c = AppConstants.MAX_NOTES_LENGTH;
//
//
//        Integer length = Integer.valueOf(0);
//
//        c = length.compareTo(1);

    }

//    public void doSomething(int count) {}

    public void doSomething() throws Exception {
        if (0 == count) {
            throw new Exception();
        }
    }

    public void doSomethingElse() throws Exception {
        if (0 == count) {
            throw new Exception();
        }
    }

    public void computeAverage() {}
    public void compAvg() {}

    // Private helper methods
    private void validateItem(Item item) {
        if (item.getName() == null || item.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Item name cannot be empty");
        }

        if (item.getPrice() == null || item.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Item price must be non-negative");
        }

        if (item.getCategory() == null) {
            throw new IllegalArgumentException("Item category cannot be null");
        }

        if (item.getType() == null) {
            throw new IllegalArgumentException("Item type cannot be null");
        }

    }

    private void validatePriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        if (minPrice == null || maxPrice == null) {
            throw new IllegalArgumentException("Both minPrice and maxPrice must be provided");
        }

        if (minPrice.compareTo(BigDecimal.ZERO) < 0 || maxPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Prices cannot be negative");
        }

        if (minPrice.compareTo(maxPrice) > 0) {
            throw new IllegalArgumentException("minPrice cannot be greater than maxPrice");
        }
    }
}
