package org.huy.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.huy.util.AppUtils;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ProductModifyRequest extends AbstractProductRequest {

    private String productId;

    public boolean isValidProductId() {
        return AppUtils.isValidUUID(this.productId);
    }
}
