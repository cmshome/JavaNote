## Linux 下JDK自带的排查jvm信息的几个命令常用形式  
  
  
### [jps (java ps)][0]
|命令         |含义|
|:----        |-----|
|jps         |输出pid和进程简单名称|
|jps -l      |输出pid和完全的包名，应用主类名，jar的完全路径名|
|jps -m      |输出main method的参数|
|jps -v      |输出pid name 输出jvm参数|


### [jstat][1]
|命令         |含义|
| :------------ |-----|
|jstat -gc              pid 1s 100             |一秒一次，执行100次，单位都是 kb|
|jstat -gccapacity      pid 1s 100             |Java堆各区域使用到的最大最小空间|
|jstat -gcutil          pid 1s 100             |关注已使用空间占此空间的百分比|
|jstat -gcnew           pid 1s 100             |统计新生代的gc情况|
|jstat -gcnewcapacity   pid 1s 100             |关心new区的各个最大和最新空间|
|jstat -gcold           pid 1s 100             |统计old区，堆里面老年代的gc情况|
|jstat -gcoldcapacity   pid 1s 100             |统计old区的各个分区的容量最大最小情况| 
|jstat -gcmetacapacity  pid 1s 100             |看元空间的gc情况| 
|jstat -class           pid 1s 10              |监视类装载、卸载数量，总空间、类装载所耗费时间| 
|jstat -compiler        pid                    |输出jit编译器编译过的方法耗时等信息| 


### [jinfo][2]
|命令         |含义|
|:---        |-----|
|jinfo pid             |打印所有信息|
|jinfo -flags pid     |输出的是默认值和命令行的jvm信息设置|


### [jmap][3]
|命令         |含义|
|:----        |-----|
|jmap -heap            pid                     |打印heap的概要信息，GC使用的算法，heap（堆）的配置及JVM堆内存的使用情况|
|jmap -histo:live      pid > histo.txt         |打印堆的直方图。对于每个Java类，将打印对象数量、内存大小(以字节为单位)和完全限定的类名。|
|jmap -finalizerinfo   pid                     |打印正等候回收的对象的信息|


### [jstack][4]
|命令         |含义|
|:----        |-----|
|jstack -l pid          |打印Java线程的Java 栈跟踪|


### [Copyright and License][5]
**The MIT License (MIT)**

*******************
[0]: https://lixuekai.blog.csdn.net/article/details/106421721
[1]: https://lixuekai.blog.csdn.net/article/details/106524222
[2]: https://lixuekai.blog.csdn.net/article/details/106555276
[3]: https://lixuekai.blog.csdn.net/article/details/106672147
[4]: https://lixuekai.blog.csdn.net/article/details/106691145
[5]: https://github.com/cmshome/JavaNote/blob/master/jvm/src/main/resources/LinuxJvm.md
