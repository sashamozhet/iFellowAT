public class Mercedes extends  Car{
    public Mercedes(String mark,
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
        System.out.println("Запуск двигателя Mercedes " + getModel());
    }

    public void displaySlogan() {
        System.out.println("Лозунг компании Mercedes: Лучшее или ничего");
    }
}
