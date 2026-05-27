package Concurrency.Thread_Thread_Class;

class ThreadExample extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Thread: " + Thread.currentThread().getId() + " - Iteration: " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted.");
            }
        }
    }
}

public class MyThread {
    public static void main(String[] args) {
        ThreadExample thread1 = new ThreadExample();
        ThreadExample thread2 = new ThreadExample();
        
        thread1.start();
        thread2.start();
    }
}