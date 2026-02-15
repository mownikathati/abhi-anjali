import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class FileProcessor implements Runnable {

    private String fileName;

    public FileProcessor(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void run() {
        System.out.println("Started processing: " + fileName +
                " by " + Thread.currentThread().getName());
        try {
            Thread.sleep(3000); // Simulate 3 seconds processing
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finished processing: " + fileName);
    }
}

public class ParallelFileProcessingSystem {

    public static void main(String[] args) {

        // Create thread pool with 5 threads
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // Submit 5 file tasks
        for (int i = 1; i <= 5; i++) {
            executor.submit(new FileProcessor("File_" + i + ".txt"));
        }

        // Shutdown executor
        executor.shutdown();

        try {
            // Wait until all tasks finish
            if (executor.awaitTermination(10, TimeUnit.SECONDS)) {
                System.out.println("All files processed successfully.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}








