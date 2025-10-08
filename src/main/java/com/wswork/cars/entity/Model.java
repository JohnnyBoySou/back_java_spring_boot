package com.wswork.cars.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double fipe;

    @ManyToOne
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    @JsonBackReference
    private Brand brand;
}
