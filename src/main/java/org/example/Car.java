package org.example;

import java.util.Objects;

public class Car {

    private int year;
    private String color;
    private String manufacturer;
    private String manufacturingCountry;

    public Car() {
    }

    public Car(int year, String color) {
        this.year = year;
        this.color = color;
    }

    public Car(int year, String color, String manufacturer) {
        this.year = year;
        this.color = color;
        this.manufacturer = manufacturer;
    }

    public Car(int year, String color, String manufacturer, String manufacturingCountry) {
        this.year = year;
        this.color = color;
        this.manufacturer = manufacturer;
        this.manufacturingCountry = manufacturingCountry;
    }

    public void drive(String direction) {
        System.out.println("I am driving to " + direction);
    }

    @Deprecated(since = "Cars are not broken anymore")
    public void broke(){}

    @Override
    public String toString() {
        return "Car{" +
                "year=" + year +
                ", color='" + color + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", manufacturingCountry='" + manufacturingCountry + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Car car = (Car) object;
        return year == car.year && Objects.equals(color, car.color) && Objects.equals(manufacturer, car.manufacturer) && Objects.equals(manufacturingCountry, car.manufacturingCountry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, color, manufacturer, manufacturingCountry);
    }
}
