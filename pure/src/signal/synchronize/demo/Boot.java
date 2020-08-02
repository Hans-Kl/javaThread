package signal.synchronize.demo;

/**
 * <p>2020/7/31 16:06</p>
 *
 * @author konglinghan
 * @version 1.0
 */
public class Boot {
    public static void main(String[] args) {
        NotifyDemo notifyDemo = new NotifyDemo();
        Thread notifyThread = new Thread(() -> {
            try {
                notifyDemo.receive();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread waitThread = new Thread(() -> {
            try {
                notifyDemo.send();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        waitThread.start();
        notifyThread.start();
    }


}
