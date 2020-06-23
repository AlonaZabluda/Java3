package lesson1;

public abstract class Fruit {
    private float weight;
    private String name;

    public Fruit(String name) {
        this.name = name;
    }

    public abstract float getWeight();

    public String getName() {
        return name;
    }
}
