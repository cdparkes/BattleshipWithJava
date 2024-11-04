import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int speed = scanner.nextInt();
        scanner.close();

        Vehicle car = new Car(speed, 4);
        Vehicle motorcycle = new Motorcycle(speed, false);

        System.out.println(car.getInfo());
        System.out.println(motorcycle.getInfo());
    }
}

class Vehicle {
    protected int speed;

    public Vehicle(int speed) {
        this.speed = speed;
    }

    public String getInfo() {
        return "Vehicle: Speed " + speed + " mph";
    }

}

class Car extends Vehicle {
    private int numberOfDoors;

    public Car(int speed, int numberOfDoors) {
        super(speed);
        this.numberOfDoors = numberOfDoors;
    }

    @Override
    public String getInfo() {
        return "Car: Speed " + speed + " mph, Doors: " + numberOfDoors;
    }
}

class Motorcycle extends Vehicle {
    private boolean hasSideCar;

    public Motorcycle(int speed, boolean hasSideCar) {
        super(speed);
        this.hasSideCar = hasSideCar;
    }

    @Override
    public String getInfo() {
        return "Motorcycle: Speed " + speed + " mph, Sidecar: " + hasSideCar;
    }
}