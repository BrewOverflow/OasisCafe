package com.ncs.cafe.app.menu.controller;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ncs.cafe.app.exception.ItemNotFoundException;
import com.ncs.cafe.app.menu.model.Item;
import com.ncs.cafe.app.menu.model.ItemCategory;
import com.ncs.cafe.app.menu.service.ItemService;

/**
 * REST Controller for item management operations.
 * Demonstrates proper REST API design, validation, and error handling.
 */
@RestController
@RequestMapping("/api/items")
public class ItemController {

    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        logger.info("Received request to create item: {}", item.getName());

        itemService.run();
        itemService.doSomething();

        try {
            var createdItem = itemService.createItem(item);
            logger.info("Successfully created item with ID: {}", createdItem.getId());
            return ResponseEntity.ok(createdItem);

        } catch (IllegalArgumentException exception) {
            logger.warn("Invalid request to create item: {}", exception.getMessage());
            return ResponseEntity.badRequest().build();
        } catch (Exception exception) {
            logger.error("Internal server error while creating item", exception);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        logger.debug("Received request to get item by ID: {}", id);

        try {
            Item item = itemService.getItemById(id);
            return ResponseEntity.ok(item);

        } catch (ItemNotFoundException exception) {
            logger.warn("Item not found with ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        logger.debug("Received request to get all items");

        var items = itemService.getAllItems();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/available")
    public ResponseEntity<List<Item>> getAvailableItems() {
        logger.debug("Received request to get available items");

        var availableItems = itemService.getAvailableItems();
        return ResponseEntity.ok(availableItems);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Item>> getItemsByCategory(@PathVariable ItemCategory category) {
        logger.debug("Received request to get items by category: {}", category);

        var items = itemService.getAvailableItemsByCategory(category);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Item>> searchItems(@RequestParam String keyword) {
        logger.debug("Received search request with keyword: {}", keyword);

        if (keyword == null || keyword.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var items = itemService.searchItems(keyword);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/price-range")
    public ResponseEntity<List<Item>> getItemsByPriceRange(
            @RequestParam BigDecimal minPrice,
            @RequestParam BigDecimal maxPrice) {

        logger.debug("Received request for items in price range: {} - {}", minPrice, maxPrice);

        try {
            var items = itemService.getItemsByPriceRange(minPrice, maxPrice);
            return ResponseEntity.ok(items);

        } catch (IllegalArgumentException exception) {
            logger.warn("Invalid price range parameters: {}", exception.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/premium")
    public ResponseEntity<List<Item>> getPremiumItems(@RequestParam(defaultValue = "5.00") BigDecimal minPrice) {
        logger.debug("Received request for premium items (min price: {})", minPrice);

        try {
            var premiumItems = itemService.getPremiumItems(minPrice);
            return ResponseEntity.ok(premiumItems);

        } catch (IllegalArgumentException exception) {
            logger.warn("Invalid min price parameter: {}", exception.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<Item>> getAvailableItemsPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        logger.debug("Received paged request - page: {}, size: {}, sort: {}, direction: {}",
                page, size, sortBy, direction);

        var sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        var itemPage = itemService.getAvailableItems(pageable);
        return ResponseEntity.ok(itemPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item itemDetails) {
        logger.info("Received request to update item with ID: {}", id);

        try {
            var updatedItem = itemService.updateItem(id, itemDetails);
            logger.info("Successfully updated item with ID: {}", id);
            return ResponseEntity.ok(updatedItem);

        } catch (IllegalArgumentException exception) {
            logger.warn("Invalid request to update item: {}", exception.getMessage());
            return ResponseEntity.badRequest().build();
        } catch (Exception exception) {
            logger.error("Internal server error while updating item", exception);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PatchMapping("/{id}/price")
    public ResponseEntity<Item> updateItemPrice(@PathVariable Long id, @RequestParam BigDecimal newPrice) {
        logger.info("Received request to update price for item ID: {} to {}", id, newPrice);

        try {
            var updatedItem = itemService.updateItemPrice(id, newPrice);
            return ResponseEntity.ok(updatedItem);

        } catch (IllegalArgumentException exception) {
            logger.warn("Invalid price update request: {}", exception.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/{id}/availability")
    public ResponseEntity<Item> updateItemAvailability(@PathVariable Long id, @RequestParam boolean available) {
        logger.info("Received request to update availability for item ID: {} to {}", id, available);

        try {
            var updatedItem = itemService.updateItemStock(id, available);
            return ResponseEntity.ok(updatedItem);

        } catch (IllegalArgumentException exception) {
            logger.warn("Invalid availability update request: {}", exception.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{id}/activate")
    public ResponseEntity<Item> activateItem(@PathVariable Long id) {
        logger.info("Received request to activate item with ID: {}", id);

        try {
            var activatedItem = itemService.activateItem(id);
            return ResponseEntity.ok(activatedItem);

        } catch (IllegalArgumentException exception) {
            logger.warn("Invalid activation request: {}", exception.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{id}/deactivate")
    public ResponseEntity<Item> deactivateItem(@PathVariable Long id) {
        logger.info("Received request to deactivate item with ID: {}", id);

        try {
            var deactivatedItem = itemService.deactivateItem(id);
            return ResponseEntity.ok(deactivatedItem);

        } catch (IllegalArgumentException exception) {
            logger.warn("Invalid deactivation request: {}", exception.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/check-name")
    public ResponseEntity<Boolean> checkItemNameAvailability(@RequestParam String name) {
        logger.debug("Received request to check name availability: {}", name);

        var isAvailable = itemService.isItemNameAvailable(name);
        return ResponseEntity.ok(isAvailable);
    }

    @GetMapping("/stats/count")
    public ResponseEntity<Long> getAvailableItemsCount() {
        logger.debug("Received request for available items count");

        var count = itemService.getAvailableItemsCount();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/stats/average-price")
    public ResponseEntity<BigDecimal> getAveragePrice() {
        logger.debug("Received request for average item price");

        var averagePrice = itemService.getAverageItemPrice();
        return ResponseEntity.ok(averagePrice);
    }

    @GetMapping("/stats/most-expensive")
    public ResponseEntity<Item> getMostExpensiveItem() {
        logger.debug("Received request for most expensive item");

        var mostExpensive = itemService.getMostExpensiveItem();
        return mostExpensive != null ? ResponseEntity.ok(mostExpensive) : ResponseEntity.notFound().build();
    }

    @GetMapping("/stats/least-expensive")
    public ResponseEntity<Item> getLeastExpensiveItem() {
        logger.debug("Received request for least expensive item");

        var leastExpensive = itemService.getLeastExpensiveItem();
        return leastExpensive != null ? ResponseEntity.ok(leastExpensive) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        logger.info("Received request to delete item with ID: {}", id);

        try {
            itemService.deleteItem(id);
            logger.info("Successfully deleted item with ID: {}", id);
            return ResponseEntity.ok().build();

        } catch (IllegalArgumentException exception) {
            logger.warn("Invalid deletion request: {}", exception.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception exception) {
            logger.error("Internal server error while deleting item", exception);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<Item>> createItemsBulk(@RequestBody List<Item> items) {
        logger.info("Received bulk request to create {} items", items.size());

        try {
            var createdItems = itemService.createItems(items);
            return ResponseEntity.ok(createdItems);

        } catch (Exception exception) {
            logger.error("Failed to create items in bulk", exception);
            return ResponseEntity.badRequest().build();
        }
    }
}