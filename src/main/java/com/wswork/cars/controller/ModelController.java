package com.wswork.cars.controller;

import com.wswork.cars.entity.BrandEntity;
import com.wswork.cars.entity.ModelEntity;
import com.wswork.cars.repository.ModelRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/models")
public class ModelController {

    private final ModelRepository modelRepository;

    public ModelController(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @GetMapping
    public Page<ModelEntity> getAllModels(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return modelRepository.findAll(pageable);
    }

    @PostMapping
    public ModelEntity createModel(@RequestBody ModelEntity modelEntity) {
        return modelRepository.save(modelEntity);
    }

    @PutMapping("/{id}")
    public ModelEntity updateModel(@PathVariable Long id, @RequestBody ModelEntity modelEntity) {
        modelEntity.setId(id);
        return modelRepository.save(modelEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteModel(@PathVariable Long id) {
        modelRepository.deleteById(id);
    }

    @PatchMapping("/{id}")
    public ModelEntity patchModel(@PathVariable Long id, @RequestBody ModelEntity modelEntityUpdates) {
        ModelEntity existingModelEntity = modelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Model not found with id " + id));

        if (modelEntityUpdates.getName() != null) existingModelEntity.setName(modelEntityUpdates.getName());
        if (modelEntityUpdates.getFipeValue() != null) existingModelEntity.setFipeValue(modelEntityUpdates.getFipeValue());

        if (modelEntityUpdates.getBrandEntity() != null && modelEntityUpdates.getBrandEntity().getId() != null) {
            BrandEntity brandEntity = new BrandEntity();
            brandEntity.setId(modelEntityUpdates.getBrandEntity().getId());
            existingModelEntity.setBrandEntity(brandEntity);
        }

        return modelRepository.save(existingModelEntity);
    }

    @GetMapping("/{id}")
    public ModelEntity getModelById(@PathVariable Long id) {
        return modelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Model not found with id " + id));
    }
}
