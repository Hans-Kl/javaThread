package singleton.lazy.synchronize;

import annotation.NotSafe;
import annotation.SafeAndPerfect;

/**
 * 加上volatile关键字的正确双检锁饿汉单例模式
 * <p>2020/7/30 09:34</p>
 *
 * @author konglinghan
 * @version 1.0
 */
@SafeAndPerfect
public class CorrectDCLLazySingleton {
    private static volatile CorrectDCLLazySingleton instance;

    private CorrectDCLLazySingleton() {
    }

    public CorrectDCLLazySingleton getInstance() {
        // KLH:  读屏障
        if (instance == null) {//<1>
            // KLH:  获取屏障
            synchronized (CorrectDCLLazySingleton.class) {
                if (instance == null) {//<2>
                    //klh 释放屏障
                    instance = new CorrectDCLLazySingleton();
                    //klh 写屏障
                }
            }
        }
        return instance;
    }
}
