package org.huy.entity.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

@UserDefinedType("dimension")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dimension {

    private Double length;

    private Double height;

    private Double width;
}
