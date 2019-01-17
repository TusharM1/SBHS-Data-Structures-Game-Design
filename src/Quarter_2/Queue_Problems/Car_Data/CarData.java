package Quarter_2.Queue_Problems.Car_Data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;

public class CarData {

    public static void main(String[] args) {

        Stack<Car> stack = new Stack<>();
        LinkedList<Car> linkedList = new LinkedList<>();
        PriorityQueue<Car> priorityQueue = new PriorityQueue<>();

        File name = new File("src/Quarter_2/Queue_Problems/Car_Data/CarData.txt");
        try {
            BufferedReader input = new BufferedReader(new FileReader(name));
            String text;
            String title = input.readLine();
            while ((text = input.readLine()) != null) {
                Car car = new Car(text);
                stack.push(car);
                linkedList.add(car);
                priorityQueue.add(car);
            }
            System.out.println(title);
            for (int i = 0; i < stack.size(); )
                System.out.println(stack.pop());
            System.out.println("\n" + title);
            for (int i = 0; i < linkedList.size(); )
                System.out.println(linkedList.pop());
            System.out.println("\n" + title);
            for (int i = 0; i < priorityQueue.size(); )
                System.out.println(priorityQueue.poll());
        } catch (IOException io) {
            System.err.println("File does not exist");
        }

    }

}

class Car implements Comparable {

    int carID, milesPerGallon, engineSize, horsePower, weight, acceleration, countryOfOrigin, numberOfCylinders;

    public Car(String s) {
        String[] args = s.split("[\\s]");
        this.carID = Integer.parseInt(args[0]);
        this.milesPerGallon = Integer.parseInt(args[1]);
        this.engineSize = Integer.parseInt(args[2]);
        this.horsePower = Integer.parseInt(args[3]);
        this.weight = Integer.parseInt(args[4]);
        this.acceleration = Integer.parseInt(args[5]);
        this.countryOfOrigin = Integer.parseInt(args[6]);
        this.numberOfCylinders = Integer.parseInt(args[7]);
    }

    public int getCarID() {
        return carID;
    }

    public int getEngineSize() {
        return engineSize;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public int getWeight() {
        return weight;
    }

    public int getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public int getNumberOfCylinders() {
        return numberOfCylinders;
    }

    public int getAcceleration() {
        return acceleration;
    }

    public Integer getMilesPerGallon() {
        return milesPerGallon;
    }

    @Override
    public int compareTo(Object o) {
        int result = ((Integer) (this.acceleration)).compareTo(((Car) o).getAcceleration());
        if (result != 0)
            return -result;
        result = ((Integer) (this.milesPerGallon)).compareTo(((Car) o).getMilesPerGallon());
        if (result != 0)
            return -result;
        result = ((Integer) (this.horsePower)).compareTo(((Car) o).getHorsePower());
        if (result != 0)
            return -result;
        result = ((Integer) (this.engineSize)).compareTo(((Car) o).getEngineSize());
        if (result != 0)
            return -result;
        result = ((Integer) (this.weight)).compareTo(((Car) o).getWeight());
        if (result != 0)
            return -result;
        result = ((Integer) (this.numberOfCylinders)).compareTo(((Car) o).getNumberOfCylinders());
        if (result != 0)
            return -result;
        result = ((Integer) (this.carID)).compareTo(((Car) o).getCarID());
        if (result != 0)
            return -result;
        return 0;
    }

    @Override
    public String toString() {
        return String.format("%d\t%d\t%d\t%d\t%d\t%d\t%d\t%d", carID, milesPerGallon, engineSize, horsePower, weight, acceleration, countryOfOrigin, numberOfCylinders);
    }
}
