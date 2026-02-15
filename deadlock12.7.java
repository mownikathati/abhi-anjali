class OrderService {

    private final Object inventoryLock = new Object();
    private final Object paymentLock = new Object();

    public void processOrder() {

        // Step 1: Lock Inventory FIRST (consistent order)
        synchronized (inventoryLock) {

            System.out.println(Thread.currentThread().getName() 
                + " locked Inventory");

            // Step 2: Lock Payment SECOND
            synchronized (paymentLock) {

                System.out.println(Thread.currentThread().getName() 
                    + " locked Payment");

                // Business Logic
                System.out.println("Processing Order...");
            }
        }
    }
}

public class DeadlockPreventionDemo {
    public static void main(String[] args) {

        OrderService service = new OrderService();

        Runnable task = () -> service.processOrder();

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");

        t1.start();
        t2.start();
    }
}










