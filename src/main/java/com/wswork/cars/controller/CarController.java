package com.wswork.cars.controller;

import com.wswork.cars.entity.CarEntity;
import com.wswork.cars.repository.CarRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarRepository carRepository;

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping
    public Page<CarEntity> getAllCars(
            @RequestParam(defaultValue =  "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return carRepository.findAll(pageable);
    }

    @PostMapping
    public CarEntity createCar(@RequestBody CarEntity carEntity) {
        return carRepository.save(carEntity);
    }

    @PutMapping("/{id}")
    public CarEntity updateCar(@PathVariable Long id, @RequestBody CarEntity carEntity) {
        carEntity.setId(id);
        return carRepository.save(carEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        carRepository.deleteById(id);
    }

    @PatchMapping("/{id}")
    public CarEntity patchCar(@PathVariable Long id, @RequestBody CarEntity carEntityUpdates) {
        CarEntity existingCarEntity = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found with id " + id));

        if (carEntityUpdates.getYear() != null) existingCarEntity.setYear(carEntityUpdates.getYear());
        if (carEntityUpdates.getFuel() != null) existingCarEntity.setFuel(carEntityUpdates.getFuel());
        if (carEntityUpdates.getDoorCount() != null) existingCarEntity.setDoorCount(carEntityUpdates.getDoorCount());
        if (carEntityUpdates.getColor() != null) existingCarEntity.setColor(carEntityUpdates.getColor());
        if (carEntityUpdates.getModelEntity() != null) existingCarEntity.setModelEntity(carEntityUpdates.getModelEntity());
        if (carEntityUpdates.getRegistrationTimestamp() != null) existingCarEntity.setRegistrationTimestamp(carEntityUpdates.getRegistrationTimestamp());

        return carRepository.save(existingCarEntity);
    }

    @GetMapping("/{id}")
    public CarEntity getCarById(@PathVariable Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found with id " + id));
    }
}
