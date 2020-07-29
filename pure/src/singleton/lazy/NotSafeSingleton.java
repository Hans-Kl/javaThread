package singleton.lazy;

import annotation.NotSafe;

/**
 * 单线程版单例模式，多线程环境下非线程安全
 * <p>2020/7/29 16:38</p>
 *
 * @author konglinghan
 * @version 1.0
 */
@NotSafe
public class NotSafeSingleton {
    private NotSafeSingleton instance;

    private NotSafeSingleton() {

    }

    public NotSafeSingleton getInstance() {
        //if-then-act操作，非原子操作，会有线程安全问题
        if (instance != null) {
            instance = new NotSafeSingleton();
        }
        return instance;
    }

}
