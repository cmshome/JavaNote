package com.lxk.jdk.reflect.whatCanReflectCanDo;


import com.lxk.bean.model.Car;

/**
 * 反射具体能干些什么的测试
 * <p>
 * @author lxk on 2017/7/19
 */
public class Main {
    public static void main(String[] args) {
        String path = "com.lxk.model.Car";
        //getWholePackageAndClassNameByObject();
        //getClassObject(path);
        getNewInstance(path);

    }

    /**
     * 通过Class实例化其他类的对象
     *
     * @param path 类的全路径
     */
    private static void getNewInstance(String path) {
        Class<?> demo = null;
        try {
            demo = Class.forName(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Car car = null;
        try {
            car = (Car) demo.newInstance();
        } catch (Exception ignore) {
        }
        car.setPrice(100);
        car.setSign("qq");
        System.out.println(car);
    }

    /**
     * 实例化Class类对象
     *
     * @param path 类的全路径
     */
    private static void getClassObject(String path) {
        Class<?> demo1 = null;
        Class<?> demo2;
        Class<?> demo3;
        try {
            //一般尽量采用这种形式
            demo1 = Class.forName(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        demo2 = new Car().getClass();
        demo3 = Car.class;
        System.out.println("类名称   " + demo1.getName());
        System.out.println("类名称   " + demo2.getName());
        System.out.println("类名称   " + demo3.getName());
    }

    /**
     * 通过一个对象获得完整的包名和类名
     */
    private static void getWholePackageAndClassNameByObject() {
        Car car = new Car();
        System.out.println(car.getClass().getName());
    }


}
