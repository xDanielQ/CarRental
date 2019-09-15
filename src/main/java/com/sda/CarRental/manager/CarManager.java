package com.sda.CarRental.manager;

import com.sda.CarRental.model.Car;
import com.sda.CarRental.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.sda.CarRental.model.CarColour.*;

@Service
public class CarManager {
    private CarRepository carRepository;

    @Autowired
    public CarManager(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Iterable<Car> showAll() {
        return carRepository.findAll();
    }

    public Optional<Car> findById(Long id) {
        return carRepository.findById(id);
    }

    public Car save(Car car) {
        return carRepository.save(car);
    }

    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void defaultCarDatabase() {
        save(new Car("Fiat", "Panda", WHITE, 30000));
        save(new Car("Opel", "Insignia", BLACK, 80000));
        save(new Car("Volkswagen", "Passat", BLACK, 180000));
        save(new Car("Dodge", "Viper", RED, 380000));
    }
}
