package com.myproject.javase;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.myproject.javase.thread.*;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;

import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;


public class ThreadTest {
    private final Logger logger = Logger.getLogger("test.Test");
 //测试三种创建线程方法
    @Test
    public void testThread() throws InterruptedException {
     try {
            new Thread(new RunnableTest()).start();
            new Threadtest().start();
            FutureTask<String> task = new FutureTask<String>(new CallableTest());
            new Thread(task).start();
            logger.info(task.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //测试线程安全问题
    @Test
    public void testThreadUnsafe() throws InterruptedException {
        WindowUnsafe windows = new WindowUnsafe();
        Thread window1 = new Thread(windows);
        Thread window2 = new Thread(windows);
        Thread window3 = new Thread(windows);
        window1.setName("window1");
        window2.setName("window2");
        window3.setName("window3");
        window1.start();
        window2.start();
        window3.start();
        Thread.sleep(5000);
    }

   //线程通信
    @Test
    public void testThreadCommunicate() throws InterruptedException {
        ThreadCommunicate testWindow = new ThreadCommunicate();
        Thread window1 = new Thread(testWindow);
        Thread window2 = new Thread(testWindow);
        window1.start();
        window2.start();
        Thread.sleep(5000);
    }

//   //测试生产者消费者问题
//    @Test
//    @Execution(CONCURRENT)  //多线程并发执行测试方法
//    @RepeatedTest(3)  //线程重复执行三次
//    public void testProducterConsumer() throws InterruptedException {
//        new ProductorConsumer().start();
//        Thread.sleep(10000);
//    }

    //测试线程的方法
    @Test
    public void testThreadMethod() throws InterruptedException{
        RunnableTest test = new RunnableTest();
        Thread window1 = new Thread(test);
        Thread window2 = new Thread(test);
        Thread window3 = new Thread(test);
        window1.setName("test1");
        window2.setName("test2");
        window3.setName("test3");
        window2.setDaemon(true);
        window2.setPriority(5);
        window3.setPriority(1);
        window1.setPriority(10);
        Thread.setDefaultUncaughtExceptionHandler((thread,exception)->
        {
            System.out.println(thread.isInterrupted());
            thread.interrupted();
        });
        window1.start();
        window2.start();
        window3.start();
        window1.interrupt();
        AtomicInteger atomicInteger=new AtomicInteger(2);
        System.out.println(atomicInteger.incrementAndGet());
        atomicInteger.updateAndGet(x->Math.max(atomicInteger.get(),4));
        atomicInteger.accumulateAndGet(5,Math::max);
        LongAdder longAdder= new LongAdder();
        longAdder.add(3L);
        longAdder.add(4L);
        System.out.println(longAdder.sum());
        LongAccumulator longAccumulator=new LongAccumulator(Long::sum,0);
        longAccumulator.accumulate(4L);
        longAccumulator.accumulate(3L);
        System.out.println(longAccumulator.get());
        Thread.sleep(10000000);
    }

    @Test
    public void testBlockingQueue() throws InterruptedException{
        LinkedBlockingDeque<String> linkedBlockingDeque = new LinkedBlockingDeque<>();
        logger.log(Level.INFO,linkedBlockingDeque.peek());
        linkedBlockingDeque.add("3");
        logger.log(Level.INFO,linkedBlockingDeque.element());
        logger.log(Level.INFO,linkedBlockingDeque.poll());
        ArrayBlockingQueue<String> arrayBlockingQueue=new ArrayBlockingQueue<>(3);
        logger.log(Level.INFO, String.valueOf(arrayBlockingQueue.offer("2")));
        arrayBlockingQueue.put("3");
        arrayBlockingQueue.remove();
        arrayBlockingQueue.take();
        PriorityBlockingQueue<String> priorityBlockingQueue =new PriorityBlockingQueue<>(2, String::compareTo);
        priorityBlockingQueue.add("bbb");
        priorityBlockingQueue.add("aaa");
        logger.log(Level.INFO,priorityBlockingQueue.peek());
        DelayQueue<DelayedThread> delayQueue = new DelayQueue<DelayedThread>();
        DelayedThread delayedThread1=new DelayedThread(1000L);
        DelayedThread delayedThread2=new DelayedThread(2000L);
        delayedThread1.setName("thread1");
        delayedThread2.setName("thread2");
        delayQueue.add(delayedThread1);
        delayQueue.add(delayedThread2);
        while(!delayQueue.isEmpty()){
            if(delayQueue.element().getDelay(TimeUnit.NANOSECONDS)<=0){
                delayQueue.take().start();
            }
        }
        LinkedTransferQueue<String> linkedTransferQueue= new LinkedTransferQueue<>();
        linkedTransferQueue.add("3");
        linkedTransferQueue.take();
    }

    public  class DelayedThread extends Thread implements Delayed {
        ThreadLocal<Integer> threadLocal=new ThreadLocal<>();
        volatile Long startTime;
        @Override
        public long getDelay(TimeUnit unit) {
            return startTime-System.currentTimeMillis();
        }

        @Override
        public int compareTo(Delayed o) {
            return (int) (startTime-((DelayedThread)o).startTime);
        }
        @Override
        public void run(){
            logger.log(Level.INFO,Thread.currentThread().getName());
        }

        public  DelayedThread(Long DelayedTime){
            this.startTime=System.currentTimeMillis()+DelayedTime;
        }
    }

    @Test
    public void testCollecion(){
        ConcurrentHashMap<String,String> concurrentHashMap=new ConcurrentHashMap<>();
        ConcurrentSkipListMap<String,String> concurrentSkipListMap=new ConcurrentSkipListMap<>();
        ConcurrentSkipListSet<String> concurrentSkipListSet =new ConcurrentSkipListSet<>();
        ConcurrentLinkedDeque<String> concurrentLinkedDeque=new ConcurrentLinkedDeque<>();
        CopyOnWriteArrayList<String> copyOnWriteArrayList=new CopyOnWriteArrayList<>();
        CopyOnWriteArraySet<String> copyOnWriteArraySet=new CopyOnWriteArraySet<>();
        Arrays.parallelSort(new int[]{3,2,1,5,4});
        /*Vector<String> vector=new Vector<>();
        Hashtable<String,String> hashtable=new Hashtable<>();*/
    }

    @Test
    public void testThreadPool() throws ExecutionException, InterruptedException {
        CallableTest callableTest=new CallableTest();
        FutureTask<String> futureTask=new FutureTask<>(callableTest);
        new Thread(futureTask).start();
        futureTask.get();
        ExecutorService cachedThreadPool=Executors.newCachedThreadPool();
        Future<?> future1=cachedThreadPool.submit(new RunnableTest());
        Future<?> future2=cachedThreadPool.submit(new RunnableTest());
        Future<?> future3=cachedThreadPool.submit(new RunnableTest());
        List<CallableTest> invokeList=new ArrayList<>();
        invokeList.add(new CallableTest());
        invokeList.add(new CallableTest());
        ExecutorCompletionService<String> completionService=new ExecutorCompletionService<>(cachedThreadPool);
        List<Future<String>> resInvoke=cachedThreadPool.invokeAll(invokeList);
        completionService.submit(new CallableTest());
        completionService.submit(new CallableTest());
        for(Future<String> res :resInvoke){
            System.out.println("invokeall"+res.get());
            System.out.println("completion"+completionService.take().get());
        }
        String res2Invoke=cachedThreadPool.invokeAny(invokeList);
        System.out.println(res2Invoke);
        cachedThreadPool.shutdown();
        ExecutorService fixedThreadPool=Executors.newFixedThreadPool(2);
        fixedThreadPool.shutdownNow();
        ScheduledExecutorService scheduledThreadPool=Executors.newScheduledThreadPool(3);
        scheduledThreadPool.schedule(new RunnableTest(),3000,TimeUnit.NANOSECONDS);
        ExecutorService workStealingPool=Executors.newWorkStealingPool();
        ExecutorService singleThreadExecutor=Executors.newSingleThreadExecutor();
        ScheduledExecutorService singleThreadScheduledExecutor=Executors.newSingleThreadScheduledExecutor();
        List<Integer> lists= List.of(1,2,3,4,5,6,7,8,9);
        ForkJoinPool pool=new ForkJoinPool();
        testRecursive test=new testRecursive(lists);
        pool.invoke(test);
        System.out.println(test.join());
        RecursiveAction action=new RecursiveAction() {
            @Override
            protected void compute() {
                return;
            }
        };
    }

    public class testRecursive extends RecursiveTask<Integer>{
        List<Integer> intList;
        public testRecursive(List<Integer> list){
                intList=list;
        }

        @Override
        protected Integer compute() {
            int size=intList.size();
            if(size>=2){
                testRecursive a=new testRecursive(intList.subList(0,size/2));
                testRecursive b=new testRecursive(intList.subList(size/2,size));
                invokeAll(a,b);
                return  a.join()+b.join();
            }else{
                return intList.get(0);
            }
        }
    }

    @Test
    public void TestAsynchronous(){
        ExecutorService service=Executors.newCachedThreadPool();
        CompletableFuture<String> completableFuture=CompletableFuture.supplyAsync(()->{return "3";},service);
        completableFuture.thenAccept(System.out::println);
        completableFuture.whenComplete((s,t)->{if(t==null){System.out.println(s);}else {t.printStackTrace();}});
        completableFuture.handle((s,t)->{if(t==null){System.out.println(s);}else {t.printStackTrace();}
            return s;
        });
        completableFuture.exceptionally(e->{return "3";});
        CompletableFuture<Integer> a=completableFuture.thenApply(Integer::parseInt);
        CompletableFuture<Integer> b=completableFuture.thenCompose(s->CompletableFuture.supplyAsync(()->{return Integer.parseInt(s);}));
        CompletableFuture<String> c=completableFuture.thenCombine(CompletableFuture.supplyAsync(()->{return "3";}),(x,y)->{return x+y;});
        CompletableFuture<String> d=completableFuture.applyToEither(CompletableFuture.supplyAsync(()->{return "3";}),x->{return x;});
    }

    @Test
    public void TestProcess() throws IOException, InterruptedException {
        ProcessBuilder processBuilder=new ProcessBuilder("cmd.exe","/c","dir");
        System.out.println(processBuilder.directory());
        processBuilder.redirectErrorStream(true);
        Map<String,String> env=processBuilder.environment();
        System.out.println(env.toString());
        Process process =processBuilder.start();
        ProcessHandle ph1=process.toHandle();
        ProcessHandle ph2=ProcessHandle.of(8124).get();
        ProcessHandle ph3=ProcessHandle.current();
        System.out.println(ph3.pid());
        System.out.println(ph3.info().toString());
        System.out.println(ProcessHandle.allProcesses().toString());
        if(process.waitFor(10000,TimeUnit.NANOSECONDS)){
            System.out.println(process.exitValue());
        }else {
            process.destroy();
        }
        OutputStream outputStream=process.getOutputStream();
        InputStream inputStream=process.getInputStream();
        List<ProcessBuilder> processBuilders=new ArrayList<>();
        processBuilders.add(processBuilder);
        ProcessBuilder processBuilder2=new ProcessBuilder("cmd.exe","/d","dir");
        processBuilders.add(processBuilder2);
        ProcessBuilder.startPipeline(processBuilders);
        Process s=processBuilders.get(processBuilders.size()-1).start();
        if(s.waitFor(10000,TimeUnit.NANOSECONDS)){
            System.out.println(s.exitValue());
        }else {
            s.destroyForcibly();
        }
        CompletableFuture<Process> exit=s.onExit();
        exit.whenComplete((k,t)->System.out.println("ok"));
    }

//    @Test
//    public void testVirtual(){
//        Thread virtualThread1=Thread.startVirtualThread(new RunnableTest());
//        Thread vt2=Thread.ofVirtual().start(new RunnableTest());
//        ExecutorService vtp=Executors.newVirtualThreadPerTaskExecutor();
//    }

    @Test
    public void  testSy(){
        synchronized(this){
            System.out.println("1");
        }
    }

    @Test
    public void testt() {
            System.out.println(new Date());
            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4,
                    4,
                    1000,
                    TimeUnit.SECONDS,
                    new LinkedBlockingQueue<>(1024),
                    new ThreadFactoryBuilder().setNameFormat("test-pool-%d").build(),
                    new ThreadPoolExecutor.CallerRunsPolicy());

            List<Integer> list = Arrays.asList(1, 2, 3, 4);

            List<String> collect = list.parallelStream().map(time -> CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return Collections.singletonList(time + "s");
            }, threadPoolExecutor)).flatMap(future -> future.join().stream()).collect(Collectors.toList());

            System.out.println(collect);
            System.out.println(new Date());
        }


    @Test
    public void testy() {
        System.out.println(new Date());
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4,
                4,
                1000,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1024),
                new ThreadFactoryBuilder().setNameFormat("test-pool-%d").build(),
                new ThreadPoolExecutor.CallerRunsPolicy());

        List<Integer> list = Arrays.asList(1, 2, 3, 4);

        List<CompletableFuture<String>> futures = list.stream().map(time -> CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Collections.singletonList(time) + "s";
        }, threadPoolExecutor)).collect(Collectors.toList());

        List<String> resultList = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenApply(v -> futures.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList()))
                .join();
        System.out.println(resultList);
        System.out.println(new Date());
    }
}
