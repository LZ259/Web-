package EggCake;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class EggCakeSimulation {

    private static final int MAX_UNSOLD_CAKES = 3;
    private static final int MAX_DAILY_SALES = 10;
    private static int soldCakes = 0;
    private static BlockingQueue<Object> cakeQueue = new LinkedBlockingQueue<>(MAX_UNSOLD_CAKES);
    private static final Random random = new Random();
    private static final Object lock = new Object();

    public static void main(String[] args) {
        Thread producerThread = new Thread(new CakeProducer());
        Thread consumerThread = new Thread(new CakeConsumer());

        producerThread.start();
        consumerThread.start();

        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Day is over, closing the stall.");
    }

    static class CakeProducer implements Runnable {
        @Override
        public void run() {
            while (soldCakes < MAX_DAILY_SALES) {
                synchronized (lock) {
                    try {
                        while (cakeQueue.size() == MAX_UNSOLD_CAKES && soldCakes < MAX_DAILY_SALES) {
                            lock.wait();
                        }

                        if (soldCakes >= MAX_DAILY_SALES) {
                            break;
                        }

                        System.out.println("Making an egg cake...");
                        Thread.sleep(3000); // Simulate 3 seconds to make one egg cake
                        cakeQueue.put(new Object()); // Put a new cake in the queue
                        System.out.println("One egg cake made. Current queue size: " + cakeQueue.size());

                        lock.notifyAll(); // Notify consumers that a new cake is available
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class CakeConsumer implements Runnable {
        @Override
        public void run() {
            while (soldCakes < MAX_DAILY_SALES) {
                synchronized (lock) {
                    try {
                        while (cakeQueue.isEmpty() && soldCakes < MAX_DAILY_SALES) {
                            lock.wait();
                        }

                        if (soldCakes >= MAX_DAILY_SALES) {
                            break;
                        }

                        int waitTime = random.nextInt(5000) + 1000; // Random wait time between 1 and 5 seconds
                        System.out.println("Customer is coming to buy a cake. Waiting for " + waitTime + " ms.");
                        Thread.sleep(waitTime);

                        cakeQueue.take(); // Take a cake from the queue
                        soldCakes++;
                        System.out.println("One egg cake sold. Total sold: " + soldCakes);

                        lock.notifyAll(); // Notify producer that a cake has been sold
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

