package org.huy.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.util.UUID;

@Table("shop")
@Getter
@Setter
@NoArgsConstructor
public class Shop {

    @PrimaryKey
    @Column("shop_id")
    private UUID shopId;

    private String name;

    @Column("is_restrict")
    private Boolean isRestrict;

    private String description;

    private Address address;

    @UserDefinedType
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Address {

        private String street;

        private String state;

        private String city;

        private String country;
    }

    /**
     * https://jagadish-ramachandran.medium.com/cassandra-for-e-commerce-cart-checkout-de06ecfaead2
     *
     */
}
