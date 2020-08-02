package signal.synchronize;

import util.Debug;

/**
 * 测试等待的线程被唤醒继续执行是否需要获取到锁
 * <p>2020/7/31 17:22</p>
 *
 * @author konglinghan
 * @version 1.0
 */
public class IsNotifiedNeedLock {
    private boolean flag = true;

    public void receive() {
        Debug.info("receive进入方法");
        synchronized (this) {
            Debug.info("receive进入临界区");
            while (flag) {
                Debug.info("receive等待");
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Debug.info("receive被唤醒");
        }
    }

    public void send() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (this) {
            Debug.info("send准备唤醒");
            flag = false;
            notify();

            Debug.info("send唤醒完毕");
        }
        synchronized (this) {//试验wait线程被唤醒继续执行的时候需不需要重新获得锁
//      synchronized (Object.class) {
            Debug.info("send sleep");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Debug.info("send执行完毕");
    }
}
