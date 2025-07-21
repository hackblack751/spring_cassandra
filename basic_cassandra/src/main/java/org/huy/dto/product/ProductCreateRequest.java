package org.huy.dto.product;

import lombok.Data;
import org.huy.entity.product.Dimension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
public class ProductCreateRequest {

    private String category;
    private String name;
    private BigDecimal price;
    private Set<String> tags;
    private Map<String, String> attributes;
    private List<String> images;

    /** Should make a separate DTO instead. */
    private Dimension dimension;
}
