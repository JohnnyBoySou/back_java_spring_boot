package com.wswork.cars.controller;

import com.wswork.cars.entity.Brand;
import com.wswork.cars.entity.Model;
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
    public Page<Model> getAllModels(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return modelRepository.findAll(pageable);
    }

    @PostMapping
    public Model createModel(@RequestBody Model model) {
        return modelRepository.save(model);
    }

    @PutMapping("/{id}")
    public Model updateModel(@PathVariable Long id, @RequestBody Model model) {
        model.setId(id);
        return modelRepository.save(model);
    }

    @DeleteMapping("/{id}")
    public void deleteModel(@PathVariable Long id) {
        modelRepository.deleteById(id);
    }

    @PatchMapping("/{id}")
    public Model patchModel(@PathVariable Long id, @RequestBody Model modelUpdates) {
        Model existingModel = modelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Model not found with id " + id));

        if (modelUpdates.getName() != null) existingModel.setName(modelUpdates.getName());
        if (modelUpdates.getFipe() != null) existingModel.setFipe(modelUpdates.getFipe());

        if (modelUpdates.getBrand() != null && modelUpdates.getBrand().getId() != null) {
            Brand brand = new Brand();
            brand.setId(modelUpdates.getBrand().getId());
            existingModel.setBrand(brand);
        }

        return modelRepository.save(existingModel);
    }

    @GetMapping("/{id}")
    public Model getModelById(@PathVariable Long id) {
        return modelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Model not found with id " + id));
    }
}
