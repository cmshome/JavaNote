package com.lxk.design.pattern.dataaccessobject;

import java.util.List;

/**
 * @author LiXuekai on 2020/7/26
 */
public interface StudentDao {
    List<Student> getAllStudents();

    Student getStudent(int rollNo);

    void updateStudent(Student student);

    void deleteStudent(Student student);
}
