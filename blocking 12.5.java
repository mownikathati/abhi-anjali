import java.util.concurrent.*;

class Order {
    private int orderId;

    public Order(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderId() {
        return orderId;
    }
}

// Producer Task
class OrderProducer implements Runnable {
    private BlockingQueue<Order> queue;

    public OrderProducer(BlockingQueue<Order> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                Order order = new Order(i);
                queue.put(order);   // Blocks if queue is full
                System.out.println("Order Produced: " + i);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

// Consumer Task
class OrderConsumer implements Runnable {
    private BlockingQueue<Order> queue;

    public OrderConsumer(BlockingQueue<Order> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Order order = queue.take();  // Blocks if queue is empty
                System.out.println("Processing Order: " + order.getOrderId());
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class ProducerConsumerExample {
    public static void main(String[] args) {

        BlockingQueue<Order> queue = new ArrayBlockingQueue<>(5);

        // Producer Thread
        Thread producerThread = new Thread(new OrderProducer(queue));

        // Consumer using Executor
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(new OrderConsumer(queue));

        producerThread.start();

        // Shutdown executor after some time (Demo purpose)
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdownNow();
        System.out.println("System Shutdown");
    }
}










