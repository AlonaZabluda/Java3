package lesson1;

public class Orange extends Fruit{
    private float weight = 1.5f;

    public Orange(String name) {
        super(name);
    }

    @Override
    public float getWeight() {
        return weight;
    }
}