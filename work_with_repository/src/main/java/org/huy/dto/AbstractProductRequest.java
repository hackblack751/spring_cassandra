package org.huy.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.huy.constant.ProductCategory;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractProductRequest {

    private String name;
    private BigDecimal price;
    private ProductCategory category;
    private Integer stock;

    public boolean isValidCategory() {
        return this.category != null;
    }

    public boolean isValidStock() {
        return this.stock != null && this.stock >= 0;
    }

    public boolean isValidName() {
        return this.name != null && !this.name.isBlank();
    }

    public boolean isValidPrice() {
        return this.price != null && this.price.compareTo(BigDecimal.ZERO) > 0;
    }

}
