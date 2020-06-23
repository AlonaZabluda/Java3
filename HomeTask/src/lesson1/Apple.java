package lesson1;

public class Apple extends Fruit{
    private float weight = 1.0f;

    public Apple(String name) {
        super(name);
    }

    @Override
    public float getWeight() {
        return weight;
    }

}