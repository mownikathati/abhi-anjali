import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Bank Account class
class BankAccount {

    private int balance = 1000;

    // synchronized method to avoid race condition
    public synchronized void withdraw(String user, int amount) {

        System.out.println(user + " trying to withdraw ₹" + amount);

        if (balance >= amount) {
            System.out.println(user + " processing withdrawal...");
            balance -= amount;

            System.out.println(user + " completed withdrawal. Remaining balance: ₹" + balance);
        } else {
            System.out.println(user + " failed. Insufficient balance. Current balance: ₹" + balance);
        }

        System.out.println("----------------------------------");
    }

    public synchronized void deposit(String user, int amount) {

        System.out.println(user + " depositing ₹" + amount);
        balance += amount;

        System.out.println(user + " deposit successful. New balance: ₹" + balance);
        System.out.println("----------------------------------");
    }
}

// ATM Task
class ATMTask implements Runnable {

    private BankAccount account;
    private String userName;

    public ATMTask(BankAccount account, String userName) {
        this.account = account;
        this.userName = userName;
    }

    @Override
    public void run() {

        // Simulate different operations
        account.withdraw(userName, 300);
        account.deposit(userName, 200);
    }
}

// Main class
public class ATMSimulation {

    public static void main(String[] args) {

        BankAccount account = new BankAccount();

        // Fixed Thread Pool of 5 users
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // 5 users accessing ATM
        for (int i = 1; i <= 5; i++) {
            executor.submit(new ATMTask(account, "User-" + i));
        }

        executor.shutdown();
    }
}










