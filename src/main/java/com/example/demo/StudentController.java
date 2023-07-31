package com.example.demo;

import com.example.demo.dto.StudentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> fetchAllStudents(){
        return ResponseEntity.ok().body(studentService.getAll());
    }

    @PostMapping
    public ResponseEntity <Student> createStudent(@RequestBody StudentDto studentDto){
        return ResponseEntity.ok().body(studentService.insert(studentDto));
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody StudentDto studentDto){
        return ResponseEntity.ok().body(studentService.update(studentDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String id){
        studentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
