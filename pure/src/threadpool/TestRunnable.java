package threadpool;

import java.util.concurrent.*;

/**
 * 测试不需要返回值的runnable线程池
 * <p>2020/8/3 21:43</p>
 *
 * @author konglinghan
 * @version 1.0
 */
public class TestRunnable {
    final ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 1000, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(1), new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestRunnable testRunnable = new TestRunnable();
        int i = Runtime.getRuntime().availableProcessors();
        System.out.println(i);
        Future<?> submit = testRunnable.executor.submit(() -> {
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        });
        testRunnable.executor.submit(() -> {
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(testRunnable.executor.getActiveCount());
        });
        testRunnable.executor.submit(() -> {
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(testRunnable.executor.getActiveCount());
        });
        System.out.println(Thread.currentThread().getName());
//        List<Runnable> runnables = testRunnable.executor.shutdownNow();
//        System.out.println(runnables.size());
        System.out.println("xyz" + submit.get());
    }
}
