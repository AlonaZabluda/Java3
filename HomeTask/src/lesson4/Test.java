package lesson4;


public class Test {
    private char letter = 'A';

    public void printA() throws InterruptedException {
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                while (letter != 'A')
                    wait();
                System.out.println("A");
                letter = 'B';
                notifyAll();
            }
        }
    }


    public void printB() throws InterruptedException {
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                while (letter != 'B')
                    wait();
                System.out.println("B");
                letter = 'C';
                notifyAll();
            }
        }
    }

    public void printC() throws InterruptedException {
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                while (letter != 'C')
                    wait();
                System.out.println("C");
                letter = 'A';
                notifyAll();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Test test = new Test();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    test.printA();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    test.printB();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    test.printC();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

}

