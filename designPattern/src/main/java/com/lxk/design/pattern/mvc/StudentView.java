package com.lxk.design.pattern.mvc;

/**
 * @author LiXuekai on 2020/7/24
 */
public class StudentView {
    public void printStudentDetails(String studentName, String studentRollNo) {
        System.out.println("Student: ");
        System.out.println("Name: " + studentName);
        System.out.println("Roll No: " + studentRollNo);
    }
}
