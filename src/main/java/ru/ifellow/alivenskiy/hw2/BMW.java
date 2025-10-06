package ru.ifellow.alivenskiy.hw2;

public class BMW  extends  Car{
    public BMW(String mark,
               String model,
                   int year,
                   String transmission,
                   String fuelType,
                   double engineVolume,
                   String color,
                   int horsePower) {
        super(mark,model, year, transmission, fuelType, engineVolume, color, horsePower);
    }
    public void startEngine() {
        System.out.println("Запуск двигателя ru.ifellow.alivenskiy.BMW " + getModel());
    }

    public void displaySlogan() {
        System.out.println("Лозунг компании BMW: C удовольствием за рулём");
    }
}
