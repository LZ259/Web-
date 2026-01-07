import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class EggCakeSimulation {

    private static final int MAX_UNSOLD_CAKES = 3;//队列中可存放的未销售蛋饼数量
    private static final int MAX_DAILY_SALES = 10;//一天内最多可销售的蛋饼数量
    private static int soldCakes = 0;//已销售的蛋饼数量
    private static BlockingQueue<Object> cakeQueue = new LinkedBlockingQueue<>(MAX_UNSOLD_CAKES);//cakeQueue：一个 BlockingQueue，用于存放制作好的蛋饼。这里使用 LinkedBlockingQueue，它是线程安全的，并且支持阻塞操作。
    private static final Random random = new Random();
    private static final Object lock = new Object();//一个对象锁，用于同步 CakeProducer 和 Cakeconsumer 之间的操作

    public static void main(String[] args) {
        Thread producerThread = new Thread(new CakeProducer());//创建并启动 CakeProducer 线程。
        Thread consumerThread = new Thread(new CakeConsumer());//创建并启动 CakeConsumer 线程。

        producerThread.start();
        consumerThread.start();

        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }//让主线程（main 方法所在的线程）等待对应的子线程执行完毕（实际上，由于使用了无限循环，这里不会自然结束，但在实际测试中，可以通过其他方式中断线程）。
         //join 方法能确保主线程不会过早结束而导致程序异常退出或者无法正确统计最终结果等情况。
        System.out.println("已售罄，打烊收摊");
    }

    static class CakeProducer implements Runnable {//CakeProducer内部类
        @Override
        public void run() {//run方法
            while (soldCakes < MAX_DAILY_SALES) {//使用一个 while 循环来不断检查是否可以制作蛋饼。
                synchronized (lock) {
                    try {
                        while (cakeQueue.size() == MAX_UNSOLD_CAKES && soldCakes < MAX_DAILY_SALES) {//首先检查队列是否已满且没有达到销售上限。
                            lock.wait();//调用 lock.wait() 来等待消费者线程消费蛋饼。
                        }

                        if (soldCakes >= MAX_DAILY_SALES) {
                            break;
                        }

                        System.out.println("正在制作蛋饼...");
                        Thread.sleep(3000); //制作一个蛋饼需要 3秒
                        cakeQueue.put(new Object()); // 制作完成，将蛋饼放入队列并通知消费者
                        System.out.println("一个蛋饼制作完成，现在队列中的蛋饼数量为" + cakeQueue.size());

                        lock.notifyAll(); // 通知可能正在等待的消费者线程有新的蛋饼可以购买
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class CakeConsumer implements Runnable {//CakeConsumer内部类
        @Override
        public void run() {//run方法
            while (soldCakes < MAX_DAILY_SALES) {//检查是否可以购买蛋饼
                synchronized (lock) {
                    try {
                        while (cakeQueue.isEmpty() && soldCakes < MAX_DAILY_SALES) {//检查队列是否为空且没有达到销售上限
                            lock.wait();//等待生产者线程制作新的蛋饼
                        }

                        if (soldCakes >= MAX_DAILY_SALES) {//队列不为空或已经达到销售上限
                            break;
                        }

                        int waitTime = random.nextInt(1000)+1000 ; // 模拟一个顾客到来的过程。表示顾客可能在1到6秒之间的任意时间到达。
                        System.out.println("顾客正在来买一个蛋饼. 等待时间为 " + waitTime + " ms.");
                        Thread.sleep(waitTime);

                        cakeQueue.take(); // 顾客到达后，从队列中取出一个蛋饼（表示购买成功）
                        soldCakes++;//增加已销售的蛋饼数量
                        System.out.println("已售一个蛋饼. 总共销售量: " + soldCakes);

                        lock.notifyAll(); //通知可能正在等待的生产者线程有新的销售记录
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
