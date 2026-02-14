class EmailService implements Runnable {

    private String customerEmail;

    public EmailService(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    @Override
    public void run() {
        try {
            System.out.println("Email sending started for: " + customerEmail);
            Thread.sleep(3000); // Simulate email sending delay
            System.out.println("Email sent successfully to: " + customerEmail);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class OrderSystem {

    public static void main(String[] args) {

        System.out.println("Order processing started...");

        // Simulate order processing
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Order placed successfully!");

        // Start email in background thread
        EmailService emailTask = new EmailService("customer@gmail.com");
        Thread emailThread = new Thread(emailTask);
        emailThread.start();

        // Confirmation prints immediately
        System.out.println("Order confirmation displayed to user immediately.");
    }
}






