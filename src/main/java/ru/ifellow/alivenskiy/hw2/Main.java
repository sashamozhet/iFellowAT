package ru.ifellow.alivenskiy.hw2;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Car> garage = new ArrayList<>();
        garage.add(new Audi("Audi","A7", 2025, "Automatic", "Gasoline", 3.0, "Red", 201));
        garage.add(new Audi("Audi","Q7", 2004, "Mechanic", "Diesel", 4.0, "Orange", 382));
        garage.add(new BMW("BMW","X5", 2023, "Automatic", "Diesel", 3.0, "Red", 335));
        garage.add(new BMW("hw2.BMW","3 Series", 2024, "Automatic", "Hybrid", 2.0, "Gray", 188));
        garage.add(new Hyundai("Hyundai","Elantra", 2015, "Automatic", "Gasoline", 1.6, "Brown", 132));
        garage.add(new Hyundai("Hyundai","Tucson", 2002, "Automatic", "Hybrid", 1.8, "Red", 175));
        garage.add(new Mercedes("Mercedes","E-Class", 2005, "Automatic", "Gasoline", 2.0, "Silver", 255));
        garage.add(new Mercedes("Mercedes","GLC", 2004, "Automatic", "Diesel", 2.0, "Black", 194));
        garage.add(new Nissan("Nissan","Qashqai", 2003, "Mechanic", "Gasoline", 1.3, "Red", 160));
        garage.add(new Nissan("issan","X-Trail", 2004, "Mechanic", "Hybrid", 1.5, "Green", 121));
        garage.add(new Toyota("oyota","Corolla", 2011, "Automatic", "Gasoline", 2.5, "Black", 126));
        garage.add(new Toyota("Toyota","Land Cruiser", 2008, "Automatic", "Diesel", 2.4, "Grey", 205));

        System.out.println("Количество машин в списке - " + garage.size()
                + ". Вывод всего списка машин\n");
        for(Car car: garage){
            car.startEngine();
            car.displaySlogan();
            car.displayInfo();
            System.out.println("---------");
        }
        CarsByYear(garage);
        ChangeRedColor(garage);

        System.out.println(" \nВывод всего списка машин после изменения цвета:\n");
        for(Car car: garage){
            car.displayInfo();
            System.out.println("---------");
        }
        findAutomaticTransmission(garage);


    }

    public static void CarsByYear(List<Car> cars){
        System.out.println("\nИнформация по году выпуска: \n");
        for(Car car: cars){
            if(car.getYear() > 2006){
                System.out.println(car.getMark()+" " +car.getModel()+" " + car.getYear() +
                        " года - свежий авто, выпущенный после 2006" );
            }
            else{
                System.out.println(car.getMark()+" "+car.getModel()+" " + car.getYear() +
                        " года - устаревший авто" );
            }
        }
    }

    public static void ChangeRedColor(List<Car> cars){
        System.out.println("\nЗамена цвета красных машин: \n");
        for(Car car: cars){
            if(car.getColor().equals("Red")){
                System.out.println("Машина " + car.getMark()+" " +car.getModel()+" " + car.getYear()
                        + " Красного цвета. Меняем на зелёный" );
                car.setColor("Green");
            }
            else{
                System.out.println("Машина " + car.getMark()+" " +car.getModel()+" " + car.getYear()
                        + " НЕ красного цвета. Цвет НЕ будем менять" );
            }
        }
    }
    public static void findAutomaticTransmission(List<Car> cars){
        System.out.println("\nИщем машины с автоматической коробкой передач: \n");
        for(Car car: cars){
            if(car.getTransmission().equals("Automatic")){
                System.out.println("Машина " + car.getMark()+" " +car.getModel()+" " + car.getYear()
                        + " Имеет автоматическую коробку передач" );
            }
            else{
                System.out.println("Машина " + car.getMark()+" " +car.getModel()+" " + car.getYear()
                        + " имеет механическую коробку передач" );
            }
        }
    }

}
