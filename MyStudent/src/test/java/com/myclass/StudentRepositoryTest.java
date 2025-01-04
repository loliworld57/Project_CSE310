package com.myclass;

import com.myclass.students.Student;
import com.myclass.students.StudentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class StudentRepositoryTest {
    @Autowired private StudentRepository stuR;

    @Test
    public void testAddStudent() {
        Student stu = new Student();
        stu.setStudentName("Le van A");
        stu.setEntryDate("02/01/2025");
        stu.setParentNumber("8989898989");

        Student saveSty = stuR.save(stu);
        Assertions.assertThat(saveSty).isNotNull();
        Assertions.assertThat(saveSty.getStudentId()).isGreaterThan(0);
    }

    @Test
    public void testListAllStudents() {
        Iterable<Student> students =  stuR.findAll();
        Assertions.assertThat(students).hasSizeGreaterThan(0);
        for(Student s : students){
            System.out.println(s.toString());
        }
    }

    @Test
    public void testUpdateStudent() {
        Integer studentId = 1;
        Optional<Student> optStudent = stuR.findById(studentId);
        Student stu = optStudent.get();
        stu.setParentNumber("09090909");
        stuR.save(stu);

        Student updateStudent = stuR.findById(studentId).get();
        Assertions.assertThat(updateStudent.getParentNumber()).isEqualTo("09090909");
    }

    @Test
    public void testGetStudentById() {
        Integer studentId = 1;
        Optional<Student> optStudent = stuR.findById(studentId);
        Assertions.assertThat(optStudent).isPresent();
        System.out.println(optStudent.get().toString());
    }

    @Test
    public void testDeleteStudent() {
        Integer studentId = 2;
        stuR.deleteById(studentId);

        Optional<Student> optStudent = stuR.findById(studentId);
        Assertions.assertThat(optStudent).isNotPresent();

    }
}
