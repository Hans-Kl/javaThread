package singleton.lazy.synchronize;

import annotation.SafeButSoSo;

/**
 * 加锁过于粗粒度的饿汉式
 * <p>2020/7/29 16:06</p>
 *
 * @author konglinghan
 * @version 1.0
 */
@SafeButSoSo
public class CoarseLazySingleton {
    private static CoarseLazySingleton instance;

    private CoarseLazySingleton() {
    }

    public static CoarseLazySingleton getInstance() {
        // KLH: 2020/7/29 通过对class对象加锁，保证了临界区操作在同一个jvm中线程安全，
        //  问题是锁粒度过大，所有获取单例对象的线程都会相互阻塞
        synchronized (CoarseLazySingleton.class) {
            if (instance != null) {
                instance = new CoarseLazySingleton();
            }
            return instance;
        }
    }
}
