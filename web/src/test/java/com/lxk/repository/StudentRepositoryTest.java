//package com.lxk.repository;
//
//import com.lxk.AbstractTest;
//import com.lxk.model.Student;
//import org.junit.Test;
//
//import javax.annotation.Resource;
//import java.util.List;
//
///**
// * 学生对象的数据库相关的测试
// *
// * @author lxk on 2017/11/28
// */
//public class StudentRepositoryTest extends AbstractTest {
//
//    @Resource(name = "studentRepository")
//    private StudentRepository dao;
//
//    @Test
//    public void findAll() {
//        List<Student> all = dao.findAll();
//        all.forEach(student -> System.out.println(student.toString()));
//    }
//
//    @Test
//    public void save() {
//        Student student = new Student("测试序列化", 18, true);
//        student.setFloor(18);
//        student.setMoney(100);
//        System.out.println(dao.save(student).toString());
//    }
//
//    @Test
//    public void findByName() {
//        List<Student> list = dao.findByName("测试序列化");
//        System.out.println();
//        if (list != null && !list.isEmpty()) {
//            list.forEach(student -> System.out.println(student.toString()));
//        }
//    }
//}
