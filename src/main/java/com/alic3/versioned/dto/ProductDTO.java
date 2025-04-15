package com.alic3.versioned.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDTO {
    private Long id;
    @JsonIgnoreProperties
    private String ean = "";
    private String display_name;
    private String thumbnail;

    @JsonProperty("price_instructions")
    private PriceInstructions priceInstructions;

    public double getUnitPrice() {
        return priceInstructions != null ? priceInstructions.getUnitPrice() : 0.0;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PriceInstructions {
        @JsonProperty("unit_price")
        private double unitPrice;
        @JsonProperty("unit_size")
        private double unitSize;
        @JsonProperty("size_format")
        private String sizeFormat;
    }
}
