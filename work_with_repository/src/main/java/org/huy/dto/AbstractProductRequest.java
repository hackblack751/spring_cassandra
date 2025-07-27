package org.huy.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.huy.constant.ProductCategory;
import org.huy.util.AppUtils;

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
        return AppUtils.isValidProductCategory(this.category);
    }

    public boolean isValidStock() {
        return AppUtils.isValidStock(this.stock);
    }

    public boolean isValidName() {
        return AppUtils.isStringEmpty(this.name);
    }

    public boolean isValidPrice() {
        return AppUtils.isValidPrice(this.price);
    }

}
