package com.ncs.cafe.app.exception;

import com.ncs.cafe.app.constants.ErrorConstants;

public class ItemNotFoundException extends Exception {
    
    /**
     * Constructs a new ItemNotFoundException for the specified item name.
     *
     * @param itemId the name of the item that was not found
     */
    public ItemNotFoundException(long itemId) {
        super(String.format(ErrorConstants.ERROR_ITEM_NOT_FOUND, itemId));
    }

}
