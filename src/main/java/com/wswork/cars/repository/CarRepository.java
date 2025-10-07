package com.wswork.cars.repository;

import com.wswork.cars.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    // Possível filtro por model, year, color, etc.
}
