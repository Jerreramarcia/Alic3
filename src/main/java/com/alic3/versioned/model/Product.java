package com.alic3.versioned.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "products")
public class Product {

    @Id
    private Long id;

    private String ean = "";
    private String display_name;
    private String thumbnail;
    private double unitPrice;
    private double unitSize;
    private String sizeFormat;

    @ManyToOne
    @JoinColumn(name = "subcategory_id")
    @JsonIgnore // evita bucles si usas Jackson
    private SubCategory subCategory;
}
