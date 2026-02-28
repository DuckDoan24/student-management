package vn.edu.hcmut.cse.adsoftweng.lab.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.edu.hcmut.cse.adsoftweng.lab.service.StudentService;
import vn.edu.hcmut.cse.adsoftweng.lab.entity.Student;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/students")
public class StudentWebController {
    @Autowired
    private StudentService service;
    // Route: GET http://localhost:8080/students
    @GetMapping
    public String getAllStudents(@RequestParam(required = false) String keyword, Model model) {
        List<Student> students;
        if (keyword != null && !keyword.isEmpty()) {
        // Can viet them ham searchByName trong Service/Repository
            students = service.searchByName(keyword);
        } else {
            students = service.getAll();
        }
        model.addAttribute("dsSinhVien", students);
        model.addAttribute("keyword", keyword);
        return "students";
    }

    @GetMapping("/{id}")
    public String getStudent(@PathVariable String id, Model model) {
        Student stu;
        stu = service.getById(id);
        model.addAttribute("student", stu);
        return "student_detail";
    }

    @GetMapping("/insert")
    public String getAddStudent(Model model) {
        model.addAttribute("student", new Student());
        return "insert";
    }
    @PostMapping("/insert")
    public String addStudent(@ModelAttribute Student student, BindingResult result, Model model) {
        if (service.existsById(student.getId())) {
            result.rejectValue("id", "error.student", "ID đã tồn tại, vui lòng chọn ID khác");
        }
        if (result.hasErrors()) {
            return "insert";
        }
        service.update(student);
        return "redirect:/students";
    }

    @GetMapping("/modify/{id}")
    public String getModStudent(@PathVariable String id, Model model) {
        Student stu;
        stu = service.getById(id);
        model.addAttribute("student", stu);
        return "modify";
    }
    @PostMapping("/modify/{id}")
    public String modifyStudent(@ModelAttribute Student student) {  
        service.update(student);
        return "redirect:/students";
    }

    @PostMapping("/delete/{id}")
    public String deleteStudent(@PathVariable String id) {
        service.deleteById(id);
        return "redirect:/students";
    }
}
