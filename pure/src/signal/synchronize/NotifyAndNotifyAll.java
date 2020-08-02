package signal.synchronize;

import util.Debug;

/**
 * 测试object.notify()和object.notifyAll()的区别
 * <p>2020/8/2 17:33</p>
 *
 * @author konglinghan
 * @version 1.0
 */
public class NotifyAndNotifyAll {
    public static void main(String[] args) {
        Demo demo = new Demo();
        Runnable sender = demo::send;
        Runnable receiver = demo::receive;

        Thread sendThread = new Thread(sender);
        Thread receiveThread1 = new Thread(receiver, "receive1");
        Thread receiveThread2 = new Thread(receiver, "receive2");
        Thread receiveThread3 = new Thread(receiver, "receive3");

        receiveThread1.start();
        receiveThread2.start();
        receiveThread3.start();
        sendThread.start();
    }


    private static class Demo {
        private boolean flag = true;

        public void receive() {
            Debug.info(Thread.currentThread().getName() + "进入方法");
            synchronized (this) {
                Debug.info(Thread.currentThread().getName() + "进入临界区");
                while (flag) {
                    Debug.info(Thread.currentThread().getName() + "等待");
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Debug.info(Thread.currentThread().getName() + "被唤醒");
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
//                notify();
                notifyAll();
                Debug.info("send唤醒完毕");
            }
            Debug.info("send执行完毕");
        }
    }
}
