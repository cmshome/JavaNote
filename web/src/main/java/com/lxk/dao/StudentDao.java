package com.lxk.dao;

import com.google.common.collect.Lists;
import com.lxk.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lxk on 2017/3/27
 */
@Repository("studentDao")
public class StudentDao {

    public List<Student> findAll() {
        List<Student> all = Lists.newArrayList();
        all.add(new Student("1", "tom", 18, true));
        all.add(new Student("2", "cat", 28, true));
        all.add(new Student("3", "lxk", 18, true));
        all.add(new Student("3", "李学凯", 18, true));
        all.add(new Student("4", "cms", 18, true));
        return all;
    }
}
