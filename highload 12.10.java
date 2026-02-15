import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class HighLoadFixedPool {

    public static void main(String[] args) throws InterruptedException {

        int totalRequests = 100;

        // Fixed Thread Pool of size 10
        ExecutorService executor = Executors.newFixedThreadPool(10);

        long startTime = System.currentTimeMillis();

        for (int i = 1; i <= totalRequests; i++) {
            final int requestId = i;

            executor.submit(() -> {
                System.out.println("Processing Request " + requestId +
                        " by " + Thread.currentThread().getName());
                try {
                    Thread.sleep(100);  // Simulate processing time
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);

        long endTime = System.currentTimeMillis();

        System.out.println("Total Time (Fixed Pool): " +
                (endTime - startTime) + " ms");
    }
}











