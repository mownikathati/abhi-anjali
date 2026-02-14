class LogScanner implements Runnable {

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 3; i++) {
                System.out.println("Scanning logs... Cycle " + i);
                
                // Sleep for 5 seconds
                Thread.sleep(5000);

                System.out.println("Completed scan " + i);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }
    }
}

public class LogMonitoringSystem {

    public static void main(String[] args) throws InterruptedException {

        LogScanner scanner = new LogScanner();
        Thread monitorThread = new Thread(scanner);

        System.out.println("Thread State after creation: " 
                            + monitorThread.getState());  // NEW

        monitorThread.start();

        while (monitorThread.isAlive()) {
            System.out.println("Current Thread State: " 
                                + monitorThread.getState());
            Thread.sleep(1000);
        }

        System.out.println("Final Thread State: " 
                            + monitorThread.getState());  // TERMINATED
    }
}





