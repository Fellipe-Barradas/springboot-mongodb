package com.example.demo;

import com.example.demo.dto.StudentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public List<Student> getAll(){
        return studentRepository.findAll();
    }

    public Student insert(StudentDto studentDto){
        if(studentRepository.findStudentByEmail(studentDto.email()).isEmpty()){
            Address address = new Address(studentDto.city(),studentDto.contry(),studentDto.postalCode());
            Student student = new Student(
                    studentDto.firstName(),
                    studentDto.lastName(),
                    studentDto.email(),
                    Gender.valueOf(studentDto.gender()),
                    address,
                    studentDto.favoriteSubjects(),
                    studentDto.totalSpentInBooks(),
                    LocalDateTime.now()
            );
            return studentRepository.insert(student);
        }
        throw new RuntimeException("User email already being used");
    }

    public Student update(StudentDto studentDto){
        Optional<Student> entity = studentRepository.findStudentByEmail(studentDto.email());
        if(entity.isPresent()){
            updateStudent(entity.get(), studentDto);
            studentRepository.save(entity.get());
            return entity.get();
        }
        throw new RuntimeException("User email dosen't exists");
    }

    public void deleteById(String id){
        studentRepository.deleteById(id);
    }

    private void updateStudent(Student student, StudentDto studentDto) {
        Address address = new Address(studentDto.contry(), studentDto.city(), studentDto.postalCode());
        student.setAddress(address);
        student.setGender(Gender.valueOf(studentDto.gender()));
        student.setFavoriteSubjects(studentDto.favoriteSubjects());
        student.setTotalSpentInBooks(studentDto.totalSpentInBooks());
    }
}
