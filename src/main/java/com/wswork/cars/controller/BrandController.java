package com.wswork.cars.controller;

import com.wswork.cars.entity.BrandEntity;
import com.wswork.cars.repository.BrandRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/brands")
public class BrandController {

    private final BrandRepository brandRepository;

    public BrandController(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @GetMapping
    public Page<BrandEntity> getAllBrands(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return brandRepository.findAll(pageable);
    }

    @PostMapping
    public BrandEntity createBrand(@RequestBody BrandEntity brandEntity) {
        return brandRepository.save(brandEntity);
    }

    @PutMapping("/{id}")
    public BrandEntity updateBrand(@PathVariable Long id, @RequestBody BrandEntity brandEntity) {
        brandEntity.setId(id);
        return brandRepository.save(brandEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteBrand(@PathVariable Long id) {
        brandRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    public BrandEntity getBrandById(@PathVariable Long id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found with id " + id));
    }

    @PatchMapping("/{id}")
    public BrandEntity patchBrand(@PathVariable Long id, @RequestBody BrandEntity brandEntityUpdates) {
        BrandEntity existingBrandEntity = brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found with id " + id));

        if (brandEntityUpdates.getName() != null) {
            existingBrandEntity.setName(brandEntityUpdates.getName());
        }

        return brandRepository.save(existingBrandEntity);
    }

}
