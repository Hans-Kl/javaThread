package signal.synchronize;

import java.util.Collection;

/**
 * object.wait()方法的伪代码实现
 * <p>2020/8/2 18:52</p>
 *
 * @author konglinghan
 * @version 1.0
 */
public class PseudoWait {
    private Collection<Thread> entrySet;//klh 锁池
    private Collection<Thread> waitSet;//klh 等待池

    public void myWait() {
        //klh wait方法必须在同步块内持有锁,否则直接抛异常
        if (!entrySet.contains(Thread.currentThread())) {
            throw new IllegalMonitorStateException();
        }
        //klh 加入等待池
        if (!waitSet.contains(Thread.currentThread())) {
            waitSet.add(Thread.currentThread());
        }
        releaseLock(this);//klh 释放锁
        pause(Thread.currentThread());//klh 暂停当前线程,等待唤醒<1>

        acquireLock(this);//klh 被唤醒，重新申请锁资源<2>
        waitSet.remove(Thread.currentThread());//klh 从等待集中移除
        return;//klh wait()方法返回
    }

    /**
     * 暂停线程
     *
     * @param thread
     */
    private void pause(Thread thread) {
    }

    /**
     * 申请内部锁
     *
     * @param monitor 锁所在的对象
     */
    private void acquireLock(Object monitor) {
        if (entrySet.isEmpty()) {
            return;
        } else {
            //klh 如果没有获取到锁，线程阻塞，进入锁池等待锁资源
            entrySet.add(Thread.currentThread());
        }
    }

    /**
     * 释放锁
     *
     * @param monitor 锁所在的对象
     */
    private void releaseLock(Object monitor) {
    }

}
