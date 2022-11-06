package thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ThreadMain {
    public static void main(String[] args) {
        int count = 10;
        // ??why Can't use ArrayList in place of List
        // if I am keeping two lists, do I really need synchronized
        List<Integer> square = Collections.synchronizedList(new ArrayList<>());
        List<Integer> cube = Collections.synchronizedList(new ArrayList<>());
        Thread t1 = new Thread(new GeneratorThread(count, square, cube));
        Thread t2 = new Thread(new SquareThread(square));
        Thread t3 = new Thread(new CubeThread(cube));
        // ??why do we need setDaemon
        t2.setDaemon(true);
        t2.start();
        t3.setDaemon(true);
        t3.start();
        t1.start();
    }

//? diff from extends Threads
    public static class SquareThread implements Runnable {
        // ?? do we need to make it final
        private final List<Integer> square;

        public SquareThread(List<Integer> square) {
            this.square = square;
        }

// ?? what does override do
        @Override
        public void run() {
            while (true) { // will it not take infinite CPU time
                if (square.size() > 0) {
                    // is the get call blocking
                    int number = square.get(0);
                    // Can't I cast it as (Integer)
                    square.remove((Object) number);
                    System.out.println("Square " + number * number);
                }
            }
        }
    }

    public static class CubeThread implements Runnable {
        private final List<Integer> cube;

        public CubeThread(List<Integer> cube) {
            this.cube = cube;
        }

        @Override
        public void run() {
            while (true) {
                if (cube.size() > 0) {
                    int number = cube.get(0);
                    cube.remove((Object) number);
                    System.out.println("Cube " + number * number * number);
                }
            }
        }
    }

    public static class GeneratorThread implements Runnable {
        private final int count;
        private final List<Integer> square;
        private final List<Integer> cube;
        private final Random RANDOM = new Random();

        public GeneratorThread(int count, List<Integer> square, List<Integer> cube) {
            this.count = count;
            this.square = square;
            this.cube = cube;
        }

        @Override
        public void run() {
            try {
                for (int x = 0; x < count; x++) {
                    int random = RANDOM.nextInt(10);
                    System.out.println("Generated:" + random);
                    square.add(random);
                    cube.add(random);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
