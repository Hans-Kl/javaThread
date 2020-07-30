package singleton.lazy.easy;

import annotation.SafeAndPerfect;

/**
 * 利用静态变量初始化的懒汉式单例
 * <p>2020/7/30 14:24</p>
 *
 * @author konglinghan
 * @version 1.0
 */
@SafeAndPerfect
public class StaticLazySingleton {

    private StaticLazySingleton() {

    }

    // KLH: 2020/7/30 单例持有类
    private static class InstanceHolder {
        static final StaticLazySingleton instance = new StaticLazySingleton();
    }

    public static StaticLazySingleton getInstance() {
        return InstanceHolder.instance;
    }
}
