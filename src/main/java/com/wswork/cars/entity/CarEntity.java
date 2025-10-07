package com.wswork.cars.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant registrationTimestamp;
    @Column(name = "car_year")
    private Integer year;
    private String fuel;
    private Integer doorCount;
    private String color;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private ModelEntity modelEntity;
}
