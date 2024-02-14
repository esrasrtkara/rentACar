package com.rentACar.rentACar.core.configurations;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "discount-user")
@Data
public class DiscountConfiguration {
    private String name;
    private Float rate;
    private int rentalCount;
}
