import java.util.concurrent.*;

public class ScheduledCleanupJob {

    public static void main(String[] args) {

        // Create Scheduled Executor with 1 thread
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // Task to run every 5 seconds
        Runnable cleanupTask = () -> {
            System.out.println("Cleaning temporary files... " +
                    Thread.currentThread().getName() +
                    " | Time: " + java.time.LocalTime.now());
        };

        // Schedule task at fixed rate (initial delay 0, period 5 seconds)
        ScheduledFuture<?> scheduledTask =
                scheduler.scheduleAtFixedRate(cleanupTask, 0, 5, TimeUnit.SECONDS);

        // Stop scheduler after 20 seconds
        scheduler.schedule(() -> {
            System.out.println("Stopping cleanup job...");
            scheduledTask.cancel(true);   // Cancel periodic task
            scheduler.shutdown();         // Shutdown executor
        }, 20, TimeUnit.SECONDS);
    }
}









