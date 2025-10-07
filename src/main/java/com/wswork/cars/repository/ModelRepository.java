package com.wswork.cars.repository;

import com.wswork.cars.entity.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<ModelEntity, Long> {
    // Possível filtro por brand, se necessário
}
