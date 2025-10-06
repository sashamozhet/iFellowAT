package ru.ifellow.alivenskiy.hw2;

public abstract class Car {
    private String mark;
    private String model;
    private int year;
    private String transmission;
    private String fuelType;
    private double engineVolume;
    private String color;
    private int horsePower;

    public Car(String mark,
               String model,
               int year,
               String transmission,
               String fuelType,
               double engineVolume,
               String color, int horsePower) {
        this.mark = mark;
        this.model = model;
        this.year = year;
        this.transmission = transmission;
        this.fuelType = fuelType;
        this.engineVolume = engineVolume;
        this.color = color;
        this.horsePower = horsePower;
    }
    public String getMark(){
        return mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public double getEngineVolume() {
        return engineVolume;
    }

    public void setEngineVolume(double engineVolume) {
        this.engineVolume = engineVolume;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }
    public abstract void startEngine();
    public abstract void displaySlogan();
    public void displayInfo(){
        System.out.println("Марка авто: " + mark);
        System.out.println("модель авто: " + model);
        System.out.println("Год выпуска авто: " + year );
        System.out.println("Коробка передач: " + transmission);
        System.out.println("Тип двигателя: " + fuelType);
        System.out.println("Объём двигателя: " + engineVolume);
        System.out.println("Цвет авто: " + color);
        System.out.println("Количество лошадиных сил: " + horsePower);
    }
}
