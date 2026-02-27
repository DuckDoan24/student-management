package vn.edu.hcmut.cse.adsoftweng.lab.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;
import java.util.*;
import vn.edu.hcmut.cse.adsoftweng.lab.entity.Student;
import vn.edu.hcmut.cse.adsoftweng.lab.service.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController {
	@Autowired
	private StudentService service;
	// 1. API Lấy danh sách: GET http://localhost:8080/api/students
	@GetMapping
	public List<Student> getAllStudents() {
		return service.getAll();
	}
	// 2. API Lấy chi tiết: GET http://localhost:8080/api/students/{id}
	@GetMapping("/{id}")
	public Student getStudentById(@PathVariable String id) {
	// Lưu ý: Cần thêm method getById trong Service trước
		return service.getById(id);
	}
}

