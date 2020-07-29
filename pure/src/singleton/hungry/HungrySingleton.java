package singleton.hungry;

import annotation.Safe;

/**
 * 饿汉式单例模式，不存在线程安全问题
 * <p>2020/7/29 11:55</p>
 *
 * @author konglinghan
 * @version 1.0
 */
@Safe
public class HungrySingleton {
    static final String constString = "HungrySingleton常量";
    static final HungrySingleton instance = new HungrySingleton();

    static {
        System.out.println("HungrySingleton静态代码块");
    }

    private HungrySingleton() {
        System.out.println("HungrySingleton构造方法");
    }

    public static HungrySingleton getInstance() {
        System.out.println("HungrySingleton获取单例实例");
        return instance;
    }
}
