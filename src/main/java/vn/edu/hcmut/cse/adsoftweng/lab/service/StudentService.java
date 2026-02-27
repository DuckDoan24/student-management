package vn.edu.hcmut.cse.adsoftweng.lab.service;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import java.util.List;
import vn.edu.hcmut.cse.adsoftweng.lab.entity.Student;
import vn.edu.hcmut.cse.adsoftweng.lab.repository.StudentRepository;

@Service
public class StudentService {
	@Autowired
	private StudentRepository repository;
	public List<Student> getAll() {
		return repository.findAll();
	}

	public Student getById(String id) {
		return repository.findById(id).orElse(null);
	}

	public List<Student> searchByName(String name) {
		return repository.findByNameContaining(name);
	}

	public void update(Student student) {
		repository.save(student);
	}

	public void deleteById(String id) {
		repository.deleteById(id);
	}
}
