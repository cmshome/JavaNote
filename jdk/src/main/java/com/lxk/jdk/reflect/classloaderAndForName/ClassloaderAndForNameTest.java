package com.lxk.jdk.reflect.classloaderAndForName;

/**
 * Class.forName和classloader的区别
 * 结论如下：
 * class.forName()前者除了将类的.class文件加载到jvm中之外，还会对类进行解释，执行类中的static块。
 * 而classLoader只干一件事情，就是将.class文件加载到jvm中，不会执行static中的内容,只有在newInstance才会去执行static块。
 * 静态代码块的执行外，竟然还有一个静态方法被执行，就是给静态变量赋值的静态方法被执行了。
 * <p>
 * @author lxk on 2017/2/21
 */
public class ClassloaderAndForNameTest {
    public static void main(String[] args) {
        String wholeNameLine = "com.lxk.reflect.classloaderAndForName.Line";
        String wholeNamePoint = "com.lxk.reflect.classloaderAndForName.Point";
        System.out.println("下面是测试Classloader的效果");
        testClassloader(wholeNameLine, wholeNamePoint);
        System.out.println("----------------------------------");
        System.out.println("下面是测试Class.forName的效果");
        testForName(wholeNameLine, wholeNamePoint);
        //Line line = new Line();

    }

    /**
     * classloader
     */
    private static void testClassloader(String wholeNameLine, String wholeNamePoint) {
        Class<?> line;
        Class<?> point;
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        try {
            line = loader.loadClass(wholeNameLine);
            point = loader.loadClass(wholeNamePoint);
            //demo = ClassloaderAndForNameTest.class.getClassLoader().loadClass(wholeNamePoint);//这个也是可以的
            System.out.println("line   " + line.getName());
            System.out.println("point   " + point.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Class.forName
     */
    private static void testForName(String wholeNameLine, String wholeNamePoint) {

        try {
            Class line = Class.forName(wholeNameLine);
            Class point = Class.forName(wholeNamePoint);
            System.out.println("line   " + line.getName());
            System.out.println("point   " + point.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
