package com.lxk.jdk.file.character;

import java.io.FileWriter;
import java.io.IOException;

class FileWriterDemo {
    public static void main(String[] args) throws IOException {
        //创建一个FileWriter对象。该对象一被初始化就必须要明确被操作的文件。
        //而且该文件会被创建到指定目录下。如果该目录下已有同名文件，将被覆盖。
        //其实该步就是在明确数据要存放的目的地。
        FileWriter fw = new FileWriter("e:/demo.txt");

        //这个写法就是将续写文件。而不是像上面的直接覆盖。
        //FileWriter fw = new FileWriter("d:demo.txt",true);
        System.out.println(fw.getEncoding());
        //调用write方法，将字符串写入到流中。
        fw.write("abcde");

        //刷新流对象中的缓冲中的数据。
        //将数据刷到目的地即文件中
        //fw.flush();


        //close方法：关闭流资源，但是关闭之前会刷新一次内部的缓冲中的数据。
        //将数据刷到目的地中。
        //和flush区别：flush刷新后，流可以继续使用，close刷新后，会将流关闭。
        fw.close();
        //不用java，也可以新建文件，往里面写数据，说明windows本身具有这么个功能，那么java能往windows系统的文件写数据，说明Java在调用Windows
        //的写数据的功能，这就是我们说的流资源，Java本身是不能往硬盘写数据的，Java会调用系统的内容来完成数据的建立，这些调用都是在使用windows的资源
        //所以，用完一定要释放资源。

        //这个例子没有很正确的姿势关闭文件流，需要注意哈。正确姿势是try catch finally中关闭流资源。
    }
}
