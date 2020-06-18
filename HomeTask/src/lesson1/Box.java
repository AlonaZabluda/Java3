package lesson1;

import java.util.ArrayList;

public class Box<T extends Fruit> {
    private ArrayList<T> fruits;
    private float weightOfBox;


    public Box() {
        this.fruits = new ArrayList<>();
    }


    public ArrayList<T> addFruits(T... obj) {
        for (T o : obj) {
            fruits.add(o);
        }
        return fruits;
    }


    public float getWeightOfBox() throws Exception {
        if (fruits.size() != 0) {
            weightOfBox = fruits.size() * fruits.get(0).getWeight();
        } else {
            throw new Exception("Array is empty");
        }
        return weightOfBox;
    }


    public boolean compare(Box<?> otherBox) throws Exception {
        return getWeightOfBox() == otherBox.getWeightOfBox();
    }


    public void transferFruits(Box<T> otherBox) {
        otherBox.fruits.addAll(fruits);
        fruits.clear();
    }


    public static void main(String[] args) throws Exception {
        Apple apple1 = new Apple("Green apple");
        Apple apple2 = new Apple("Red apple");
        Apple apple3 = new Apple("Yellow apple");

        Orange orange1 = new Orange("Sweet orange");
        Orange orange2 = new Orange("Acidic orange");
        Orange orange3 = new Orange("Sicilian orange");


        System.out.println("Box with apple(s):");
        Box<Apple> appleBox = new Box<>();
        appleBox.addFruits(apple1, apple2, apple3);
        for (Apple t : appleBox.fruits) {
            System.out.println(t.getName());
        }

        System.out.println("\nBox with orange(s):");
        Box<Orange> orangeBox = new Box<>();
        orangeBox.addFruits(orange1, orange3);
        for (Orange t : orangeBox.fruits) {
            System.out.println(t.getName());
        }

        System.out.println("\nCompare the weight of two boxes:");
        System.out.println(orangeBox.compare(appleBox));


        System.out.println("\nPut fruits from one box to another:");
        Box<Apple> newAppleBox = new Box<>();
        appleBox.transferFruits(newAppleBox);
        for (Apple t : newAppleBox.fruits) {
            System.out.println(t.getName());
        }

    }
}


