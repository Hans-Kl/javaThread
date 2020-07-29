package singleton;

/**
 * 饿汉式单例模式，不存在线程安全问题
 * 2020/7/29 11:55
 *
 * @author konglinghan
 * @version 1.0
 */
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
