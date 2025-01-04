package com.myclass.students;

import jakarta.persistence.*;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentId;

    @Column(nullable = false, length = 50)
    private String studentName;

    @Column(nullable = false, length = 50)
    private String entryDate;

    private char performance;

    @Column(nullable = false, length = 12)
    private String parentNumber;

    private String imgFileName;




    public String getImgFileName() {
        return imgFileName;
    }

    public void setImgFileName(String imgFileName) {
        this.imgFileName = imgFileName;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public char getPerformance() {
        return performance;
    }

    public void setPerformance(char performance) {
        this.performance = performance;
    }

    public String getParentNumber() {
        return parentNumber;
    }

    public void setParentNumber(String parentNumber) {
        this.parentNumber = parentNumber;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", entryDate='" + entryDate + '\'' +
                ", performance=" + performance +
                ", parentNumber='" + parentNumber + '\'' +
                '}';
    }
}
