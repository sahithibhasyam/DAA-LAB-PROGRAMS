/*
 * Write a Java program that implements a multi-thread application
 * that has three threads. First thread generates a random integer for every 1 second;
 * second thread computes the square of the number and prints;
 * third thread will print the value of cube of the number.
 * <p>
 * Two Command line inputs i.e. args[0] and args[1]
 * param1 - count i.e. number of values to be generated
 * param2 - max value to be generated randomly
 */

/*
 * Two Command line inputs i.e. args[0] and args[1]
 * param1 - count i.e. number of values to be generated
 * param2 - max value to be generated randomly
 */
package thread;


import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadSquareCube {
  public static void main(String[] args) {
    if(args.length < 2) {
      System.out.println("Two args required");
      System.exit(-1);
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
    BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(count);
    Thread t1 = new Thread(new GeneratorThread(count, maxvalue, queue));
    Thread t2 = new Thread(new PowerThread(count, queue, 2));
    Thread t3 = new Thread(new PowerThread(count, queue, 3));
    t2.setDaemon(true);
    t2.start();
    t3.setDaemon(true);
    t3.start();
    t1.start();
  }

  public static class PowerThread implements Runnable {
    private final BlockingQueue<Integer> queue;
    private final int expon;
    private final int count;

    public PowerThread(int count, BlockingQueue<Integer> queue, int expon) {
      this.queue = queue;
      this.expon = expon;
      this.count = count;
    }

    @Override
    public void run() {
      try {
        for (int i = 0; i < count; i++) {
          synchronized (queue) {
            while (queue.size() <= i) {
              queue.wait();
            }
            int number = queue.take(); // this is blocking queue
            int term = (int) Math.pow(number, expon);
            System.out.println(number + "^" + expon + "=" + term);
          }
        }
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }

  public static class GeneratorThread implements Runnable {
    private final int count;
    private final int maxvalue;
    private final BlockingQueue<Integer> queue;
    private final Random random = new Random();

    public GeneratorThread(int count, int maxvalue, BlockingQueue<Integer> queue) {
      this.queue = queue;
      this.count = count;
      this.maxvalue = maxvalue;
    }

    @Override
    public void run() {
      try {
        for (int x = 0; x < count; x++) {
          Thread.sleep(1000);
          synchronized (queue) {
            int rndval = random.nextInt(maxvalue);
            System.out.println("Generated:" + rndval);
            queue.put(rndval);
            queue.notifyAll();
            queue.put(rndval);
          }
        }
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }//try catch
    }// run
    }//Generator thread
}//ThreadSquareCube

