package threadpool;

import org.omg.CORBA.Object;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 测试关闭线程池的方法
 * <p>2020/8/4 16:02</p>
 *
 * @author konglinghan
 * @version 1.0
 */
public class TestShutDownNow {
    final ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 3, 1000, TimeUnit.SECONDS, new ArrayBlockingQueue<>(3));

    public static void main(String[] args) throws InterruptedException {
        Runnable r = () -> {
            System.out.println(Thread.currentThread().getName() + "执行任务");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                while (true){}// KLH: 2020/8/4 shutdownnow是通过发送中断信号来停止线程的，无法响应中断信号或者不接受中断信号的线程不会被shutdown
//                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "任务完成");
        };
        ThreadPoolExecutor executor = new TestShutDownNow().executor;
        executor.submit(r);
        executor.submit(r);
        executor.submit(r);
        executor.shutdownNow();
        System.out.println(executor.awaitTermination(10, TimeUnit.MILLISECONDS));// KLH: 2020/8/4 主线程等待10毫秒,返回线程池是否关闭
        while (!executor.awaitTermination(100, TimeUnit.MILLISECONDS)) {// KLH: 2020/8/4 常见使用方法
            System.out.println("线程池没有关闭");
        }
        System.out.println(executor.awaitTermination(7, TimeUnit.SECONDS));
    }
}
