class BankAccount {
    private int balance;
    private String name;

    public BankAccount(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }

    public void transfer(BankAccount target, int amount) {
        synchronized (this) { // Lock source account
            System.out.println(Thread.currentThread().getName() +
                    " locked " + this.name);

            try { Thread.sleep(100); } catch (Exception e) {}

            synchronized (target) { // Lock target account
                System.out.println(Thread.currentThread().getName() +
                        " locked " + target.name);

                this.balance -= amount;
                target.balance += amount;
            }
        }
    }
}

public class DeadlockDemo {
    public static void main(String[] args) {

        BankAccount accA = new BankAccount("AccountA", 1000);
        BankAccount accB = new BankAccount("AccountB", 1000);

        Thread t1 = new Thread(() -> {
            accA.transfer(accB, 100);
        }, "Thread-1");

        Thread t2 = new Thread(() -> {
            accB.transfer(accA, 200);
        }, "Thread-2");

        t1.start();
        t2.start();
    }
}






