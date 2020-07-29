package singleton.hungry;

/**
 * 启动类
 * <p>2020/7/29 13:31</p>
 *
 * @author konglinghan
 * @version 1.0
 */
public class BootPackage {
    public static void main(String[] args) {
        System.out.println("JVM启动");
        //static final 修饰的常量，在编译期已将常量放入使用类(在本例中是BootPackage类)的class文件常量池中，运行时已经不存在依赖关系了，所以不会导致类的初始化
        System.out.println(HungrySingleton.constString);
        System.out.println(HungrySingleton.getInstance());//static final 修饰的对象，会导致类的初始化
    }
}
