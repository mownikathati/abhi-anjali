class Order implements Runnable {

    private int orderId;

    public Order(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + 
                " -> Order " + orderId + " Validation Started");
        validateOrder();

        System.out.println(Thread.currentThread().getName() + 
                " -> Order " + orderId + " Payment Processing");
        processPayment();

        System.out.println(Thread.currentThread().getName() + 
                " -> Order " + orderId + " Completed ✅\n");
    }

    private void validateOrder() {
        try {
            Thread.sleep(1000); // Simulate validation delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void processPayment() {
        try {
            Thread.sleep(1500); // Simulate payment delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class OrderProcessingSystem {
    public static void main(String[] args) {

        for (int i = 1; i <= 5; i++) {
            Order order = new Order(i);
            Thread t = new Thread(order);
            t.setName("Order-Thread-" + i);   // Thread Naming
            t.start();                        // Start thread
        }
    }
}



