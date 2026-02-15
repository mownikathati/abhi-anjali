import java.util.concurrent.*;
import java.util.*;

class InterestCalculator implements Callable<Double> {

    private double principal;
    private double rate;
    private int time;
    private String accountNumber;

    public InterestCalculator(String accountNumber, double principal, double rate, int time) {
        this.accountNumber = accountNumber;
        this.principal = principal;
        this.rate = rate;
        this.time = time;
    }

    @Override
    public Double call() throws Exception {
        System.out.println("Calculating interest for Account: " + accountNumber +
                " by " + Thread.currentThread().getName());

        Thread.sleep(2000); // simulate processing delay

        double interest = (principal * rate * time) / 100;
        return interest;
    }
}

public class BankingBatchProcessing {
    public static void main(String[] args) throws Exception {

        ExecutorService executor = Executors.newFixedThreadPool(3);

        List<Future<Double>> results = new ArrayList<>();

        results.add(executor.submit(new InterestCalculator("ACC101", 10000, 5, 2)));
        results.add(executor.submit(new InterestCalculator("ACC102", 20000, 6, 3)));
        results.add(executor.submit(new InterestCalculator("ACC103", 15000, 4, 1)));

        // Collect results (Blocking)
        for (Future<Double> future : results) {
            System.out.println("Calculated Interest: " + future.get());
        }

        executor.shutdown();
    }
}







