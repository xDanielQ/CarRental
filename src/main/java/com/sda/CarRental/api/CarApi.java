package com.sda.CarRental.api;

import com.sda.CarRental.manager.CarManager;
import com.sda.CarRental.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/CarRental")
public class CarApi {
    private CarManager carManager;

    @Autowired
    public CarApi(CarManager carManager) {
        this.carManager = carManager;
    }

    @GetMapping("/cars")
    public Iterable<Car> getAll() {
        return this.carManager.showAll();
    }

    @GetMapping("/car")
    public Optional<Car> getById(@RequestParam Long id) {
        return this.carManager.findById(id);
    }

    @PostMapping
    public Car post(@RequestBody Car car) {
        return this.carManager.save(car);
    }

    @PutMapping
    public Car put(@RequestBody Car cassette) {
        return this.carManager.save(cassette);
    }

    @DeleteMapping
    public void delete(@RequestParam Long id) {
        this.carManager.deleteById(id);
    }


}
