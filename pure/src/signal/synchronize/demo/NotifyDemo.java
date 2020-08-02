package signal.synchronize.demo;

import java.util.Random;

/**
 * 用线程通信机制实现一个等待通知的模型
 * <p>2020/7/31 16:07</p>
 *
 * @author konglinghan
 * @version 1.0
 */
public class NotifyDemo {
    private boolean hasMessage = false;

    public void send() throws InterruptedException {
            synchronized (this) {
                while (!hasMessage) {
                    System.out.println("send" + "没有消息，等待！");
                    wait(3000);
                }
                System.out.println("send" + "收到信息，发送！");
            }
    }

    public void receive() throws InterruptedException {
            Thread.sleep(1000);
            Random random = new Random();
            boolean flag = random.nextBoolean();
            if (flag) {
                synchronized (this) {
                    hasMessage = true;
                    System.out.println("receive" + "有新消息！");
                    notify();
                }
            } else {
                System.out.println("receive" + "没有消息");
            }
    }
}
