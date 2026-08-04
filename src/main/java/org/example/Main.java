package org.example;

import org.example.model.Student;
import org.example.repository.StudentRepository;

import javax.imageio.spi.ServiceRegistry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        StudentRepository studentRepository=new StudentRepository();
        studentRepository.createStudent(
                new Student("priti",43,"priti@gmail.com"));



//        studentRepository.createStudent(
//                new Student("priti",43,"priti@gmail.com"),
//               new Student("ankita" , 43 , "ankita@gmailcom"),
//                new Student("ram " , 21 , "ram@gmail.com")
//        );

        //studentRepository.updateStudent();

//        studentRepository.deleteStudent();


       // studentRepository.getStudentById(2);

        //studentRepository.getAllStudent();

//        studentRepository.allCrud();
     }
}