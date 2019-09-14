package com.lxk.guava.collection;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;
import com.lxk.bean.model.Car;
import com.lxk.bean.model.Person;
import com.lxk.bean.ordering.OrderingConstants;
import com.lxk.bean.ordering.PersonByAge;
import com.lxk.bean.ordering.PersonByName;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;


/**
 * guava ordering test
 *
 * @author lxk on 2018/8/30
 */
public class OrderingTest {
    public static void main(String[] args) {
        //naturalTest();
        //usually();
        //classCompare();
        //sortedCopy();
        //arbitrary();
        //sortTwoWay();
        //testListSort();
        //testFunction();
        //from();
        whenNull();
    }

    /**
     * 可见在出现null或者empty的时候，处理的不是很完美。还是自己在compare方法里面手动处理比较稳妥。
     */
    private static void whenNull() {
        List<Person> persons = Lists.newArrayListWithExpectedSize(4);
        persons.add(new Person(11, "周星驰"));
        persons.add(new Person(99, "陈世美"));
        persons.add(new Person(21, "潘金莲"));
        persons.add(new Person(15, "阿姆斯特丹"));
        persons.add(new Person(15, ""));
        persons.add(new Person(15, null));
        persons.add(null);
        persons.add(new Person(15, "阿姆斯特丹"));
        persons.add(new Person(15, "阿姆斯特丹"));
        persons.sort(OrderingConstants.NAME_ORDERING.nullsLast());
        persons.forEach(person -> System.out.print(person == null ? null : "[" + person.getName() + "] "));
        //[] [阿姆斯特丹] [阿姆斯特丹] [阿姆斯特丹] [陈世美] [潘金莲] [周星驰] [null]
    }

    private static void from() {
        Comparator<Person> ascComparatorByAge = new PersonByAge();
        Ordering<Person> personOrdering = Ordering.from(ascComparatorByAge);
        Person p1 = new Person(11, "adf");
        Person p2 = new Person(99, "ggf");
        Person p3 = new Person(21, "444");
        Person p4 = new Person(15, "yrf");

        //集合初始化的时候，若大小可知，应初始化固定大小的集合，也是个好习惯。
        List<Person> persons = Lists.newArrayListWithCapacity(4);
        persons.add(p1);
        persons.add(p2);
        persons.add(p3);
        persons.add(p4);
        persons.sort(personOrdering.reverse());

    }

    private static void testFunction() {
        Ordering<Person> ordering = Ordering.natural().nullsFirst().onResultOf(new Function<Person, String>() {
            @Override
            public String apply(Person person) {
                return person.getName();
            }
        });

        List<Person> persons = Lists.newArrayListWithExpectedSize(4);
        persons.add(new Person(11, "周星驰"));
        persons.add(new Person(99, "陈世美"));
        persons.add(new Person(21, "潘金莲"));
        persons.add(new Person(15, "阿姆斯特丹"));
        persons.sort(ordering);
        persons.forEach(person -> System.out.print(person.getName() + " "));

    }

    /**
     * 先是按年纪排序，年纪相同，再按名称排序。
     */
    private static void sortTwoWay() {
        //集合初始化的时候，若大小可知，应初始化固定大小的集合，也是个好习惯。
        List<Person> persons = Lists.newArrayListWithExpectedSize(6);
        persons.add(new Person(11, "周星驰"));
        persons.add(new Person(11, "吴孟达"));
        persons.add(new Person(44, "陈世美"));
        persons.add(new Person(44, "小金金--是程咬金的意思，不是潘金莲。。。"));
        persons.add(new Person(22, "潘金莲"));
        persons.add(new Person(22, "武松"));
        persons.add(new Person(33, "阿姆斯特丹"));
        persons.add(new Person(33, "阿姆斯特朗"));

        //可以拿出去，分开声明，然后如下合并一起。代码好看些。
        Ordering<Person> ordering = OrderingConstants.AGE_ORDERING.compound(OrderingConstants.NAME_ORDERING);
        persons.sort(ordering);
        persons.forEach(person -> System.out.println(person.toString()));
    }

    /**
     * 把原来的顺序，任意打乱，返回。测试几次，返回的都一样，不是随机的。
     * 好像没啥用的样子
     */
    private static void arbitrary() {
        List<String> list = Lists.newArrayList("1", "2", "3", "4", "5", "4", "3", "2", "1", "0");
        Ordering<Object> arbitrary = Ordering.arbitrary();
        System.out.println(list.toString());
        list.sort(arbitrary);
        System.out.println(list.toString());
    }

    /**
     * 返回指定的元素的排序副本，不修改原来的list。
     */
    private static void sortedCopy() {
        List<String> list = Lists.newArrayList("12345", "2345", "345", "34", "4", "", "5", "55", "测试", "基线", "有钱");
        List<String> sortedCopy = Ordering.natural().sortedCopy(list);
        System.out.println(list.toString());
        System.out.println(sortedCopy.toString());
    }

