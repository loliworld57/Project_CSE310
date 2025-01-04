package com.myclass.students;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class StudentController {
    @Autowired
    private StudentService stuService;

    @GetMapping("/students")
    public String showStudents(Model model) {
        List<Student> listStudents = stuService.getAllStudents();
        model.addAttribute("listStudents", listStudents);
        return "students";
    }

    @GetMapping("/students/new")
    public String showNewStudent(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("pageTitle", "Add Student");
        return "student_form";
    }

    @PostMapping("/students/save")
    public String saveStudent(Student student, RedirectAttributes rA) {
        stuService.save(student);
        rA.addFlashAttribute("message", "Successfully Saved");
        return "redirect:/students";
    }

    @GetMapping("/students/update/{id}")
    public String showEditStudent(@PathVariable("id") Integer id, Model model, RedirectAttributes rA) {
        try {
            Student stu = stuService.getStudent(id);
            rA.addFlashAttribute("message", "Sucessfully Edited");
            model.addAttribute("student", stu);
            model.addAttribute("pageTitle", "Edit Student " + id);
            return "student_form";
        } catch (StudentNotFoundException e) {
            rA.addFlashAttribute("message", "Failed to Edit Student");
            return "redirect:/students";
        }
    }
    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable("id") Integer id, RedirectAttributes rA) {
        try {
            rA.addFlashAttribute("message", "Sucessfully Deleted");
            stuService.deleteStudent(id);
        }  catch (StudentNotFoundException e){
            rA.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/students";
    }

}
