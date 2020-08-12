# 请叫我大师兄 Java Note Project![:kiss:][lips]

## 项目结构简单说明目录
* [Bean](#bean)
* [DesignPattern](#设计模式)
* [Doc](#doc)
* [ElasticSearch](#ElasticSearch)
* [Guava](#Guava)
* [JDK](#JDK)
* [JDK8](#JDK8)
* [JSON](#JSON)
* [JVM](#JVM)
* [Lombok](#Lombok)
* [MD](#MD)
* [Questions](#Questions)
* [Sorting](#十大排序算法)
* [SpringMVC](#SpringMVC)
* [Thread](#Thread)
* [Tool](#Tool)
* [Vertx](#vertx)
* [Web](#web)
* [Life](#漫漫人生路)
* [About me](#about-me)
* [Copyright and License](#copyright-and-license)

## bean
```
统一存放一些测试使用的model的bean，enum...
    
```
  
## 设计模式
### 简要说明
```
设计模式是一套被反复使用的、多数人知晓的、经过分类编目的、代码设计经验的总结。 
使用设计模式是为了重用代码、让代码更容易被他人理解、保证代码可靠性。  
基于以下的面向对象设计原则。 
- 对接口编程而不是对实现编程。 
- 优先使用对象组合而不是继承。 
```

### 创建型模式
```
工厂模式（Factory Pattern）
抽象工厂模式（Abstract Factory Pattern）
单例模式（Singleton Pattern）
建造者模式（Builder Pattern）
原型模式（Prototype Pattern）
```

### 结构型模式
```
适配器模式（Adapter Pattern）
桥接模式（Bridge Pattern）
过滤器模式（Filter、Criteria Pattern）
组合模式（Composite Pattern）
装饰器模式（Decorator Pattern）
外观模式（Facade Pattern）
享元模式（Flyweight Pattern）
代理模式（Proxy Pattern）
```

### 行为型模式
```
责任链模式（Chain of Responsibility Pattern）
命令模式（Command Pattern）
解释器模式（Interpreter Pattern）
迭代器模式（Iterator Pattern）
中介者模式（Mediator Pattern）
备忘录模式（Memento Pattern）
观察者模式（Observer Pattern）
状态模式（State Pattern）
空对象模式（Null Object Pattern）
策略模式（Strategy Pattern）
模板模式（Template Pattern）
访问者模式（Visitor Pattern）
```

### J2EE 模式
```
MVC 模式（MVC Pattern）
业务代表模式（Business Delegate Pattern）
组合实体模式（Composite Entity Pattern）
数据访问对象模式（Data Access Object Pattern）
前端控制器模式（Front Controller Pattern）
拦截过滤器模式（Intercepting Filter Pattern）
服务定位器模式（Service Locator Pattern）
传输对象模式（Transfer Object Pattern）
```
## doc
 ```
 主要存储一些，可复用的文档
 doc文档  
 pdf书籍  
 txt文档  
 ......  
     
 ``` 
## ElasticSearch
```
Elastic Search    
一个好的存储大数据的一个工具  
很多场景下，都需要使用Java来操作这个工具。  
这个项目，就来学习这个工具。
  
```
## Guava
```
Guava    
google的一个通用的工具jar  
里面有很多好东西  
实际使用的，估计是整个jar的冰山一角  
这个项目就学习一下，这个工具的使用方法  
  
```
## JDK
```
JDK    
jdk中很多代码都是似懂非懂的  
有的疑问的地方，还是自己手动实际测试一下来解惑是比较好的 
  
```
## JDK8
```
JDK 8   
它已经出来n年了  
但是，实际在代码开发的时候，使用还不是很广泛  
这个项目就学习和使用一下的例子  
  
```
## JSON
```
JSON 常用的数据结构  
json工具的jar也有很多的  
比如：fastjson，gson，jackson ...  
但是，他们的效率如何，什么情况下，序列化和反序列化谁快。。。  
都是未知的，需要测试一下，自己确认一下。  

```
## JVM
```
JMM java内存分区模型  
Java的GC  
内存重排序的代码实例  
```
## Lombok
```
lombok 工具  
这个工具，可以使得开发人员不用再去手动实现一些 getter setter 构造函数 等简单的方法。
会在编译阶段，自动给class文件带上这些个自动生成的方法。  
很是方便，主要测试和学习一下这个工具的使用。  
```
## MD
```
要写 README.md 文件，咱就的学习一下这个md的语法  
这个项目，就存了几个md文件，学习一下这个md语法  
不记得了，可以参考一下。  
```
## Questions
```
记录一些程序题
```
## 十大排序算法  
![十大经典排序算法 概览截图](sorting/res/sort.png)
1. [冒泡排序](sorting/1.bubbleSort.md)
2. [选择排序](sorting/2.selectionSort.md)
3. [插入排序](sorting/3.insertionSort.md)
4. [希尔排序](sorting/4.shellSort.md)
5. [归并排序](sorting/5.mergeSort.md)
6. [快速排序](sorting/6.quickSort.md)
7. [堆排序](sorting/7.heapSort.md)
8. [计数排序](sorting/8.countingSort.md)
9. [桶排序](sorting/9.bucketSort.md)
10. [基数排序](sorting/10.radixSort.md)
## SpringMVC
```
一个简单的spring mvc的 web 例子  
主要实现简单的页面到后台controller到service再到dao  
这么一个简单的过程  
体验一下入门时候说的分层结构   
```
 
## Thread
```
thread  
主要记录一些 Java 线程相关测试的使用  
CountDownLatch、CyclicBarrier、DeadLockDemo、volatile、n 个 ThreadPool...
  
```
## Tool
```
算是一个底层的 common jar项目  
此处的其他几个项目基本都依赖这个项目  
此项目 主要是存放一些公用的工具jar依赖    
比如，lombok，guava，fastjson ...  
```
## vertx
```
使用 vertx 框架做的微服务  
主要是学习 vertx 的使用  
vertx 果然还是不太适合，还是Spring cloud 香
```
## web
```
想着弄个web例子的，但是后期发现springboot相比之前的Spring mvc 简直是ezsy的不要不要的，加上使用vue，前后端分离之后  
撇开了过时的jsp页面啥的，后端完全从js里面解放出来了。也不需要做web调试了。
```
## 漫漫人生路
![begin][start] ![going on][going on] ![going on][going on more]

## About Me
- QQ：[1181415316][CSDN_]
- Email：  [cmshome@163.com][Email]
- GitHub： [Please Call Me Big Brother.][Github]
- CSDN：   [请叫我大师兄][CSDN]

## Copyright and License
**The MIT License (MIT)**


[Email]:mailto:cmshome@163.com "我的邮箱"
[Github]:https://github.com/cmshome "我的GitHub"
[CSDN]:http://blog.csdn.net/qq_27093465?viewmode=contents "我的CSDN"
[CSDN_]:http://blog.csdn.net/qq_27093465?viewmode=contents "我的qq号"
[start]:http://forum.csdn.net/PointForum/ui/scripts/csdn/Plugin/003/onion/41.gif "刚刚毕业，好好学习。"
[going on]:http://forum.csdn.net/PointForum/ui/scripts/csdn/Plugin/003/onion/83.gif "渐入佳境，高调装逼。"
[going on more]:http://forum.csdn.net/PointForum/ui/scripts/csdn/Plugin/003/onion/2.gif "最高境界，低调低调。"
[lips]:https://github.com/jsw0528/rails_emoji/raw/master/vendor/assets/images/emojis/kiss.png "烈焰红唇"

