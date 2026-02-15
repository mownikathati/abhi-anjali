import java.util.*;
import java.util.concurrent.*;

class ReportTask implements Callable<String> {

    private String reportName;

    public ReportTask(String reportName) {
        this.reportName = reportName;
    }

    @Override
    public String call() throws Exception {
        System.out.println("Generating " + reportName + 
                " by " + Thread.currentThread().getName());

        // Simulate report processing time
        Thread.sleep(2000);

        return reportName + " generated successfully.";
    }
}

public class ParallelReportGenerator {

    public static void main(String[] args) throws Exception {

        // Create thread pool
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Create list of tasks
        List<Callable<String>> reportTasks = Arrays.asList(
                new ReportTask("Sales Report"),
                new ReportTask("Inventory Report"),
                new ReportTask("Employee Report"),
                new ReportTask("Finance Report"),
                new ReportTask("Customer Report")
        );

        // invokeAll() executes all tasks in parallel
        List<Future<String>> results = executor.invokeAll(reportTasks);

        System.out.println("\n----- Report Summary -----");

        // Collect results
        for (Future<String> result : results) {
            System.out.println(result.get());
        }

        executor.shutdown();
    }
}











