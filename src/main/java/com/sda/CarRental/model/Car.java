package com.sda.CarRental.model;

import javax.persistence.*;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mark;
    private String carModel;

    @Enumerated(EnumType.STRING)
    private CarColour colour;

    double price;

    public Car(String mark, String carModel, CarColour colour, double price) {
        this.mark = mark;
        this.carModel = carModel;
        this.colour = colour;
        this.price = price;
    }

    public Car() {
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
