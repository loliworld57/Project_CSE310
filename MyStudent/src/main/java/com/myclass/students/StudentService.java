package com.myclass.students;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository stuR;

    public List<Student> getAllStudents() {
        return (List<Student>) stuR.findAll();
    }

    public void save(Student stu) {
        stuR.save(stu);

    }

    public Student getStudent(int id) throws StudentNotFoundException {
        Optional<Student> stu = stuR.findById(id);
        if (stu.isPresent())
            return stu.get();

        throw new StudentNotFoundException("Could not find this student");
    }

    public void deleteStudent(int id) throws StudentNotFoundException {

        if (stuR.findById(id).isPresent()) {
            stuR.deleteById(id);
        } else {
            throw new StudentNotFoundException("Could not find this student " + id);
        }
    }
}
