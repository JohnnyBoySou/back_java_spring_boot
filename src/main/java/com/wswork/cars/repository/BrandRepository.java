package com.wswork.cars.repository;

import com.wswork.cars.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Long> {
    // Aqui você pode adicionar consultas customizadas se precisar
}
