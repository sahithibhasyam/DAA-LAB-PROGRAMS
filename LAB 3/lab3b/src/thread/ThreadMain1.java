
/**
 * Write a Java program that implements a multi-thread 
 * application that has three threads. First thread generates
 * a random integer for every 1 second; second thread computes 
 * the square of the number andprints; third thread will print 
 * the value of cube of the number.
 */

package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ThreadMain1 {
	
    public static void main(String[] args) {
        if(args.length < 2) {
            throw new IllegalArgumentException("two args required");
        }
        try {
            Integer.parseInt(args[0]);
            Integer.parseInt(args[1]);
        } catch(NumberFormatException e) {
            System.out.println("Invalid Input.Try Again.");
            System.exit(-1);
        }
        int count = Integer.parseInt(args[0]);
        int maxvalue = Integer.parseInt(args[1]);
        List<Integer> square = new ArrayList<>();
        Thread t1 = new Thread(new GeneratorThread(count, square, maxvalue));
        Thread t2 = new Thread(new PowerThread(count, 2, square));
        Thread t3 = new Thread(new PowerThread(count, 3, square));
        t2.start();
        t3.start();
        t1.start();
    }
	
    public static class PowerThread implements Runnable {
        private final int count;
        private final int power;
        private final List<Integer> square;
		
        public PowerThread(int count, int power, List<Integer> square) {
            this.count = count;
            this.power = power;
            this.square = square;
        }
		
        @Override
        public void run() {
            try {
                for (int x = 0; x < count; x++) {
                    synchronized (square) {
                        while (square.size() <= x) {
                            square.wait();
                        }
                        int number = square.get(x);
                        System.out.println(number + " ^ " + power + " = " + (int) Math.pow(number, power));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
	
    public static class GeneratorThread implements Runnable {
        private final int count;
        private final int maxvalue;
        private final List<Integer> square;
        private final Random RANDOM = new Random();
		
        public GeneratorThread(int count, List<Integer> square, int maxvalue) {
            this.count = count;
            this.square = square;
            this.maxvalue = maxvalue;
        }
		
        @Override
        public void run() {
            try {
                for (int x = 0; x < count; x++) {
                    Thread.sleep(1000);
                    synchronized (square) {
                        int random = RANDOM.nextInt(maxvalue);
                        System.out.println("Generated:" + random);
                        square.add(random);
                        square.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
