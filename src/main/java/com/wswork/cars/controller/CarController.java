package com.wswork.cars.controller;

import com.wswork.cars.entity.Car;
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
    public Page<Car> getAllCars(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return carRepository.findAll(pageable);
    }

    @PostMapping
    public Car createCar(@RequestBody Car car) {
        return carRepository.save(car);
    }

    @PutMapping("/{id}")
    public Car updateCar(@PathVariable Long id, @RequestBody Car car) {
        car.setId(id);
        return carRepository.save(car);
    }

    @PatchMapping("/{id}")
    public Car patchCar(@PathVariable Long id, @RequestBody Car updates) {
        Car existing = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found with id " + id));

        if (updates.getYear() != null) existing.setYear(updates.getYear());
        if (updates.getFuel() != null) existing.setFuel(updates.getFuel());
        if (updates.getDoorCount() != null) existing.setDoorCount(updates.getDoorCount());
        if (updates.getColor() != null) existing.setColor(updates.getColor());
        if (updates.getModel() != null) existing.setModel(updates.getModel());

        return carRepository.save(existing);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        carRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    public Car getCarById(@PathVariable Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found with id " + id));
    }

    @GetMapping("/by-brand/{brandId}")
    public Page<Car> getCarsByBrand(
            @PathVariable Long brandId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return carRepository.findByModel_Brand_Id(brandId, pageable);
    }

}
