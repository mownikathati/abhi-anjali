import java.util.Random;

class PaymentTask implements Runnable {

    private String paymentId;

    public PaymentTask(String paymentId) {
        this.paymentId = paymentId;
    }

    @Override
    public void run() {
        try {
            Random random = new Random();
            int delay = random.nextInt(3) + 1; // 1 to 3 seconds
            
            System.out.println(paymentId + " verification started...");
            
            Thread.sleep(delay * 1000);  // sleep in milliseconds
            
            System.out.println(paymentId + " completed in " + delay + " seconds.");
            
        } catch (InterruptedException e) {
            System.out.println(paymentId + " interrupted.");
        }
    }
}

public class PaymentGatewaySimulation {
    public static void main(String[] args) {

        Thread p1 = new Thread(new PaymentTask("Payment-1"));
        Thread p2 = new Thread(new PaymentTask("Payment-2"));
        Thread p3 = new Thread(new PaymentTask("Payment-3"));

        p1.start();
        p2.start();
        p3.start();
    }
}




