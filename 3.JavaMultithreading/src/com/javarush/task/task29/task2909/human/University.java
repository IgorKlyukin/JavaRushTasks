package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University {
    private String name;
    private int age;
    private List<Student> students = new ArrayList<>();

    public University(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        //TODO:
        for (Student student :
                students) {
            if (student.getAverageGrade() == averageGrade) {
                return student;
            }
        }
        return null;
    }

    public Student getStudentWithMaxAverageGrade() {
        //TODO:
        Student maxStudent = null;
        if (students.size() > 0){
            maxStudent = students.get(0);
            for (Student student :
                    students) {
                if (student.getAverageGrade() > maxStudent.getAverageGrade()) {
                    maxStudent = student;
                }
            }
        }
        return maxStudent;
    }

    public Student getStudentWithMinAverageGrade() {
        Student minStudent = null;
        if (students.size() > 0){
            minStudent = students.get(0);
            for (Student student :
                    students) {
                if (student.getAverageGrade() < minStudent.getAverageGrade()) {
                    minStudent = student;
                }
            }
        }
        return minStudent;
    }

    public void expel(Student student) {
        //TODO:
        students.remove(student);
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}