package com.henan.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/** 
 * @author wn: 
 * Future模式
Future模式的核心在于：去除了主函数的等待时间，并使得原本需要等待的时间段可以用于处理其他业务逻辑

Futrure模式:对于多线程，如果线程A要等待线程B的结果，那么线程A没必要等待B，直到B有结果，可以先拿到一个未来的Future，等B有结果是再取真实的结果。

在多线程中经常举的一个例子就是：网络图片的下载，刚开始是通过模糊的图片来代替最后的图片，等下载图片的线程下载完图片后在替换。而在这个过程中可以做一些其他的事情。
 * @version 上午16:53:57 
 * 类说明 
 */
public class ThreadCallTest2
{
    public static void main(String[] args)
    {
        long time1 = System.currentTimeMillis();
        //ExecutorService executor = Executors.newCachedThreadPool();
        ExecutorService executor =
            new ThreadPoolExecutor(10, 20, 1000, TimeUnit.MILLISECONDS,
                new SynchronousQueue<Runnable>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        /*TaskP task2 = new TaskP(3);
        Future<Integer> result2 = executor.submit(task2);*/
        
        System.out.println("call result111111111111111");
        try
        {
            List<Future> list = new ArrayList<Future>();
            for (int i = 0; i < 10; i++)
            {
                TaskP task = new TaskP(i);
                Future<Integer> result = executor.submit(task);
                list.add(result);
            }
            /* if (executor != null)
                 executor.shutdown();*/
            int sum = 0;
            for (int i = 0; i < 10; i++)
            {
                Future<Integer> result = list.get(i);
                int res = result.get();
                System.out.println("call result" + res);
                sum += res;
            }
            System.out.println("sum:" + sum);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        catch (ExecutionException e)
        {
            e.printStackTrace();
        }
        long time2 = System.currentTimeMillis();
        System.out.println("当前程序耗时：" + (time2 - time1) + "ms");
        System.out.println("over");
        
    }
    
    public static void main2(String[] args)
    {
        long time1 = System.currentTimeMillis();
        ExecutorService executor = Executors.newCachedThreadPool();
        /*TaskP task2 = new TaskP(3);
        Future<Integer> result2 = executor.submit(task2);*/
        
        System.out.println("call result111111111111111");
        try
        {
            int sum = 0;
            for (int i = 0; i < 10; i++)
            {
                TaskP task = new TaskP(i);
                Future<Integer> result = executor.submit(task);
                int res = result.get();
                System.out.println("call result" + res);
                sum += res;
            }
            if (executor != null)
                executor.shutdown();
            System.out.println("sum:" + sum);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        catch (ExecutionException e)
        {
            e.printStackTrace();
        }
        long time2 = System.currentTimeMillis();
        System.out.println("当前程序耗时：" + (time2 - time1) + "ms");
        System.out.println("over");
        
    }
    
    /**
     * 
     * Callable
    在Java中，创建线程一般有两种方式，一种是继承Thread类，一种是实现Runnable接口。然而，这两种方式的缺点是在线程任务执行结束后，无法获取执行结果。我们一般只能采用共享变量或共享存储区以及线程通信的方式实现获得任务结果的目的。

    不过，Java中，也提供了使用Callable和Future来实现获取任务结果的操作。Callable用来执行任务，产生结果，而Future用来获得结果。
     * <功能详细描述>
     * 
     * @author  zhangjian
     * @version  [版本号, 2019年12月16日]
     */
    static class TaskP implements Callable<Integer>
    {
        int s = 1;
        
        public TaskP(int s)
        {
            this.s = s;
        }
        
        @Override
        public Integer call()
            throws Exception
        {
            System.out.println("3.开始 ...." + s);
            Thread.sleep(3000);
            System.out.println("4.结束 ...." + s);
            return s;
        }
    }
}