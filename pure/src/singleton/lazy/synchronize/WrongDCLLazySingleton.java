package singleton.lazy.synchronize;

import annotation.NotSafe;

/**
 * 错误的双检锁饿汉单例模式
 * <p>2020/7/30 09:34</p>
 *
 * @author konglinghan
 * @version 1.0
 */
@NotSafe
public class WrongDCLLazySingleton {
    private static WrongDCLLazySingleton instance;

    private WrongDCLLazySingleton() {

    }

    public WrongDCLLazySingleton getInstance() {
        if (instance == null) {//<1>
            synchronized (WrongDCLLazySingleton.class) {
                if (instance == null) {//<2>
                    // KLH: 2020/7/30 执行构造方法并赋值这一步并非原子操作，具体操作指令为：
                    //  1. 在堆中申请用于存放WrongDCLLazySingleton对象的内存，将对象属性赋零值
                    //  2. 在分配好的内存上执行实例初始化方法
                    //  3. 将堆内存地址赋值给instance变量
                    //  这三个指令可能会重排序为132，结果是：
                    //  a线程先将内存地址赋值给instance变量,还没等执行构造器，线程就中断切出
                    //  下一个线程b执行<1>，看到instance不为null了,直接返回instance变量，但这时instance实例还没执行构造方法，得到的是不完整对象，导致错误结果。
                    instance = new WrongDCLLazySingleton();
                }
            }
        }
        return instance;
    }
}
