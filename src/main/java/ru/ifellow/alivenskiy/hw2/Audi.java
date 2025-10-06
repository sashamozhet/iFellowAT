package ru.ifellow.alivenskiy.hw2;

public class Audi extends Car{
    public Audi(String mark,
                String model,
                   int year,
                   String transmission,
                   String fuelType,
                   double engineVolume,
                   String color,
                   int horsePower) {
        super(mark, model, year, transmission, fuelType, engineVolume, color, horsePower);
    }
    public void startEngine() {
        System.out.println("Запуск двигателя " + getMark());
    }

    public void displaySlogan() {
        System.out.println("Лозунг компании Audi: Превосходство высоких технологий");
    }
}
