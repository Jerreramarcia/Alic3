package com.alic3.versioned.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class BoughtProduct {

    @Id
    private int id;
    private int productId;
    private int quantity;
    private String info;
}
