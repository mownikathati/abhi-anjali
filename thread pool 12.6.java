import java.util.concurrent.*;

public class CustomThreadPoolDemo {

    public static void main(String[] args) {

        // Custom ThreadPoolExecutor
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                3,                      // Core pool size
                6,                      // Max pool size
                60,                     // Keep alive time
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),  // Queue capacity
                new ThreadPoolExecutor.AbortPolicy() // Rejection policy
        );

        // Submit 30 tasks
        for (int i = 1; i <= 30; i++) {
            int taskId = i;

            executor.execute(() -> {
                System.out.println("Task " + taskId +
                        " handled by " + Thread.currentThread().getName());
                try {
                    Thread.sleep(2000); // Simulate work
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
    }
}










