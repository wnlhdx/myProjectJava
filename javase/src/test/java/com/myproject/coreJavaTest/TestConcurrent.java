package com.myproject.coreJavaTest;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

/**
 * @author lkxl
 */
public class TestConcurrent {
    @Test
    void TestAtomic(){
        AtomicInteger atomicInteger=new AtomicInteger(10);
        AtomicInteger value = new AtomicInteger();
        AtomicInteger newV = new AtomicInteger();
        new Thread(()-> {
            do{
                value.set(atomicInteger.get());
                newV.set(100);
            }while (atomicInteger.compareAndSet(value.get(), newV.get()));
        });

    }


    @Test
    void TestBlockingQuene() throws InterruptedException {
        LinkedBlockingDeque<Integer> lbq=new LinkedBlockingDeque<>();
        ArrayBlockingQueue<Integer> abq=new ArrayBlockingQueue<>(1);
        PriorityBlockingQueue<Integer> pbq=new PriorityBlockingQueue<>();
        TransferQueue<Integer> ltq=new LinkedTransferQueue<>();
        DelayQueue<Delayed> dq=new DelayQueue<Delayed>();
        Thread producer =new Thread(()->{while(true){abq.offer(1);System.out.println("thread:add"+abq.peek());}});
        Thread consumer = new Thread(()->{while(true){abq.poll();System.out.println("thread:remove"+abq.peek());}});
        producer.start();
        consumer.start();
        Thread tproducer =new Thread(()->{while(true){
            try {
                ltq.transfer(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("transfer thread:add"+abq.peek());}});
        Thread tconsumer = new Thread(()->{while(true){
            try {
                ltq.take();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("transfer thread:remove"+abq.peek());}});
        tproducer.start();
        tconsumer.start();
    }
}
