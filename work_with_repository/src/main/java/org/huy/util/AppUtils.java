package org.huy.util;

import org.huy.constant.ProductCategory;

import java.math.BigDecimal;
import java.util.UUID;

public class AppUtils {

    public static boolean isValidUUID(String id) {
        if(id == null || id.isBlank()) return false;

        // There must be other better way.
        try {
            UUID.fromString(id);
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

    public static boolean isValidStock(Integer stock) {
        return stock != null && stock >= 0;
    }

    public static boolean isValidProductCategory(ProductCategory category) {
        return category != null;
    }

    public static boolean isStringEmpty(String str) {
        return str != null && !str.isBlank();
    }

    public static boolean isValidPrice(BigDecimal price) {
        return price != null && price.compareTo(BigDecimal.ZERO) > 0;
    }
}
