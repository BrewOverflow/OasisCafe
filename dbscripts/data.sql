-- ==========================================
-- Columns: ID, NAME, PRICE, DRINK_TYPE, IS_AVAILABLE
-- ==========================================

-- COFFEES (IDs 1, 2, 3)
INSERT INTO TBL_CAFE_DRINK (id, name, price, drink_type, available)
VALUES (1, 'Caramel Macchiato', 4.50, 'Coffee', 1);

INSERT INTO TBL_CAFE_DRINK (id, name, price, drink_type, available)
VALUES (2, 'Cold Brew', 3.75, 'Coffee', 1);

INSERT INTO TBL_CAFE_DRINK (id, name, price, drink_type, available)
VALUES (3, 'Hazelnut Latte', 4.00, 'Coffee', 0); -- Currently Out of Stock!

-- TEAS (IDs 4, 5, 6)
INSERT INTO TBL_CAFE_DRINK (id, name, price, drink_type, available)
VALUES (4, 'Matcha Latte', 4.25, 'Tea', 1);

INSERT INTO TBL_CAFE_DRINK (id, name, price, drink_type, available)
VALUES (5, 'Chamomile', 2.50, 'Tea', 1);

INSERT INTO TBL_CAFE_DRINK (id, name, price, drink_type, available)
VALUES (6, 'Oolong Milk Tea', 3.50, 'Tea', 1);


-- ==========================================
-- Columns: ID, ROAST_LEVEL, CAFFEINE_MG
-- ==========================================

INSERT INTO TBL_CAFE_COFFEE (id, roast_level, caffeine_mg) VALUES (1, 'Medium', 150);
INSERT INTO TBL_CAFE_COFFEE (id, roast_level, caffeine_mg) VALUES (2, 'Dark', 200);
INSERT INTO TBL_CAFE_COFFEE (id, roast_level, caffeine_mg) VALUES (3, 'Light', 120);


-- ==========================================
-- Columns: ID, LEAF_TYPE, STEEP_MINUTES
-- ==========================================

INSERT INTO TBL_CAFE_TEA (id, leaf_type, steep_minutes) VALUES (4, 'Powder', 0);
INSERT INTO TBL_CAFE_TEA (id, leaf_type, steep_minutes) VALUES (5, 'Herbal', 5);
INSERT INTO TBL_CAFE_TEA (id, leaf_type, steep_minutes) VALUES (6, 'Oolong', 3);