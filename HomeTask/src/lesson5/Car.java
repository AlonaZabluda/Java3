package lesson5;

import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private Race race;
    private int speed;
    private String name;
    private static CyclicBarrier cb;
    private static boolean winner;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public static void setCyclicBarrier(CyclicBarrier cb) {
        Car.cb = cb;
    }

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            cb.await();
            for (int i = 0; i < race.getStages().size(); i++)
                race.getStages().get(i).go(this);
            findWinner(this);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private static synchronized void findWinner(Car c) {
        if (!winner) {
            System.out.println(c.name + " - Победитель!!!");
            winner = true;
        }
    }
}
