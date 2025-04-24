package com.alic3.versioned.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class UserPantry {

    @Id
    private Long id;
    private Long userId;
    @OneToOne
    private BoughtProduct product;

}
