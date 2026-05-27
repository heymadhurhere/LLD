package Concurrency.Callable;

import java.util.concurrent.*;

public class CallableExample {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Callable<String> callable1 = new MyCallable("Task 1");
        Callable<String> callable2 = new MyCallable("Task 2");

        try {
            Future<String> future1 = executor.submit(callable1);
            Future<String> future2 = executor.submit(callable2);

            System.out.println("Result of Task 1: " + future1.get());
            System.out.println("Result of Task 2: " + future2.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