    /**
     * 对某个类的某个属性进行排序，很常用的姿势。
     */
    private static void classCompare() {
        //集合初始化的时候，若大小可知，应初始化固定大小的集合，也是个好习惯。
        List<Person> persons = Lists.newArrayListWithExpectedSize(4);
        persons.add(new Person(11, "周星驰"));
        persons.add(new Person(99, "陈世美"));
        persons.add(new Person(21, "潘金莲"));
        persons.add(new Person(15, "阿姆斯特丹"));

        persons.forEach(person -> System.out.print(person.getAge() + " "));
        System.out.println();
        persons.sort(OrderingConstants.AGE_ORDERING);
        persons.forEach(person -> System.out.print(person.getAge() + " "));
        System.out.println();

        persons.forEach(person -> System.out.print(person.getName() + " "));
        System.out.println();
        persons.sort(OrderingConstants.NAME_ORDERING);
        persons.forEach(person -> System.out.print(person.getName() + " "));
        System.out.println();
    }

    /**
     * 实现自定义的排序器
     * 常用姿势，传入需要比较的对象，然后使用对应的属性去compare，返回int。返回个比较器，然后list就可以使用啦。
     * 这地方使用的是简单的字符串，还示范了使用传入自定义类对象，根据某个属性排序的例子。
     */
    private static void usually() {
        List<String> list = Lists.newArrayList("12345", "2345", "345", "34", "4", "", "5", "55", "测试", "基线", "有钱");
        Ordering<String> lengthOrdering = new Ordering<String>() {
            @Override
            public int compare(String left, String right) {
                return Ints.compare(left.length(), right.length());
            }
        };
        list.sort(lengthOrdering);
        System.out.println(list.toString());

        list.sort(lengthOrdering.reverse());
        System.out.println(list.toString());


        //这样汉字就可以按首字母排序啦。
        Ordering<String> stringOrdering = new Ordering<String>() {
            @Override
            public int compare(String left, String right) {
                return Collator.getInstance(Locale.CHINA).compare(left, right);
            }
        };
        list.sort(stringOrdering);
        System.out.println(list.toString());
    }

    /**
     * 默认的排序器
     * natural() 对可排序类型做自然排序，如数字按大小，日期按先后排序
     * reverse() 对当前的比较器进行反转
     * 自然排序，不能将汉字按首字母的顺序排序。
     */
    private static void naturalTest() {
        List<String> list = Lists.newArrayList("12", "2", "3", "33", "4", "44", "5", "55", "测试", "基线", "有钱");

        Ordering<Comparable> natural = Ordering.natural();
        list.sort(natural);
        System.out.println(list.toString());
        //反转
        list.sort(natural.reverse());
        System.out.println(list.toString());

        List<Integer> ints = Lists.newArrayList(12, 2, 3, 33, 4, 44, 5, 55);
        ints.sort(natural);
        System.out.println(ints.toString());
        ints.sort(natural.reverse());
        System.out.println(ints.toString());
    }


    /**
     * 测试model集合按某属性排序
     */
    private static void testListSort() {
        System.out.println("Person 集合：第二个方法要求实现一个java.util.Comparator接口。");
        Person p1 = new Person(11, "adf");
        Person p2 = new Person(99, "ggf");
        Person p3 = new Person(21, "444");
        Person p4 = new Person(15, "yrf");

        //集合初始化的时候，若大小可知，应初始化固定大小的集合，也是个好习惯。
        List<Person> persons = Lists.newArrayListWithCapacity(4);
        persons.add(p1);
        persons.add(p2);
        persons.add(p3);
        persons.add(p4);

        //注释是因为这个产生的列表有序啦。
        //代码里面有很多的空行是为了把不同功能的代码段分开，良好的编码习惯。
        // 写完代码记得格式化一下，也是习惯。
        //每个方法每个类都得带Java doc文档注释也是好习惯，当然我就不加了，提醒一下读者的你。
        //for (int i = 0; i < 4; i++) {
        //    persons.add(new Person(i, "cms" + i));
        //}
        System.out.println("persons原来的默认顺序如下：");
        for (Person p : persons) {
            System.out.println(p.toString());
        }

        System.out.println("------下面按 age int 类型排序-----升序-------");
        Comparator<Person> ascComparatorByAge = new PersonByAge();
        Collections.sort(persons, ascComparatorByAge);
        for (Person p : persons) {
            System.out.println(p.toString());
        }

        System.out.println("-------下面按 name string类型排序----Java升序-------");
        Comparator<Person> ascComparatorByName = new PersonByName();
        Collections.sort(persons, ascComparatorByName);
        for (Person p : persons) {
            System.out.println(p.toString());
        }


        System.out.println("Car 集合：第一个方法要求所排序的元素类必须实现java.lang.Comparable接口。");

        Car car1 = new Car("ben", 1000);
        Car car2 = new Car("qqq", 1);
        Car car3 = new Car("bmw", 10000);
        Car car4 = new Car("wow", 100);
        List<Car> cars = Lists.newArrayListWithCapacity(4);
        cars.add(car1);
        cars.add(car2);
        cars.add(car3);
        cars.add(car4);

        System.out.println("cars原来的默认顺序如下：");
        for (Car car : cars) {
            System.out.println(car.toString());
        }

        System.out.println("------下面按 price int 类型排序--升序----------");
        Collections.sort(cars);
        for (Car car : cars) {
            System.out.println(car.toString());
        }
    }

}
