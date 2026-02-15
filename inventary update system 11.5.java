class Inventory {

    private int stock = 100;

    public void updateStock(int quantity) {

        // Only stock update is synchronized
        synchronized (this) {
            if (stock + quantity >= 0) {
                stock += quantity;
                System.out.println(Thread.currentThread().getName() +
                        " updated stock. New stock: " + stock);
            } else {
                System.out.println("Not enough stock!");
            }
        }

        // Logging (Not synchronized for better performance)
        System.out.println(Thread.currentThread().getName() +
                " logging update completed.");
    }
}

class InventoryTest {
    public static void main(String[] args) {

        Inventory inventory = new Inventory();

        Thread t1 = new Thread(() -> inventory.updateStock(-30), "User-1");
        Thread t2 = new Thread(() -> inventory.updateStock(-50), "User-2");
        Thread t3 = new Thread(() -> inventory.updateStock(20), "User-3");

        t1.start();
        t2.start();
        t3.start();
    }
}


