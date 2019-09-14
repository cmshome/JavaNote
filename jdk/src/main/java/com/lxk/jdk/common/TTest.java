package com.lxk.jdk.common;

import com.google.common.collect.Lists;
import com.lxk.bean.model.CEO;
import com.lxk.bean.model.Employee;
import com.lxk.bean.model.Manager;
import com.lxk.bean.model.Point;

import java.util.List;

/**
 * 测试泛型
 * E   — Element，    常用在java Collection里，如：List<E>,Iterator<E>,Set<E>
 * K,V — Key，Value， 代表Map的键值对
 * N   — Number，     数字
 * T   — Type，       类型，如String，Integer等等
 *
 * @author lxk on 2017/6/14
 */
public class TTest {
    public static void main(String[] args) {
        testExtendsT();
        //认识泛型 以其中的 通配符 ？
        testQuestionMark();
        //泛型在继承的时候的使用
        testSuperT();



        //泛型方法的使用
        testMethodT("sss");
        //自动装箱操作 int -> Integer
        testMethodT(12);

    }

    /**
     * 静态不静态就差个static
     *
     * @param t   参数
     * @param <T> 参数的类型
     */
    private static <T> void testMethodT(T t) {
        System.out.println(t.toString());
    }

    /**
     * 测试泛型中的通配符：？  顺路就把extends给测啦。
     * 结论：
     * <? extends XXX>指填充为派生于XXX的任意子类
     * 利用<? extends XXX>定义的变量，只可取其中的值，不可修改
     */
    private static void testQuestionMark() {
        Point<Integer> integerPoint = new Point<>(10, 10);
        Point<Float> floatPoint = new Point<>(10.f, 10.f);
        Point<Double> doublePoint = new Point<>(10.d, 10.d);
        //Point<String> stringPoint = new Point<>("你猜", "我不猜");

        //通配符  ?  无边界通配符
        //无边界通配符？则只能用于填充泛型变量T，表示通配任何类型
        Point<?> point;
        point = integerPoint;
        System.out.println("x:"+point.getX()+" y:" + point.getY());
        //point.setX(10);//利用<? extends Number>定义的变量，只可取其中的值，不可修改
        point = floatPoint;
        System.out.println("x:"+point.getX()+" y:" + point.getY());
        point = doublePoint;
        System.out.println("x:"+point.getX()+" y:" + point.getY());
        //point = stringPoint;//若是在类中使用了extends number，那么这个类型就必须得是数字啦
        //System.out.println("x:"+point.getX()+" y:" + point.getY());



        //无边界通配符
        List<Point<?>> list = Lists.newArrayList();
        list.add(integerPoint);
        list.add(floatPoint);
        list.add(doublePoint);
        System.out.println(list.toString());

        //边界通配符
        Point<? extends Number> numberPoint;
        numberPoint = new Point<>(1,1);
        numberPoint = new Point<>(123L,1234L);
        System.out.println(numberPoint.getX());
        //能取值，不能设值。
        //numberPoint.setX(12);
        //类似的道理：人是对象，但是对象不一定是人。
    }


    /**
     * 测试Java泛型中的extends
     * 希望泛型类型只能是某一部分类型，你会希望是Number或其子类类型。这个想法其实就是给泛型参数添加一个界限。
     */
    private static void testExtendsT() {
        Point<? extends Number> point;
        point = new Point<>(10, 10);
        point = new Point<>(10f, 10f);
        point = new Point<>(10d, 10d);
        //直接类型检查不合适，编译阶段就报错。
        //point = new Point<String>("", "");


    }

    /**
     * 通配符  ？
     * 测试Java泛型中的super
     * 如果说 <? extends XXX>指填充为派生于XXX的任意子类的话，那么<? super XXX>则表示填充为任意XXX的父类！
     */
    private static void testSuperT() {
        List<? super Manager> sup;
        List<Employee> employeeList = Lists.newArrayList();
        sup = employeeList;
        List<Manager> managerList = Lists.newArrayList();
        sup = managerList;
        //List<CEO> ceoList = Lists.newArrayList();//不是其父类
        //sup = ceoList;
        //编译器无法确定<? super Manager>的具体类型，但唯一可以确定的是Manager()、CEO()肯定是<? super Manager>的子类，
        // 所以肯定是可以add进去的。但Employee不一定是<? super Manager>的子类，所以不能确定，
        // 不能确定的，肯定是不允许的，所以会报编译错误。
        //sup.add(new Employee());//报错
        sup.add(new Manager());//添加这个会被转成Manager类型
        sup.add(new CEO());//添加这个会被转成Manager类型
        Manager s = (Manager) sup.get(0);//道理同上
    }
}
