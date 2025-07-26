package org.huy.constant;

import lombok.Getter;

import java.util.Arrays;

public enum ProductCategory {
    TECHNOLOGY("technology"),
    CLOTHES("clothes"),
    FOOD("food"),
    TOYS("toys"),
    ;

    @Getter
    String value;

    ProductCategory(String value) {
        this.value = value;
    }

    public static ProductCategory fromValue(String value) {
        return Arrays.stream(ProductCategory.values())
                .filter(c -> c.getValue().equals(value))
                .findFirst().orElse(null);
    }
}
