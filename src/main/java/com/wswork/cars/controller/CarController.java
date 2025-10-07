package com.wswork.cars.controller;

import com.wswork.cars.entity.Car;
import com.wswork.cars.repository.CarRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            @RequestParam(defaultValue =  "0") int page,
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

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        carRepository.deleteById(id);
    }

    @PatchMapping("/{id}")
    public Car patchCar(@PathVariable Long id, @RequestBody Car carUpdates) {
        Car existingCar = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found with id " + id));

        if (carUpdates.getYear() != null) existingCar.setYear(carUpdates.getYear());
        if (carUpdates.getFuel() != null) existingCar.setFuel(carUpdates.getFuel());
        if (carUpdates.getDoorCount() != null) existingCar.setDoorCount(carUpdates.getDoorCount());
        if (carUpdates.getColor() != null) existingCar.setColor(carUpdates.getColor());
        if (carUpdates.getModel() != null) existingCar.setModel(carUpdates.getModel());
        if (carUpdates.getRegistrationTimestamp() != null) existingCar.setRegistrationTimestamp(carUpdates.getRegistrationTimestamp());

        return carRepository.save(existingCar);
    }

    @GetMapping("/{id}")
    public Car getCarById(@PathVariable Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found with id " + id));
    }
}
