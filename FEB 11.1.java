import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ApiRequestTask implements Runnable {
    private int requestId;

    public ApiRequestTask(int requestId) {
        this.requestId = requestId;
    }

    @Override
    public void run() {
        System.out.println("Request " + requestId + 
                " is handled by " + Thread.currentThread().getName());

        try {
            // Simulate API processing time
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ApiRequestProcessingSystem {
    public static void main(String[] args) {

        // Create Fixed Thread Pool with 5 threads
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // Submit 20 API tasks
        for (int i = 1; i <= 20; i++) {
            executor.submit(new ApiRequestTask(i));
        }

        // Shutdown executor
        executor.shutdown();
    }
}


