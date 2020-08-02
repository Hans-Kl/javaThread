package signal.synchronize;

/**
 * <p>2020/8/2 12:48</p>
 *
 * @author konglinghan
 * @version 1.0
 */
public class Boot {
    public static void main(String[] args) {
        IsNotifiedNeedLock notifyButSynchronize = new IsNotifiedNeedLock();
        Runnable sender = notifyButSynchronize::send;
        Runnable receiver = notifyButSynchronize::receive;

        Thread sendThread = new Thread(sender);
        Thread receiveThread = new Thread(receiver);

        receiveThread.start();
        sendThread.start();
    }
}
