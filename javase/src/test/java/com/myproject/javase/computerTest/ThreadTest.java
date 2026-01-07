package com.myproject.javase.computerTest;

public class ThreadTest {

    private static final Object lock = new Object();
    private static int sharedCounter = 0;

    public static void main(String[] args) throws InterruptedException {

        // 线程 A：增加计数
        Thread threadA = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                synchronized (lock) {
                    sharedCounter++;
                    System.out.println("Thread A incremented: " + sharedCounter);
                    lock.notify(); // 唤醒等待线程
                }
                sleepRandom();
            }
        });

        // 线程 B：减少计数
        Thread threadB = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                synchronized (lock) {
                    while (sharedCounter == 0) { // 阻塞条件
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    sharedCounter--;
                    System.out.println("Thread B decremented: " + sharedCounter);
                }
                sleepRandom();
            }
        });

        threadA.start();
        threadB.start();

        threadA.join();
        threadB.join();

        System.out.println("Final counter: " + sharedCounter);
    }

    private static void sleepRandom() {
        try {
            Thread.sleep((long) (Math.random() * 500)); // 模拟随机调度
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
