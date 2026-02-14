class Account {

    private int balance = 5000;   // Shared resource

    // Synchronized method
    public synchronized void withdraw(String customerName, int amount) {

        System.out.println(customerName + " is trying to withdraw ₹" + amount);

        if (balance >= amount) {
            System.out.println("Processing withdrawal for " + customerName);

            try {
                Thread.sleep(1000);  // Simulate delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            balance -= amount;
            System.out.println(customerName + " completed withdrawal. Remaining Balance: ₹" + balance);
        } else {
            System.out.println("Insufficient balance for " + customerName);
        }
    }
}

// Customer Thread
class Customer extends Thread {

    Account account;
    String name;
    int amount;

    Customer(Account account, String name, int amount) {
        this.account = account;
        this.name = name;
        this.amount = amount;
    }

    public void run() {
        account.withdraw(name, amount);
    }
}

// Main Class
public class BankSystem {

    public static void main(String[] args) {

        Account sharedAccount = new Account();  // Shared object

        Customer c1 = new Customer(sharedAccount, "Anjali", 3000);
        Customer c2 = new Customer(sharedAccount, "Rahul", 3000);
        Customer c3 = new Customer(sharedAccount, "Priya", 2000);

        c1.start();
        c2.start();
        c3.start();
    }
}

