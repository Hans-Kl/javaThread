package signal.synchronize;

import util.Debug;

/**
 * 测试notify()方法会否释放锁
 * <p>2020/7/31 17:22</p>
 *
 * @author konglinghan
 * @version 1.0
 */
public class IsNotifyReleaseLock {
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
            Debug.info("send sleep");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Debug.info("send唤醒完毕");
        }
        Debug.info("send执行完毕");
    }
}
